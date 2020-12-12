import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
class DES{
	public static void main(String args[]){
		try{
			String key="antra123";
			FileInputStream fis = new FileInputStream("OriginalText.txt");
			FileOutputStream fos = new FileOutputStream("EncryptedText.txt");
			encryptText(fis,fos,key);
			
			 fis = new FileInputStream("EncryptedText.txt");
			fos = new FileOutputStream("DecryptedText.txt");
			decryptText(fis,fos,key);
		}
		catch(Throwable e){
			System.out.print("Exception occured!"+e.getMessage());
		}
	}
	public static void encryptText(FileInputStream is,FileOutputStream os,String key)throws Throwable{
		encryptDecryptData(is,os,Cipher.ENCRYPT_MODE,key);
	}
	public static void decryptText(FileInputStream is,FileOutputStream os,String key)throws Throwable{
		encryptDecryptData(is,os,Cipher.DECRYPT_MODE,key);
	}
	public static void encryptDecryptData(FileInputStream is,FileOutputStream os,int mode,String key)throws Throwable{
		DESKeySpec key_spec = new DESKeySpec(key.getBytes());
		Cipher cipher =Cipher.getInstance("DES");
		SecretKeyFactory secret_key_factory = SecretKeyFactory.getInstance("DES");
		SecretKey secret_key=secret_key_factory.generateSecret(key_spec);
		
		if(mode == Cipher.ENCRYPT_MODE){
			cipher.init(Cipher.ENCRYPT_MODE,secret_key);
			CipherInputStream cis = new CipherInputStream(is,cipher);
			doCopy(cis,os);
		}
		else
		{
			cipher.init(Cipher.DECRYPT_MODE,secret_key);
			CipherOutputStream cos = new CipherOutputStream(os,cipher);
			doCopy(is,cos);
		}
	}
	public static void doCopy(InputStream is,OutputStream os)throws Throwable{
		byte[] buffer=new byte[64];
		int data;
		while((data=is.read(buffer)) != -1){
			os.write(buffer,0,data);
		}
	}
}
