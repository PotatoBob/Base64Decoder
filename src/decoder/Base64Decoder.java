package decoder;

import java.io.BufferedReader;
import java.io.FileReader;

public class Base64Decoder {
	final static char[] base64Chars = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};
	
	public static byte convertBase64Char(char c){
		byte convertedChar = -1;
		
		for (int i = 0; i < base64Chars.length; i++) {
			if (base64Chars[i] == c)
				convertedChar = (byte) i;
		}
		
		return convertedChar;
	}
	
	public static byte[] base64ToByteArray(String s){
		byte[] decodedBytes = new byte[3];
		
		char[] sChars = new char[4];
		sChars = s.toCharArray();
		
		decodedBytes[0] = (byte) ((convertBase64Char(sChars[0])<<2)+(convertBase64Char(sChars[1])>>4));
		decodedBytes[1] = (byte) ((convertBase64Char(sChars[1])<<4)+(convertBase64Char(sChars[2])>>2));
		decodedBytes[2] = (byte) ((convertBase64Char(sChars[2])<<6)+(convertBase64Char(sChars[3])));
		base64LongStringToByteArray("base64_data.txt");
		return decodedBytes;
	}
	public static byte[] base64LongStringToByteArray(String file) {
		String fileStuff = ""; //contents of the file
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			while(line != null) {
				fileStuff += line;
				
				line = br.readLine();
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		byte[] decodedBytes = new byte[fileStuff.length()*3/4];
		char[] fileStuffChars = fileStuff.toCharArray();
		
		for (int i = 0; i < fileStuffChars.length/4; i++) {
			decodedBytes[3*i] = (byte) ((convertBase64Char(fileStuffChars[4*i])<<2)+(convertBase64Char(fileStuffChars[4*i+1])>>4));
			decodedBytes[3*i+1] = (byte) ((convertBase64Char(fileStuffChars[4*i+1])<<4)+(convertBase64Char(fileStuffChars[4*i+2])>>2));
			decodedBytes[3*i+2] = (byte) ((convertBase64Char(fileStuffChars[4*i+2])<<6)+(convertBase64Char(fileStuffChars[4*i+3])));
		}
		for(int i = 0; i < decodedBytes.length; i++) {
			System.out.println(decodedBytes[i]);
		}
		return decodedBytes;
	}
}
