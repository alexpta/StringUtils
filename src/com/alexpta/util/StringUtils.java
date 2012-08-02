package com.alexpta.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtils {

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}
	
	public static String[] splitToLine(String str, int lineLimit) {
		String delims = " .,:;!?";
		StringTokenizer tokenizer = new StringTokenizer(str, delims, true);
		List<StringBuilder> words = new ArrayList<StringBuilder>();
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(!" ".equals(token)) {
				if(delims.indexOf(token) > -1) {
					words.get(words.size() - 1).append(token);
				}
				else {
					words.add(new StringBuilder(token));
				}
			}
		}
		
//		for(StringBuilder word : words) {
//			System.out.println(">" + word.toString() + "<");
//		}
		
		List<String> lines = new ArrayList<String>();
		String line = null;
		for(int i = 0; i < words.size(); i++) {
			if(line == null) line = "";
			String word = words.get(i).toString();
			if((line + word).length() > lineLimit - 1) {
				if(line.length() > 0) {
					lines.add(line);
					line = word;
				}
				else {
					while(word.length() > lineLimit) {
						lines.add(word.substring(0, lineLimit));
						word = word.substring(lineLimit);
					}
					line = word;
				}
			}
			else {
				if(line.length() > 0) line += " ";
				line += word;
			}
		}
		if(isNotEmpty(line)) {
			lines.add(line);
		}
		
		String[] output = new String[lines.size()];
		
		return (String[])lines.toArray(output);
	}
	
	
	// for test purpose
	public static void main(String args[]) {
		String str = "Carfox Group OÜ, Kaarel Kuik võlgnevuse tasumine, kreeditor Danske Bank A/S Eesti filiaal ";
		String lines[] = splitToLine(str, 60);
		for(int i = 0; i < lines.length; i++) {
			System.out.println(lines[i] + "/" + lines[i].length());
		}
	}
}
