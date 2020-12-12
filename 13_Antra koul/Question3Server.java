/*1.	Implement authentication Service. The sender sends password in encrypted format to the receiver, the receiver checks 
the password (after decrypting) in its database/array and replies back as success or failure.(Keys are already shared)*/
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;

class Question3Server
{ 
    public static void main(String args[]) throws IOException 
    { 
		DatagramSocket server = new DatagramSocket(1111);
		byte[] packetBytes = new byte[65536];
		String message="";
		DatagramPacket packet=null;
		while(!message.equalsIgnoreCase("bye")){
			packet=new DatagramPacket(packetBytes,packetBytes.length);
			server.receive(packet);
			message=convertToString(packetBytes);
			System.out.print("\nClient:"+message);
			if(message.equalsIgnoreCase("bye")){
				System.out.print("\n Connection terminated!");
				break;
			}
		   packetBytes = new byte[65536];
		}
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
}
/*Output:
PS D:\Semester-5-\Network security\Assignment 2> javac Question3Client.java
PS D:\Semester-5-\Network security\Assignment 2> java Question3Client

Client:Hello
Server:sent!
Client:How are you?
Server:sent!
Client:terrorist
Server:not sent!
Client:Hey again?
Server:sent!
Client:

PS D:\Semester-5-\Network security\Assignment 2> javac Question3Firewall.java
PS D:\Semester-5-\Network security\Assignment 2> java Question3Firewall

Client:
Client:Hello
Client:How are you?Can't be forwarded!

Client:terrorist

PS D:\Semester-5-\Network security\Assignment 2> javac Question3Server.java
PS D:\Semester-5-\Network security\Assignment 2> java Question3Server

Client:Hello
Client:How are you?
Client:Hey again?*/
