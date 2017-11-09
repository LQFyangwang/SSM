package com.gs.crypto;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

import java.security.Key;

public class ShiroCrypto {
    /**
     * 明文->密文
     *
     * 可逆与不可逆
     *
     * 消息摘要， 消息摘要算法
     * a ->  b
     *
     * SHA: 可逆    SHA1 SHA256  SHA384   SHA512
     * MD5: 不可逆
     *
     * 规则a得到的密文，可以通过规则a反向算出明文      SHA
     *
     * 对称加密DES，非对称加密AES
     * 公钥， 密钥       公钥 = 密钥    对称加密
     *                    公钥 != 密钥  非对称加密
     *
     * HASH 哈希， 散列算法
     *
     * 消息摘要后得到的都是字节数组，把字节数组转化成字符串（编码规则）
     *  1）采用十六进制的方式转化成字符串
     *  2）Base64编码规则
     */

    @Test
    public void testSha1() {
        Sha1Hash sha1Hash = new Sha1Hash("123456", "jdfkjdslk");
        System.out.println(sha1Hash.toString());
        System.out.println(sha1Hash.toHex());
        System.out.println(sha1Hash.toBase64());
    }

    @Test
    public void testSha256() {
        Sha256Hash sha1Hash = new Sha256Hash("123456", "jdfkjdslk");
        System.out.println(sha1Hash.toString());
        System.out.println(sha1Hash.toHex());
        System.out.println(sha1Hash.toBase64());
    }

    @Test
    public void testMD5() {
        Md5Hash md5Hash = new Md5Hash("123456", "fjhdshfjkdshfjkd");
        System.out.println(md5Hash.toString());
        System.out.println(md5Hash.toHex());
        System.out.println(md5Hash.toBase64());
    }

    @Test
    public void testAES() {
        AesCipherService aesCipherService = new AesCipherService(); // 对称密钥加密服务类
        aesCipherService.setKeySize(128); //设置key长度
        //生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
        System.out.println(encrptText + ", " + text2);
    }

}
