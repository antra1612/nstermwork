import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
public class SHA1 { 
	public static void main(String args[]) throws NoSuchAlgorithmException  { 
		String message = "Ankita"; 
		System.out.println("HashCode:" + getMessagedigest(message)); 
		} 
	
	public static String getMessagedigest(String message) 
	{ 
	try { 
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] messageDigest = md.digest(message.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 
        catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	} 
}
/*Output:
D:\Semester-5-\Network security\Assignment 1>javac SHA1.java

D:\Semester-5-\Network security\Assignment 1>java SHA1
HashCode:7aa4ca80d0edd35cb05f88b5a7bd4cf228af3f21*/

 

    