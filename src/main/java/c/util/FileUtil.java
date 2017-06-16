/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrator
 */
public class FileUtil {

    public static String getFileExtensionName(String fileName) {
        return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String fileToBase64(File file) {
        byte[] data = null;
        try {
            try (InputStream inputStream = new FileInputStream(file)) {
                data = new byte[inputStream.available()];
                inputStream.read(data);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String fileToBase64(InputStream inputStream) {
        byte[] data = null;
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean base64ToFile(String base64, String path) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64);
            try (OutputStream outputStream = new FileOutputStream(path)) {
                outputStream.write(bytes);
                outputStream.flush();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
