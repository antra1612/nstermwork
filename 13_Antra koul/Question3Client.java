/*3.	Implement a firewall that behaves like forwarder. It does not forward the packet that contains the word "terrorist".*/
import java.net.InetAddress;
import java.util.Scanner;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
class Question3Client 
{ 
    public static void main(String args[]) throws IOException 
    { 
		DatagramSocket client = new DatagramSocket();
		DatagramPacket packet = null;
		InetAddress ip = InetAddress.getLocalHost();
		byte[] packetBytes = new byte[65536];
		Scanner in = new Scanner(System.in);
		String message="";
		while(!message.equalsIgnoreCase("bye")){
			System.out.print("\nClient:");
			message = in.nextLine();
			if(message.equalsIgnoreCase("bye"))
			{
				System.out.print("\nConversation ended!");
				break;
			}
			packetBytes = message.getBytes();
			packet = new DatagramPacket(packetBytes,packetBytes.length,ip,1234);
			client.send(packet);
			packetBytes = new byte[65536];
			packet = new DatagramPacket(packetBytes,packetBytes.length);
			client.receive(packet);
			System.out.print("Server:"+convertToString(packetBytes));
		}
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