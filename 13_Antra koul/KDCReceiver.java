import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Base64;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
 import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
 import javax.crypto.spec.SecretKeySpec;
class KDCReceiver{
	public static void main(String args[])throws Exception{
		DatagramSocket sender = new DatagramSocket(2222);
		DatagramPacket packet = null;
		byte[] message= new byte[65536];
		packet = new DatagramPacket(message,message.length);
		sender.receive(packet);
		String str = convertToString(message);
		
		BufferedReader br = new BufferedReader(new FileReader(new File("ReceiverKey.txt")));
		String senderKey=br.readLine();
		str=decryptData(str,senderKey);
		
		System.out.print("\nDecrypted :"+str);
	}
	public static String decryptData(String message,String receiverKey)throws Exception{
		SecretKey key;
		byte[] keyByte = receiverKey.getBytes();
		key = new SecretKeySpec(keyByte,0,keyByte.length,"DES");
		Cipher ecipher = Cipher.getInstance("DES");
		ecipher.init(Cipher.DECRYPT_MODE, key);
		byte[] dec=Base64.getDecoder().decode(message.getBytes());
		byte[] utf8 = ecipher.doFinal(dec);
		return new String(utf8, "UTF8");
	}
	public static String convertToString(byte[] a) 
    { 
        if (a == null) 
            return null; 
        String s = "";
        int i = 0; 
        while (a[i] != 0) 
        { 
            s=s+(char)a[i]; 
            i++; 
        } 
        return s; 
    }
}
/*Output:
D:\Semester-5-\Network security\Assignment 2>javac KDCSender.java

D:\Semester-5-\Network security\Assignment 2>java KDCSender

Server:mOdm96LvgHSxIevA/AfCz8CT64IxlWviLf/EgRWP/VECqDpw5fUZfA==


D:\Semester-5-\Network security\Assignment 2>java KDC

Message:mOdm96LvgHSxIevA/AfCz8CT64IxlWviLf/EgRWP/VECqDpw5fUZfA==

D:\Semester-5-\Network security\Assignment 2>javac KDCReceiver.java

D:\Semester-5-\Network security\Assignment 2>java KDCReceiver

Decrypted :Antra,Ankita,Pizza*/