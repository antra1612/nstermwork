import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
class Question4Server2
{ 
    public static void main(String args[]) throws IOException 
    { 
		DatagramSocket server = new DatagramSocket(2222);
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
PS D:\Semester-5-\Network security\Assignment 2> javac Question4Sender.java
PS D:\Semester-5-\Network security\Assignment 2> java Question4Sender

Client:2

Client:23

Client:32

Client:11

Client:

PS D:\Semester-5-\Network security\Assignment 2> javac Question4NAT.java
PS D:\Semester-5-\Network security\Assignment 2> java Question4NAT

Client:2
Client:23
Client:32
Client:11

PS D:\Semester-5-\Network security\Assignment 2> javac Question4Server1.java
PS D:\Semester-5-\Network security\Assignment 2> java Question4Server1

Client:2
Client:32

PS D:\Semester-5-\Network security\Assignment 2> javac Question4Server2.java
PS D:\Semester-5-\Network security\Assignment 2> java Question4Server2

Client:23
Client:11*/