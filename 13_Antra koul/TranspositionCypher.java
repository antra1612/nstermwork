import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

class TranspositionCypher{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter the text to be encrypted:");
		String plain_text=in.nextLine();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("TranspositionKey.txt")));
			String key = br.readLine();
			
			TreeMap<String,String> encrypt_text = new TreeMap<String,String>();
			encrypt_text=insert_key(key,encrypt_text,plain_text);
			System.out.print("\nEncrypted text:");
			int len=printEncryptedText(encrypt_text);
			System.out.print("\nDecrypted text:");
			printDecryptedText(encrypt_text,key,len);
		}
		catch(Exception e){
			System.out.print("Exception occured!"+e.getMessage());
		}
	}
	public static void printDecryptedText(TreeMap<String,String> encrypt_text,String key,int length)
	{
		String str[] = key.split("");
		int counter=0,i=0;
		ArrayList<String> text = new ArrayList<String>();
		for(String s:str){
			text.add(encrypt_text.get(s));
		}
		while(counter<length)
		{
			for(int j=0;j<text.size();j++)
			{
				String s=text.get(j);
				if(i<s.length()){
					System.out.print(s.charAt(i));
					counter++;
				}
			}
			i++;
		}
	}
	public static int printEncryptedText(TreeMap<String,String> encrypt_text)
	{
		int count=0;
		Iterator itr =encrypt_text.entrySet().iterator(); 
		while (itr.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)itr.next(); 
			String str=mapElement.getValue().toString(); 
			System.out.print(str);
			count=count+str.length();
        } 
		return count;
	}
	public static TreeMap<String,String> insert_key(String key, TreeMap<String,String>encrypt_text,String plain_text)
	{
		String[] str = key.split("");
		String text="";
		int i,j,key_length,text_length;
		j=0;
		key_length=key.length();
		text_length=plain_text.length();
		for(String s:str){
			i=j;
			while(i<text_length){
				text=text+(plain_text.charAt(i));
				if(i+key_length < text_length){
					i+=key_length;
					}
				else
					i=text_length;
			}
			encrypt_text.put(s,text);
			j+=1;
			text="";
		}
		return encrypt_text;
	}
}
/*Output:
C:\Users\user\Desktop\Sem 5\Network security>javac TranspositionCypher.java

C:\Users\user\Desktop\Sem 5\Network security>java TranspositionCypher
Enter the text to be encrypted:please transfer one million dollars to my swiss bank account six two two

Encrypted text:as   wktosfmdti    rll sciwlanor autenenssnnwt llm cxoproiaybo eeioosast
Decrypted text:please transfer one million dollars to my swiss bank account six two two*/