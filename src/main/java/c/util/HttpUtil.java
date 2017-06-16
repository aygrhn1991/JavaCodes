/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    public static String Request_GET(String url) {
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
            connection.connect();
            String line;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String Request_POST(String url, String param) {
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
                out.print(param);
                out.flush();
            }
            String line;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getCurrentBaseUrlWithPort(HttpServletRequest request) {
        try {
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getCurrentBaseUrlWithoutPort(HttpServletRequest request) {
        try {
            return request.getScheme() + "://" + request.getServerName() + request.getContextPath();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
