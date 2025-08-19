//JavaDeserController.java
JavaDeserializationFlag Flag = new JavaDeserializationFlag(flag);		//flag为其他代码部分随机生成
Optional<byte[]> bytes = JavaDeserByteArrayUtils.objectToBytes(Flag);	//将对象转换为二进制字节数组(序列化)
byte[] ret = bytes.get();
String r1 = JavaDeserByteArrayUtils.toHexString(ret);//序列化后的反序列化是新的对象
System.out.println("序列化后转换字节数组-16进制表示："+r1);

Result res=new Result(Constants.CODE_200,null,"flag类序列化后转换字节数组(16进制):"+r1);
return res;


//JavaDeserializationFlag.java
import java.io.Serializable;
public class JavaDeserializationFlag implements Serializable {
    public String flag;
    public JavaDeserializationFlag(String s) {
        this.flag = s;
    }
    @Override
    public String toString() {
        return this.flag;
    }
}


//JavaDeserByteArrayUtils.java
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Optional;

/**
 * @date: 2022-03-10
 * @author: lihongwei
 * @description: 字节/字符串工具（java序列化与反序列化）
 */
public class JavaDeserByteArrayUtils {
    //序列化
    public static <T> Optional<byte[]> objectToBytes(T obj) {
        byte[] bytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream sOut;
        try {
            sOut = new ObjectOutputStream(out);
            sOut.writeObject(obj);
            sOut.flush();
            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(bytes);
    }
	/*
	*.....省去反序列化部分代码
	*/
	//16进制表示格式的字符串转化为字节数组
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }
}
