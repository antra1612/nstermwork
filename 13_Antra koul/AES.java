import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
class AES{
	static String Iv="AAAAAAAAAAAAAAAA";
	static String plain_text="test text 123\0\0\0";
	static String key= "0123456789abcdef";
	public static void main(String args[]){
		try{
			System.out.print("\nPlain text:"+plain_text);
			
			byte[] encrypted_text=encrypt(plain_text,key);
			
			System.out.print("\nEncrypted text:");
			for(int i=0;i<encrypted_text.length;i++){
				System.out.print(new Integer(encrypted_text[i]+""));
				
			}
			
			String decrypted_text=decrypt(encrypted_text,key);
			
			System.out.print("\nDecrypted text:"+decrypted_text);
		}
		catch(Throwable e){
			e.printStackTrace();
		}
	}
	public static byte[] encrypt(String plain_text,String key)throws Throwable{
		Cipher cipher=Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec secret_key_spec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
		cipher.init(Cipher.ENCRYPT_MODE,secret_key_spec,new IvParameterSpec(Iv.getBytes("UTF-8")));
		return cipher.doFinal(plain_text.getBytes());
	}
	public static String decrypt(byte[] cipher_text,String key)throws Throwable{
		Cipher cipher=Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec secret_key_spec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
		cipher.init(Cipher.DECRYPT_MODE,secret_key_spec,new IvParameterSpec(Iv.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipher_text),"UTF-8");
	}
}
/*Output:
C:\Users\user\Desktop\Sem 5\Semester-5-\Network security\AES>javac AES.java
Note: AES.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.

C:\Users\user\Desktop\Sem 5\Semester-5-\Network security\AES>java AES

Plain text:test text 123   
Encrypted text:16-12441-83-16-12361-64-15-74872863306478
Decrypted text:test text 123*/