/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))) {
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

    public static String HttpClient_Post(String url, String param) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().build();
            httpPost.setConfig(requestConfig);
            StringEntity postEntity = new StringEntity(param, "UTF-8");
            httpPost.setEntity(postEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            return EntityUtils.toString(httpEntity, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
