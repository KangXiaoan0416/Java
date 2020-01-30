package pers.xiaoan.common.util.aes;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author: xiaoan
 * @createDate: 2019/12/30 22:47
 */
public class AesUtil {

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            getKeyByPass();
        }
    }

    public static void getKeyByPass() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, new SecureRandom());
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s + " : " + s.length());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }


    /**
     *
     * @param bytes aaa
     * @author: xiaoan
     * @createDate: 2019/12/30
     * @return: java.lang.String
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String strHex = Integer.toHexString(aByte);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0").append(strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }
}
