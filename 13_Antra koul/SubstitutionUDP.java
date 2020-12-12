import java.net.*;
import java.io.*;
import java.util.*;
class SubstitutionUDP
{
	public static void main(String args[])throws Exception 
	{
		DatagramSocket datagram_socket = new DatagramSocket();
		
		DatagramPacket datagram_packet = null;
		
		InetAddress ip = InetAddress.getLocalHost();
		
		Scanner in = new Scanner(System.in);
		
		byte[] send_packet = new byte[65535];
		
		byte[] receive_packet = new byte[65535];
		
		BufferedReader br = new BufferedReader(new FileReader(new File("SubstitutionKey.txt")));
		int key=Integer.parseInt(br.readLine());
		
		while(true)
		{
			String message_string ;
			
			System.out.print("\nClient:");
			message_string= in.nextLine();
			
			send_packet = message_string.getBytes();
			
			datagram_packet = new DatagramPacket(send_packet,send_packet.length,ip,1234);
			
			datagram_socket.send(datagram_packet);
			
			datagram_packet = new DatagramPacket(receive_packet,receive_packet.length);
			
			datagram_socket.receive(datagram_packet);
			
			message_string =  convert_to_string(receive_packet);
			
			System.out.print("Server : "+message_string);
			
			System.out.print("\nDecrypted Text:"+decryptText(message_string,key));

			if(message_string.equals("EXIT") || message_string.equals("BYE"))
			{
				System.out.print("Client exiting.............");
				break;
			}
			send_packet = new byte[65535];
		
			receive_packet = new byte[65535];
			
		}
	}
	public static String convert_to_string(byte[] buffer)
	{
		if ( buffer == null )
		{
			System.out.print("\n No message sent !");
			return null;
		}
		String message = "";
		int i = 0;
		while ( buffer[i] != 0 )
		{
			message = message + (char)buffer[i];
			i++;
		}
		return message;
	}
	public static String decryptText(String plain_text,int key)
	{
		String encrypted_Text="";
		for(int i=0;i<plain_text.length();i++){
			if(((int)plain_text.charAt(i)) == 32)
				encrypted_Text=encrypted_Text+" ";
			else{
				int n=(((int)plain_text.charAt(i))-65+key);
				if(n>25)
					n=Math.abs(n)-26;
				encrypted_Text=encrypted_Text+((char)(n+65));
			}
		}
		return encrypted_Text;
	} 	
}