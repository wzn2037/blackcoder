package com.nanking;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class Test1 {


    @Test
    public void fun1 () throws NoSuchAlgorithmException {
        File file = new File(Test1.class.getClassLoader().getResource("templates/excel/document.xlsx").getFile());
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());

        //////
        String jiao = DigestUtils.md5Hex("jiao");
        System.out.println(jiao);
        String jiao1 = DigestUtils.sha256Hex("jiao");
        System.out.println(jiao1);
    }

}
