/*1.	Implement authentication Service. The sender sends password in encrypted format to the receiver, the receiver checks 
the password (after decrypting) in its database/array and replies back as success or failure.(Keys are already shared)*/
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;

class Question3Firewall
{ 
    public static void main(String args[]) throws IOException 
    { 
		DatagramSocket server = new DatagramSocket(1234);
		byte[] packetBytes = new byte[65536];
		String message="",status;
		InetAddress ip;
		int port;
		DatagramPacket packet=null;
		while(!message.equalsIgnoreCase("bye")){
			packet=new DatagramPacket(packetBytes,packetBytes.length);
			server.receive(packet);
			System.out.print("\nClient:"+message);
			message=convertToString(packetBytes);
			if(message.equalsIgnoreCase("bye")){
				System.out.print("\n Connection terminated!");
				break;
			}
			status=check(message);
			if(status.equals("not sent!")){
				ip = packet.getAddress();
				port=packet.getPort();
				packetBytes=status.getBytes();
				packet=new DatagramPacket(packetBytes,packetBytes.length,ip,port);
				server.send(packet);
			}
			else
			{
				
				ip = packet.getAddress();
				port=packet.getPort();
				packetBytes=status.getBytes();
				packet=new DatagramPacket(packetBytes,packetBytes.length,ip,port);
				server.send(packet);
				packetBytes = new byte[65536];
				ip=InetAddress.getLocalHost();
				packetBytes=message.getBytes();
				packet=new DatagramPacket(packetBytes,packetBytes.length,ip,1111);
				server.send(packet);
			}
		   packetBytes = new byte[65536];
		}
	}
	//authoriziing function
	public static String check(String message){
		String[] tokens = message.split(" ");
		for(String s:tokens){
			if(s.equalsIgnoreCase("terrorist"))
			{
				System.out.println("Can't be forwarded!");
				return "not sent!";
			}
		}
		return "sent!";
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

