package com.nanking.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;
import java.security.*;

public class StringUtil {



    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    private static final String password = "yuwxzhjbxz23asd";

    //private static final SecretKeySpec key;


    /**
     * 生成指定长度的随机字符串
     *
     * @param len
     * @return
     */
    public static String generateRandomString(int len) {
        //定义一个字符串（A-Z，a-z）即52位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM";
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < len; ++i) {
            //产生0-61的数字
            int number = random.nextInt(52);
            //将产生的数字通过len次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static String stringToStringTime(String time) {
        Calendar calendar = Calendar.getInstance();
        Long longTime = Long.parseLong(time);
        calendar.setTimeInMillis(longTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(calendar.getTime());
    }


//    public static  String encryptedPasswordBySha256(String password) {
//        String encryPassword = DigestUtils.sha256Hex(password);
//        return encryPassword;
//    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");
            SecureRandom sr = new SecureRandom();
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password),sr);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密
            //return new String(result);
            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception ex) {
            //Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecureRandom sr = new SecureRandom();
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password),sr);

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result);
        } catch (Exception ex) {
            //Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static Key getSecretKey(String password) {

//        return new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
//        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = null;
            random = SecureRandom.getInstance("SHA1PRNG");
            //AES 要求密钥长度为 128
            random.setSeed(password.getBytes());
            kg.init(128, random);
            //kg.init(128, new SecureRandom(password.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return secretKey;
            //return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


}
