package tech.chending.wechat.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import tech.chending.wechat.pojo.WeChatSignatureEntity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author ChenDing
 */
@Slf4j
public class WeChatSignatureUtil {

    //token值
    private final static String TOKEN = "chending";
    private WeChatSignatureUtil() {}

    public static boolean signature(final WeChatSignatureEntity signatureEntity) {
        //将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String[] strings = {TOKEN, signatureEntity.getTimestamp(), signatureEntity.getNonce()};
        //排序
        Arrays.sort(strings);
        //拼接字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length;i++) {
            builder.append(strings[i]);
        }
        //采用sha1加密
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] bytes = digest.digest(builder.toString().getBytes());
            String str = byteToStr(bytes);
            return str.equals(signatureEntity.getSignature().toUpperCase());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return false;
    }



    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            builder.append(byteToHexStr(byteArray[i]));
        }
        return builder.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];

        return new String(tempArr);
    }

}
