import java.io.*;
import java.net.*;
import java.util.*;
class SubstitutionServer
{
	public static void main(String args[])throws Exception
	{
		DatagramSocket datagram_socket = new DatagramSocket(1234);
		
		InetAddress ip = InetAddress.getLocalHost();
		
		byte[] recieve_packet = new byte[65535];
		
		byte[] send_packet = new byte[65535];
		
		DatagramPacket datagram_packet = null;
		
		BufferedReader br = new BufferedReader(new FileReader(new File("SubstitutionKey.txt")));
		int key=Integer.parseInt(br.readLine());
		
		while(true)
		{
			datagram_packet = new DatagramPacket(recieve_packet , recieve_packet.length);
			
			datagram_socket.receive(datagram_packet);
			
			String message = convert_to_String(recieve_packet);
			
			System.out.print("\nClient's Plain text:"+message);
			
			message=encryptText(message,key);
			
			System.out.print("\nServer Encrypted text:"+message);
			
			send_packet = message.getBytes();
			
			ip = datagram_packet.getAddress();
			
			int port = datagram_packet.getPort();
			
		    datagram_packet = new DatagramPacket(send_packet, send_packet.length , ip , port);
			
			datagram_socket.send(datagram_packet);
			
			if(message.equals("EXIT") || message.equals("BYE"))
			{
				System.out.print("\n Client exiting!");
				datagram_socket.close();
				break;
			}
			
			recieve_packet = new byte[65535];
		
			send_packet = new byte[65535];
		}
	}
	public static String convert_to_String(byte[] buf)
	{
		if(buf == null)
		{
			System.out.print("\n No message recieved !");
			return null;
		}
		String message = "";
		int i = 0;
		while ( buf[i] != 0 )
		{
			message = message + (char)buf[i];
			i++;
		}
	    return message;
	}
	public static String encryptText(String plain_text,int key)
	{
		String encrypted_Text="";
		for(int i=0;i<plain_text.length();i++){
			if(((int)plain_text.charAt(i)) == 32)
				encrypted_Text=encrypted_Text+" ";
			else{
				int n=(((int)plain_text.charAt(i))-65-key);
				if(n<0)
					n=26-Math.abs(n);
				encrypted_Text=encrypted_Text+((char)(n+65));
			}
		}
		return encrypted_Text;
	} 	
}
/*Output:
C:\Users\user\Desktop\Sem 5\Network security>javac SubstitutionServer.java

C:\Users\user\Desktop\Sem 5\Network security>java SubstitutionServer

Client's Plain text:THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG
Server Encrypted text:QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD

C:\Users\user\Desktop\Sem 5\Network security>javac SubstitutionUDP.java

C:\Users\user\Desktop\Sem 5\Network security>java SubstitutionUDP

Client:THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG
Server : QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD
Decrypted Text:THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG
Client:

*/
