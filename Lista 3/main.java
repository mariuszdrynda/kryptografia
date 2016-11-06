import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
public class main {
	static String keystorePass;
	static String keystoreLocation = "C:/Program Files (x86)/Java/jre1.8.0_91/bin/aes-keystore.jck";//sciezka do keystora
	static String keyPass;
	static String alias = "jcek";
	public static SecretKeySpec getKeyFromKeyStore(String keystorePass, String keystoreLocation, String keyPass, String alias) throws Exception{
		InputStream keystoreStream = new FileInputStream(keystoreLocation);
		KeyStore keystore = KeyStore.getInstance("JCEKS");
		keystore.load(keystoreStream, keystorePass.toCharArray());
		if (!keystore.containsAlias(alias)) throw new RuntimeException("Alias for key not found");
		SecretKeySpec key =(SecretKeySpec) keystore.getKey(alias, keyPass.toCharArray());
		return key;
	}
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
    public static String encrypt(String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
            //SecretKeySpec skeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");
            SecretKeySpec skeySpec = getKeyFromKeyStore(keystorePass, keystoreLocation, keyPass, alias);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            
            byte[] encrypted;
            try{
            	encrypted = cipher.doFinal(value.getBytes());;}
            catch(BadPaddingException al){
            	return new String("");
            }
            return new String(Base64.encodeBase64String(encrypted));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
            //SecretKeySpec skeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");
            SecretKeySpec skeySpec = getKeyFromKeyStore(keystorePass, keystoreLocation, keyPass, alias);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            
            byte[] original;
            try{
            	original = cipher.doFinal(Base64.decodeBase64(encrypted));}
            catch(BadPaddingException al){
            	return new String("");
            }
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String encryptFile(String initVector, String tryb) {
    	int bytesRead;
    	try {
	    	FileInputStream inputFile = new FileInputStream(("C:/Users/Mariusz/Documents/Billy Jean.mp3"));
	    	FileOutputStream outputFile = new FileOutputStream("C:/Users/Mariusz/Documents/Zapisane");
        
            IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
            SecretKeySpec skeySpec = getKeyFromKeyStore(keystorePass, keystoreLocation, keyPass, alias);

            Cipher cipher = null;
            if(tryb=="CBC"){
            	cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            }
            if(tryb=="CTR"){
            	cipher = Cipher.getInstance("AES/CTR/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            }
            if(tryb=="ECB"){
            	cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            }
            if(tryb=="GCM"){
	            byte[] ivBytes = new byte[256 / Byte.SIZE];
		    	GCMParameterSpec gcmSpecWithIV = new GCMParameterSpec(128, ivBytes);
		    	cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, gcmSpecWithIV);
            }
            
            byte [] tab = new byte[64];
            while ((bytesRead = inputFile.read(tab)) != -1) {
            	byte[] outtab = cipher.update(tab, 0, bytesRead);
            	if (outtab != null) outputFile.write(outtab);
            }
            byte [] tab2 = cipher.doFinal();//tab2 to resztki ktorych nie wybrala petla
    		outputFile.write(tab2);
        	
            //cipher.update(tab);
            inputFile.close();
            outputFile.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String encryptFile2(String initVector, String tryb, String key) {
    	int bytesRead;
    	try {
	    	FileInputStream inputFile = new FileInputStream(("C:/Users/Mariusz/Documents/ee/konfiguracja.txt"));
	    	FileOutputStream outputFile = new FileOutputStream("C:/Users/Mariusz/Documents/konfiguracja");
        
            IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
            SecretKeySpec skeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");

            Cipher cipher = null;
            if(tryb=="CBC"){
            	cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            }
            if(tryb=="CTR"){
            	cipher = Cipher.getInstance("AES/CTR/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            }
            if(tryb=="ECB"){
            	cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            }
            if(tryb=="GCM"){
	            byte[] ivBytes = new byte[256 / Byte.SIZE];
		    	GCMParameterSpec gcmSpecWithIV = new GCMParameterSpec(128, ivBytes);
		    	cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, gcmSpecWithIV);
            }
            
            byte [] tab = new byte[64];
            while ((bytesRead = inputFile.read(tab)) != -1) {
            	byte[] outtab = cipher.update(tab, 0, bytesRead);
            	if (outtab != null) outputFile.write(outtab);
            }
            byte [] tab2 = cipher.doFinal();
    		outputFile.write(tab2);
        	
            //cipher.update(tab);
            inputFile.close();
            outputFile.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String decryptFile(String initVector, String tryb) {
    	int bytesRead;
    	try {
	    	FileInputStream inputFile = new FileInputStream(("C:/Users/Mariusz/Documents/Zapisane"));
	    	FileOutputStream outputFile = new FileOutputStream("C:/Users/Mariusz/Documents/Billy Jean WYNIK.mp3");
	    	
            IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
            SecretKeySpec skeySpec = getKeyFromKeyStore(keystorePass, keystoreLocation, keyPass, alias);

            Cipher cipher = null;
            if(tryb=="CBC"){
            	cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            }
            if(tryb=="CTR"){
            	cipher = Cipher.getInstance("AES/CTR/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            }
            if(tryb=="ECB"){
            	cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            }
            if(tryb=="GCM"){
	            byte[] ivBytes = new byte[256 / Byte.SIZE];
		    	GCMParameterSpec gcmSpecWithIV = new GCMParameterSpec(128, ivBytes);
		    	cipher = Cipher.getInstance("AES/GCM/NoPadding");
        		cipher.init(Cipher.DECRYPT_MODE, skeySpec, gcmSpecWithIV);
            }
            
            byte [] tab = new byte[64];
            while ((bytesRead = inputFile.read(tab)) != -1) {
            	byte[] outtab = cipher.update(tab, 0, bytesRead);
            	if (outtab != null) outputFile.write(outtab);
            }
            byte [] tab2 = cipher.doFinal();
    		outputFile.write(tab2);
    		
    		//cipher.update(tab);
            inputFile.close();
            outputFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
    	
    	Console console = System.console();
		System.out.print("Keystore pwd: ");
		String keystorePass = String.valueOf(console.readPassword(""));
		System.out.println();
		System.out.print("Key pwd: ");
		String keyPass = String.valueOf(console.readPassword(""));
		System.out.println();
    	
        String initVector = "e5ac57fb0175883a2496e6845aba3a35"; 
        String tryb = "CBC";
        String key = "12345678909999999999999999999999";
        //System.out.println(decrypt(initVector, encrypt(initVector, "Ala ma kota. Kot ma, ale... to jednak ona go posiada. Jednak¿e gdy przeczytamy to ponownie to...")));
        //encryptFile(initVector, tryb);
        //decryptFile(initVector, tryb);
		//encryptFile2(initVector, tryb, key);
    }
}