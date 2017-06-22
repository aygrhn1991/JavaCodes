/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
public class SystemUtil {

    public static String getRootPath(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String rootPath = servletContext.getRealPath("/");
        return rootPath;//"D:\WorkSpace\Codes\target\Codes-1.0-SNAPSHOT\"
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            stringBuilder.append(base.charAt(number));
        }
        return stringBuilder.toString();
    }
}
