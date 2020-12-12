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
class KDC{
	public static void main(String args[])throws Exception{
		DatagramSocket kdc = new DatagramSocket(1234);
		DatagramPacket packet = null;
		InetAddress ip ;
		byte[] messageBytes=new byte[65536];
		String sender="Antra";
		String receiver="Ankita";
		String sessionKey="Pizza";
		String message=sender+","+receiver+","+sessionKey;
		BufferedReader br = new BufferedReader(new FileReader(new File("ReceiverKey.txt")));
		String receiverKey=br.readLine();
		
		message=encryptData(message,receiverKey);
		
		br = new BufferedReader(new FileReader(new File("SenderKey.txt")));
		String senderKey = br.readLine();
		
		message=encryptData(message+","+sessionKey,senderKey);
		System.out.print("\nMessage:"+message);
		packet = new DatagramPacket(messageBytes,messageBytes.length);
		kdc.receive(packet);
		String sender_request=convertToString(messageBytes);
		
		if(sender_request.equals("Request for access to server!")){
			messageBytes=new byte[65536];
			messageBytes=message.getBytes();
			packet = new DatagramPacket(messageBytes,messageBytes.length,packet.getAddress(),packet.getPort());
			kdc.send(packet);
		}
	}
	public static String encryptData(String message,String receiverKey)throws Exception{
		SecretKey key;
		byte[] keyByte = receiverKey.getBytes();
		key = new SecretKeySpec(keyByte,0,keyByte.length,"DES");
		Cipher ecipher = Cipher.getInstance("DES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] string_bytes=message.getBytes("UTF-8");
		byte[] encoded = ecipher.doFinal(string_bytes);
		encoded = Base64.getEncoder().encode(encoded);
		return new String(encoded);
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
