package test;

import static org.junit.Assert.*;

import org.junit.Test;

import decoder.Base64Decoder;

public class Base64DecoderTester {

	@Test
	public void testConvertBase64Char(){
		assertEquals(0, Base64Decoder.convertBase64Char('A'));
		assertEquals(1, Base64Decoder.convertBase64Char('B'));
		assertEquals(4, Base64Decoder.convertBase64Char('E'));
		assertEquals(63, Base64Decoder.convertBase64Char('/'));
		assertEquals(27, Base64Decoder.convertBase64Char('b'));
		assertEquals(45, Base64Decoder.convertBase64Char('t'));
	}
	
	@Test
	public void testBase64ToByteArray() {
		byte[] answer = {0, 0, 0};
		byte[] check = Base64Decoder.base64ToByteArray("AAAA");
		//	000.000 000.000 000.000 000.000
		//	0000.0000 0000.0000 0000.0000 
		assertArrayEquals(answer, check);
		
		answer[0] = (byte) 255;
		answer[1] = (byte) 255;
		answer[2] = (byte) 255;
		check = Base64Decoder.base64ToByteArray("////");
		//	111.111 111.111 111.111 111.111
		//  1111.1111 1111.1111 1111.1111
		assertArrayEquals(answer, check);
		
		answer[0] = (byte) 7;
		answer[1] = (byte) 13;
		answer[2] = (byte) 126;
		check = Base64Decoder.base64ToByteArray("Bw1+");
		//	000.001 110.000 110.101 111.1110
		//  0000.0111 0000.1101. 0111.1110
		assertArrayEquals(answer, check);
	}

}
