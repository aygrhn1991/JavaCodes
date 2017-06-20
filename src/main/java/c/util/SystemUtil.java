/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

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
}
