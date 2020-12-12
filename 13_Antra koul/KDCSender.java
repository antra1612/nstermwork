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
class KDCSender{
	public static void main(String args[])throws Exception{
		DatagramSocket sender = new DatagramSocket();
		DatagramPacket packet = null;
		InetAddress ip = InetAddress.getLocalHost();
		String requestMessage = "Request for access to server!";
		byte[] message= new byte[65536];
		
		message=requestMessage.getBytes();
		packet = new DatagramPacket(message,message.length,ip,1234);
		sender.send(packet);
		
		message= new byte[65536];
		packet = new DatagramPacket(message,message.length);
		sender.receive(packet);
		
		String str = convertToString(message);
		System.out.print("\nServer:"+str);
		
		BufferedReader br = new BufferedReader(new FileReader(new File("SenderKey.txt")));
		String senderKey=br.readLine();
		
		String[] st=(decryptData(str,senderKey)).split(",");
		requestMessage=st[0];
		
		message= new byte[65536];
		message=requestMessage.getBytes();
		packet = new DatagramPacket(message,message.length,ip,2222);
		sender.send(packet);
		
		
	}
	public static String decryptData(String message,String senderKey)throws Exception{
		SecretKey key;
		byte[] keyByte = senderKey.getBytes();
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