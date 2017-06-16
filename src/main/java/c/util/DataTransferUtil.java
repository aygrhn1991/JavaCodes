/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataTransferUtil {

    public static Map<String, String> jsonToMap(String str) {
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(str, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

    public static Map<String, String> xmlToMap(String str) {
        try {
            Map<String, String> map;
            try (InputStream stream = stringToInputStream(str)) {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.parse(stream);
                Element rootElement = document.getDocumentElement();
                map = new HashMap<>();
                NodeList nodes = rootElement.getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    map.put(node.getNodeName(), node.getTextContent());
                }
            }
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Map<String, String> xmlToMap(InputStream stream) {
        try {
            Map<String, String> map;
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(stream);
            Element rootElement = document.getDocumentElement();
            map = new HashMap<>();
            NodeList nodes = rootElement.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                map.put(node.getNodeName(), node.getTextContent());
            }
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String streamToString(InputStream inputStream) {
        //方法内部已经关闭stream了，外部可以不再关闭
        try {
            StringBuilder sb;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                inputStream.close();
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static InputStream stringToInputStream(String str) {
        //调用此方法时，一定要记得关闭stream
        try {
            InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            return inputStream;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
