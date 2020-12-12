import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
class Question4Server1
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