package com.security.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

import javax.security.auth.x500.X500Principal;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.bouncycastle.x509.X509V3CertificateGenerator;


@SuppressWarnings("deprecation")
public class KeyGenerator {
	
	public static void generateKeysandCert(String userId,HttpSession session)
	{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyPairGenerator keyPairGenerator;
		X509Certificate certificate=null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA","BC");
		
			keyPairGenerator.initialize(2048);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			//certificate generation
			
			 X509V3CertificateGenerator certificategenerator = new X509V3CertificateGenerator();
			 certificategenerator.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
			
			 certificategenerator.setIssuerDN(new X500Principal("CN="+userId));
			 certificategenerator.setSubjectDN(new X500Principal("CN="+userId));
			 //setting a random public key for the certificate. So it cannot be stolen
			 KeyPairGenerator keyPairGeneratorDummy = KeyPairGenerator.getInstance("RSA","BC");
			 keyPairGeneratorDummy.initialize(2048);
			 certificategenerator.setPublicKey(keyPairGeneratorDummy.generateKeyPair().getPublic());
			 certificategenerator.setNotBefore(new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
			 certificategenerator.setNotAfter(new Date(System.currentTimeMillis() +  365 * 24 * 60 * 60 * 1000));
			 certificategenerator.setSignatureAlgorithm("SHA256WithRSAEncryption");
			 certificate = certificategenerator.generateX509Certificate(keyPair.getPrivate(), new SecureRandom());
			  
			 
			 ServletContext context = session.getServletContext();
	            String realContextPath = context.getRealPath("/");
	            
	            File dir = new File(realContextPath+"/certificates");
	              if (!dir.exists())
	                  dir.mkdirs();
	            
	            
	            String certpath = realContextPath+"/certificates/"+userId+"_cert.pem";
			 
			 
			  //String certpath = "/"+userId+"_cert.pem";
			 PemWriter pemWriter = new PemWriter(new FileWriter(new File(certpath)));
			 pemWriter.writeObject(new PemObject(certificate.getType(),certificate.getEncoded()));
			 pemWriter.flush();
			 pemWriter.close();
			 
			//discard private key after certificate generation
			//save public key in vault
			Key publicKey = keyPair.getPublic();
			savePublicKey(publicKey,userId,session);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return certificate;
 catch (CertificateEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	private static void savePublicKey(Key publicKey, String userId,HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		// TODO Auto-generated method stub
		KeyFactory factory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec publicKeySpec = factory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		
		ServletContext context = session.getServletContext();
        String realContextPath = context.getRealPath("/");
        
        File dir = new File(realContextPath+"/publickeys");
        if (!dir.exists())
            dir.mkdirs();
        
        String path = realContextPath+"/publickeys/"+userId+"_public.key";
		//String path = "/"+userId+"_public.key";
		File publickey = new File(path);
		System.out.println(publickey.getAbsolutePath());
		FileOutputStream fout = new FileOutputStream(publickey);
		ObjectOutputStream outputStream = new ObjectOutputStream(fout);
		outputStream.writeObject(publicKeySpec.getModulus());
		outputStream.writeObject(publicKeySpec.getPublicExponent());
		outputStream.close();
		
		
	}
	
	private  static PublicKey readPublicKeyFromFile(String filename,HttpSession session) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		
		ServletContext context = session.getServletContext();
        String realContextPath = context.getRealPath("/");
        
		FileInputStream filein = new FileInputStream(realContextPath+"/publickeys/"+filename);
		ObjectInputStream ois = new ObjectInputStream(filein);
		BigInteger modulus=(BigInteger) ois.readObject();
		BigInteger exponent = (BigInteger)ois.readObject();
		ois.close();
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PublicKey publickey = factory.generatePublic(keySpec);
		return publickey;
	}
	
	
	
	
	
	
	
	
	
	public static boolean verifyCertificate(String userId,HttpSession session)
		{
			
			String filename = userId+"_public.key";
			try {
				PublicKey publicKey = readPublicKeyFromFile(filename,session);
				
				ServletContext context = session.getServletContext();
	            String realContextPath = context.getRealPath("/");
	            String certpath = realContextPath+"/certificates/"+userId+"_cert.pem";
				 
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				FileInputStream inputStream = new FileInputStream(new File(certpath));
				X509Certificate certificate = (X509Certificate) cf.generateCertificate(inputStream);
				 inputStream.close();
				 certificate.checkValidity(new Date());
				 certificate.verify(publicKey);
				
			     return true;
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateExpiredException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateNotYetValidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		
		
	

}
