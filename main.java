import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
public class main {
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
            SecretKeySpec skeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            
            byte[] original;
            try{
            original = cipher.doFinal(Base64.decodeBase64(encrypted));}
            catch(BadPaddingException al){
            	return new String("");
            }

            //return new String(original);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
    	String[] tab = {"0", "1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
        String wynik = "";
        String prefix = "";
        String key = "";
        String sufix = "5d49b1a85b1339729c3ceeb35e401b633a80701ba5fee7f72f13c56a";
        String initVector = "249f807c6682f6b5935b82e18f7b7ed7";
        String code = "gHvSUyF7iUB6S8BORJqHTZGi5tgZgzT5jfe6vDC6u/XxQRCvN3exBIIBDRBny7AaqtfvxgSZ6sNwbcICp1M6hzWQx88UH6/8TrPqAEGTlODkP1LYvqH32hRJSgpUnXERrJsRjCQbeKQdjhcxrgaf4XR94TIRPMKT6CazYWmFyYxk7F/mkBozfh1PX/Je39Yk";
        for (int i=0;i<16;i++){
        	prefix=prefix+tab[i];
        	for (int j=0;j<16;j++){
        		prefix=prefix+tab[j];
        		for (int k=0;k<16;k++){
        			prefix=prefix+tab[k];
        			for (int l=0;l<16;l++){
        				prefix=prefix+tab[l];
        				for (int m=0;m<16;m++){
        					prefix=prefix+tab[m];
        					for (int n=0;n<16;n++){
        						prefix=prefix+tab[n];
        						for (int o=0;o<16;o++){
        							prefix=prefix+tab[o];
        							for (int p=0;p<16;p++){
        								prefix=prefix+tab[p];
        					        	key = prefix + sufix;
        					        	//System.out.println(key);
										wynik = decrypt(key, initVector, code);
        					        	if(wynik.length()>0) if(czysmiec(wynik)) System.out.println(wynik);
        					        	prefix= prefix.substring(0, prefix.length()-1);
        					        }
        							prefix= prefix.substring(0, prefix.length()-1);
        				        }
        						prefix= prefix.substring(0, prefix.length()-1);
        			        }
        					prefix= prefix.substring(0, prefix.length()-1);
        		        }		
        				prefix= prefix.substring(0, prefix.length()-1);
        	        }	
        			prefix= prefix.substring(0, prefix.length()-1);
                }	
        		prefix= prefix.substring(0, prefix.length()-1);
            }
        	prefix= prefix.substring(0, prefix.length()-1);
        }
    }
	private static boolean czysmiec(String wynik) {
		char znak;
		int a=0;
		if(wynik.length()<10) return false;
		for(int i=0;i<10;i++){
			znak = wynik.charAt(i);
			if(znak=='a'||znak=='b'||znak=='c'||znak=='d'||znak=='e'||znak=='f'||znak=='g'||znak=='h'
					||znak=='i'||znak=='j'||znak=='k'||znak=='l'||znak=='m'||znak=='n'||znak=='o'
					||znak=='p'||znak=='q'||znak=='r'||znak=='s'||znak=='t'||znak=='u'||znak=='v'
					||znak=='w'||znak=='x'||znak=='y'||znak=='z'){//TODO
				a++;
			}
		}
		if(a>6) return true;
		else return false;
	}
}