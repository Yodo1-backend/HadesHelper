package com.yodo1.hadeshelper.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by YanFeng on 2018/4/10.
 */
public class Util {
    public static final String NewLine()
    {
       return System.getProperty("line.separator");
    }
    public static final String LogSplit()
    {
        return "***";
    }
    public static final boolean isStrValuable(String... strs)
    {
        for (String str:strs) {
            if(null == str || str.isEmpty())
            {
                return false;
            }
        }
        return true;
    }
    public static String ByteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer = null;
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset
                    .newDecoder();
            charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
