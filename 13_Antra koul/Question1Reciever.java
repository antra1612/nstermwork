/*1.	Implement authentication Service. The sender sends password in encrypted format to the receiver, the receiver checks 
the password (after decrypting) in its database/array and replies back as success or failure.(Keys are already shared)*/
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;

class Question1Reciever
{ 
	static HashMap<String,String> user_data = new HashMap<String,String>();
    public static void main(String args[]) throws IOException 
    { 
		user_data.put("ANTRA","ANTRA");
	    user_data.put("ANKITA","ANKITA");
		DatagramSocket server = new DatagramSocket(1234);
		byte[] send = new byte[65536];
		byte[] recieve = new byte[65536];
		DatagramPacket packet = new DatagramPacket(recieve,recieve.length);
		server.receive(packet);
		String[] message=(convertToString(recieve)).split(",");
		String username=message[0];
		String password=decryptPassword(message[1]);
		InetAddress ip = packet.getAddress();
		int port=packet.getPort();
		System.out.print("Username"+username+"Password"+password);
		String status=check(password,username);
		packet=new DatagramPacket(status.getBytes(),status.getBytes().length,ip,port);
		server.send(packet);
	}
	//authoriziing function
	public static String check(String password,String username){
		if(password.equals((String)user_data.get(username))){
			return "success";
		}
		return "failure";
	}
	//converting bytes to string
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
	//decrypting password
	public static String decryptPassword(String password){
		char[] tokens = password.toCharArray();
		String decrypted_text="";
		for(char c:tokens){
			decrypted_text=decrypted_text+((char)((int)c-3))+"";
		}
	 return decrypted_text;
	}
}
/*Output:
PS D:\Semester-5-\Network security\Assignment 2> javac Question1Sender.java
PS D:\Semester-5-\Network security\Assignment 2> java Question1Sender

Enter username:ANKITA

Enter password:ANKITA
Server:success
PS D:\Semester-5-\Network security\Assignment 2> javac Question1Sender.java
PS D:\Semester-5-\Network security\Assignment 2> java Question1Sender

Enter username:ABC

Enter password:ABC
Server:failure

PS D:\Semester-5-\Network security\Assignment 2> javac Question2Reciever.java
PS D:\Semester-5-\Network security\Assignment 2> java Question2Reciever
UsernameANKITAPasswordANKITA
PS D:\Semester-5-\Network security\Assignment 2> javac Question2Reciever.java
PS D:\Semester-5-\Network security\Assignment 2> java Question2Reciever
UsernameABCPasswordABC*/
