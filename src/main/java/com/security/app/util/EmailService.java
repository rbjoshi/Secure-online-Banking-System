package com.security.app.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.security.app.constant.Constants;
import com.security.app.model.PersonalInfo;

public class EmailService {

	private MailSender mailSender;
	public EmailService(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendPasswordResetEMail(PersonalInfo personalInfo, String OTP)
			throws NullPointerException, MailException {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(personalInfo.getEmailId());
		message.setSubject("Devil's Bank : Reset password. Request is completed at "+new Timestamp(Calendar.getInstance().getTime().getTime()));

		StringBuilder sb = new StringBuilder();
		sb.append("Hi ").append(personalInfo.getFirstName()).append(",\n\n");
		sb.append("Your OTP for reseting the password is :").append(OTP)
				.append("\n");
		sb.append("Please go to the following link\n\n");

		sb.append(Constants.LINK_PASSWORD_RESET).append("\n\n");

		sb.append("Thank you..!!");
		message.setText(sb.toString());
		Thread mailSenderThread = new Thread(new Runnable() {			
			public void run() {
				mailSender.send(message);
			}
		});
		mailSenderThread.start();
	}

	public void sendAccountCreationPassWordEmail(PersonalInfo personalInfo,
			String passrod, String passKey) {

		final SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(personalInfo.getEmailId());
		message.setSubject("Devil's Bank : Your Account is Created. Request Completed at "+new Timestamp(Calendar.getInstance().getTime().getTime()));

		StringBuilder sb = new StringBuilder();
		sb.append("Hello ").append(personalInfo.getFirstName()).append(",\n\n");
		sb.append("Congratulation.!! You account is created. The login password is :").append(passrod)
				.append("\n");
		sb.append("Your site pass key is :").append(passKey)
			.append("\n Always check for passkey while logging in.\n\n");
		sb.append("Thank you..!!");
		message.setText(sb.toString());
		Thread mailSenderThread = new Thread(new Runnable() {			
			public void run() {
				mailSender.send(message);
			}
		});
		mailSenderThread.start();
	}
	
	private void sendEmailWithAttachment(SimpleMailMessage message, String fileName, boolean makeThread) throws MessagingException {
		final MimeMessage mimeMessage = ((JavaMailSender)mailSender).createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setTo(message.getTo());
		helper.setText(message.getText());
		helper.setSubject(message.getSubject());
		FileSystemResource file = new FileSystemResource(fileName);
		helper.addAttachment(file.getFilename(), file);
		
		if (!makeThread) {
			((JavaMailSender) mailSender).send(mimeMessage);
		try {
			file.getOutputStream().close();
			file.getInputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		} else {
			Thread mailSenderThread = new Thread(new Runnable() {
				public void run() {
					((JavaMailSender) mailSender).send(mimeMessage);
				}
			});
			mailSenderThread.start();
		}
	}
	public void sendPublicKeyFileEmail(PersonalInfo personalInfo, String fileName) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(personalInfo.getEmailId());
		message.setSubject("Devil's Bank : Public Key. Send at "+new Timestamp(Calendar.getInstance().getTime().getTime()));

		StringBuilder sb = new StringBuilder();
		sb.append("Hi").append(personalInfo.getFirstName()).append(",\n\n");
		sb.append("Please find attached public key file.");
		sb.append("Thank you..!!");
		message.setText(sb.toString());
		sendEmailWithAttachment(message, fileName,true);
	}

	public void sendAccountDeletionNotificationEmail(PersonalInfo personalInfo) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(personalInfo.getEmailId());
		message.setSubject("Devil's Bank : Your Account is Deleted. Request Completed at "+new Timestamp(Calendar.getInstance().getTime().getTime()));

		StringBuilder sb = new StringBuilder();
		sb.append("Hi").append(personalInfo.getFirstName()).append(",\n\n");
		sb.append("You account is deleted.\n Thanks for choosing Devil's Bank..!!\n\n");
		sb.append("Thank you..!!");
		message.setText(sb.toString());
		Thread mailSenderThread = new Thread(new Runnable() {			
			public void run() {
				mailSender.send(message);
			}
		});
		mailSenderThread.start();
	}
	
	
	public void sendCertificateFileEmail(PersonalInfo personalInfo, String fileName,HttpSession session) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(personalInfo.getEmailId());
		message.setSubject("Devil's Bank : Certificate. Sent at "+new Timestamp(Calendar.getInstance().getTime().getTime()));

		StringBuilder sb = new StringBuilder();
		sb.append("Hi").append(personalInfo.getFirstName()).append(",\n\n");
		sb.append("Please find attached Certificate file.");
		sb.append("Thank you..!!");
		message.setText(sb.toString());
		
		ServletContext context = session.getServletContext();
        String realContextPath = context.getRealPath("/");
        
        File dir = new File(realContextPath+"/certificates");
          if (!dir.exists())
              dir.mkdirs();
        
        
        String filePath = realContextPath+"/certificates/"+fileName+"_cert.pem";
	 
		
		//String filePath="c://certificates/"+fileName+"_cert.pem";
		sendEmailWithAttachment(message, filePath,false);
		
		
	}
}
