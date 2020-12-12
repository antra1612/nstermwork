
 
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
 import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
 

import java.util.Base64;
 
public class DESString {
 
    private static Cipher ecipher;
    private static Cipher dcipher;
    private static SecretKey key;
    public static void main(String[] args) {
		try {
			// generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();
			FileOutputStream fileOutputStream = new FileOutputStream(new File("ReceiverKey.txt"));
			fileOutputStream.write(key.getEncoded());
			ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
			// initialize the ciphers with the given key
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
			String original="the quick brown fox jumps over a lazy dog!!";
			String encrypted = encrypt(original);
			System.out.print("Original text:"+original);
			System.out.println("\nEncrypted: " + encrypted);
			String decrypted = decrypt(encrypted);
			System.out.println("\nDecrypted: " + decrypted);
		}
        catch (Exception e) {
            System.out.println("Exception occured!" + e.getMessage());
            return;
        }
 }
	public static String encrypt(String str) {
	try {
			// encode the string into a sequence of bytes using the named charset
			byte[] string_bytes= str.getBytes("UTF8");
			// storing the result into a new byte array. 
			byte[] enc = ecipher.doFinal(string_bytes);
			// encode to base64
			enc = Base64.getEncoder().encode(enc);
			return new String(enc);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
 public static String decrypt(String str) {
	try {
	// decode with base64 to get bytes
	byte[] dec = Base64.getDecoder().decode(str.getBytes());
	byte[] utf8 = dcipher.doFinal(dec);
	return new String(utf8, "UTF8");
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
}
/*Output:
D:\Semester-5-\Network security\Assignment 1>javac DESString.java

D:\Semester-5-\Network security\Assignment 1>java DESString
Original text:the quick brown fox jumps over a lazy dog!!
Encrypted: OjizH7l/ytY90hUHfnmBG3GNDavJKuke2AZBmq/6MRY1PozpF6MxpZij9fpEDJFc

Decrypted: the quick brown fox jumps over a lazy dog!!*/