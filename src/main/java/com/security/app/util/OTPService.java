package com.security.app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class OTPService {
	public static String createOTP() {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}
		
		String result = "";
		for (int i = 0; i < 4; i++) {
			Collections.shuffle(numbers);
			result += numbers.get(i).toString();
		}
		return result;
	}
	
	public static String getPassKey() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static String getRandomPassword() {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}
		
		List<Character> alphabets = new ArrayList<Character>();
		for (int i = 97; i <= 122; i++) {
			alphabets.add((char) i);
		}
		
		List<Character> special = new ArrayList<Character>();
		for (int i = 35; i <= 38; i++) {
			special.add((char) i);
		}
		StringBuilder sb = new StringBuilder();
		while(sb.length() < 8) {
			Collections.shuffle(special);
			Collections.shuffle(numbers);
			Collections.shuffle(alphabets);
			if(sb.length() %3 == 0) {
				sb.append(special.get(0));
			} else if(sb.length() % 2 == 0) {	
				sb.append(alphabets.get(0));
			} else if(sb.length() == 1) {
				sb.append(Character.toUpperCase(alphabets.get(0)));
			} else {
				sb.append(numbers.get(0));
			}
		}
		return sb.toString();
	}
	
	public static String generateAccoundId() {
		UUID uuid = UUID.randomUUID();
		String id = Math.abs(uuid.hashCode())+"";
		if(id.length() < 10) {
			id = id+id.substring(0,10-id.length());
		}
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println(generateAccoundId());
	}
}
