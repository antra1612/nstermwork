import java.util.Scanner;
import java.util.Random;

class OneTimePad{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		String plain_text;
		
		StringBuffer key=new StringBuffer();
		StringBuffer encrypted_text=new StringBuffer();
		StringBuffer decrypted_text=new StringBuffer();
		System.out.print("\nEnter plain text:");
		plain_text=in.nextLine();
		
		key=generateKey(plain_text);
		System.out.print("\nKey:"+key);
		
		encrypted_text=encryptText(plain_text,key);
		
		System.out.print("\nEncrypted text is :"+encrypted_text);
		
		decrypted_text=decryptText(encrypted_text,key);
		
		System.out.print("\nDecrypted text is :"+decrypted_text);
		
	}
	public static StringBuffer generateKey(String plain_text)
	{
		Random random = new Random();
		 StringBuffer key=new StringBuffer("");
		 for(int i=0;i<plain_text.length();i++)
		 {
			 char a=(char)(random.nextInt(128));
			 key.append((char)(a));
		 }
		 return key;
	}
	public static StringBuffer encryptText(String plain_text,StringBuffer key)
	{
		 StringBuffer encrypted_text=new StringBuffer("");
		for(int i=0;i<plain_text.length();i++)
		{
			encrypted_text.append((char)(((int)(plain_text.charAt(i)))^((int)(key.charAt(i)))));
		}
		return encrypted_text;
	}
	public static StringBuffer decryptText(StringBuffer encrypted_text,StringBuffer key)
	{
		 StringBuffer decrypted_text=new StringBuffer("");
		for(int i=0;i<encrypted_text.length();i++)
		{
			decrypted_text.append((char)(((int)(encrypted_text.charAt(i)))^((int)(key.charAt(i)))));
		}
		return decrypted_text;
	}
}
/*Output:
C:\Users\user\Desktop\Sem 5\Semester-5-\Network security>javac OneTimePad.java

C:\Users\user\Desktop\Sem 5\Semester-5-\Network security>java OneTimePad

Enter plain text:Antra koul

Key:><"~#Xx
C^eL-ted text is :NPH
Decrypted text is :Antra koul
*/