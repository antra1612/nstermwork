import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
class Question4NAT
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
			message=convertToString(packetBytes);
			System.out.print("\nClient:"+message);
			if(message.equalsIgnoreCase("bye")){
				System.out.print("\n Connection terminated!");
				break;
			}
			status=check(message);
			ip=InetAddress.getLocalHost();
			packetBytes=message.getBytes();
			if(status.equals("even")){
				packet=new DatagramPacket(packetBytes,packetBytes.length,ip,1111);
				server.send(packet);
			}
			else if(status.equals("odd"))
			{
				packet=new DatagramPacket(packetBytes,packetBytes.length,ip,2222);
				server.send(packet);
			}
			else{
				System.out.print("\n Invalid message!");
			}
		   packetBytes = new byte[65536];
		}
	}
	//authoriziing function
	public static String check(String message){
		if((Integer.parseInt(message)) % 2 == 0)
			return "even";
		else if((Integer.parseInt(message)) % 2 != 0)
			return "odd";
		else
			return "failure!";
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

