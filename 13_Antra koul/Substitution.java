import java.util.Scanner;
import java.io.*;
class Substitution{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		String plain_text;
		try{
		BufferedReader br = new BufferedReader(new FileReader(new File("SubstitutionKey.txt")));
		int key=Integer.parseInt(br.readLine());
		
	    System.out.print("\nEnter text to be encrypted:");
		plain_text=in.nextLine();
		
		String encrypted_Text=encryptText(plain_text,key);
		
		System.out.print("\nEncrypted Text:"+encrypted_Text);
		
		System.out.print("\nDecrypted Text:"+decryptText(encrypted_Text,key));
		}
		catch(Exception e){
			e.printStackTrace();
		}
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
/*Output:
C:\Users\user\Desktop\Sem 5\Network security>javac Substitution.java

C:\Users\user\Desktop\Sem 5\Network security>java Substitution

Enter text to be encrypted:THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG

Encrypted Text:QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD
Decrypted Text:THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG
*/