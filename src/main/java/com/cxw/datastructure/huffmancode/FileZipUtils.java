package com.cxw.datastructure.huffmancode;

import java.io.*;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-07-09 11:52
 * @description 文件压缩与解压
 */
public class FileZipUtils {
    public static void main(String[] args) {
//        String src = "/Users/chengxuwei/Downloads/即时单倾斜简单整理.txt";
//        String tgt= "/Users/chengxuwei/Downloads/即时单倾斜简单整理new.newzip";
//        zip(src,tgt);
        String tgt = "/Users/chengxuwei/Downloads/即时单倾斜简单整理new.newzip";
        String src = "/Users/chengxuwei/Downloads/即时单倾斜简单整理new.txt";
        unzip(tgt,src);
    }

    private static void zip(String src, String tgt) {
        try (InputStream is = new FileInputStream(src);
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tgt))){
            byte[] context = new byte[is.available()];
            is.read(context);
            Map<Byte, String> huffmanCodeMap = HuffmanCode.getHuffmanCodeMap(context);
            byte[] newContext = HuffmanCode.huffmanZip(context,huffmanCodeMap);
            oos.writeObject(newContext);
            oos.writeObject(huffmanCodeMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unzip(String tgt,String src) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tgt));
             FileOutputStream os = new FileOutputStream(src)){
            byte[] newContext = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();
            byte[] bytes = HuffmanCode.huffmanUnzip(newContext, huffmanCodeMap);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}