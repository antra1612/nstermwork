import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAEncryption
{
    static String plainText = "My name is Antra!";
    
    public static void main(String[] args) throws Exception
    {
        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);
        
        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        System.out.println("Original Text  : "+plainText);
        
        // Encryption
        byte[] cipherTextArray = encrypt(plainText, publicKey);
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        System.out.println("Encrypted Text : "+encryptedText);
        
        // Decryption
        String decryptedText = decrypt(cipherTextArray, privateKey);
        System.out.println("DeCrypted Text : "+decryptedText);
    }
    
    public static byte[] encrypt (String plainText,PublicKey publicKey ) throws Exception
    {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
        
        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

        return cipherText;
    }
    
    public static String decrypt (byte[] cipherTextArray, PrivateKey privateKey) throws Exception
    {
        //Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING 
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
        
        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        //Perform Decryption
        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);
        
        return new String(decryptedTextArray);
    }
}
/*Output:
D:\Semester-5-\Network security\Assignment 1>javac RSAEncryption.java

D:\Semester-5-\Network security\Assignment 1>java RSAEncryption
Original Text  : My name is Antra!
Encrypted Text : jst++XKd8nZDkB/bbhLNfDN7IRHr63SBEvHp7hIxvUw0ACwq6Ckcv4zyh0x7hAmA72/SpdnkTCq8v+aV8CVV+OhhoUatPU6JxMg1yIHuoLNvzfLD5fGGKIrs8zJyWgXg24nzscMG3e1KNdo8HmDreF+iTvrLZh33JMgHhQ68KHns2/aHQkBVQYipEf2eqotJhsGZW7uBEHO2LcHyVCtPpwg8RSYzWo8eRtZ1L4lXlgn8F53lBuS8WNgjCUBf5iLGqx0njsYbccQjUMdh8JbEPMJVECOh2zlyyX8unugjQ0gLg0GSAD6ENKkkoRN5RwIojMF9kIIXovZF0OJopZW3WYZsnkx/NFrEGGTOGdpVW3kHIyGNtoYmZOJiO4d/CUgYiaK2OFdDYkvlVfhr7bwwuZooymcHSDKe89xiu0cKoZSHZA5IP0BFiaGcksa70rIH/Pzr/dRzvuM9+ZPX1aMaJKE5UtSIz44m2wxDoquvvlrEvLdWnJN2tz5I0c08eGlH2JDQV6iNng+YOCOe9AEkzb1xHCUvyNXGV6FlqeWjVd0fdagerXC1cMqdI5juCSG/gzs7IJjau0PlgsnCduLyy2u4lBQcmMct52ZwMCYJ5zmkSH+b2fuktbLaM4c2e9epD3TnvZaCxXprHQb378261B+w9w7UJVYP8tkV/j296jA=
DeCrypted Text : My name is Antra!*/