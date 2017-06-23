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
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DataTransferUtil {

    public static Map<String, String> jsonToMap(String str) {
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(str, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

    public static String mapToJson(Map<String, String> map) {
        Gson gson = new Gson();
        String str = gson.toJson(map);
        return str;
    }

    public static String objectToXml(Object obj, Class<?> cls) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(cls);
            Marshaller marshaller = ctx.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Object xmlToObject(String xml, Class<?> cls) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(cls);
            Unmarshaller unMarshaller = ctx.createUnmarshaller();
            Object obj = unMarshaller.unmarshal(new StringReader(xml));
            return obj;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Map xmlToMap(String xml, boolean needRootElement) {
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            Map<String, Object> map = (Map<String, Object>) xmlToMapUtil(root);
            if (root.elements().size() == 0 && root.attributes().size() == 0) {
                return map;
            }
            if (needRootElement) {
                Map<String, Object> rootMap = new HashMap<>();
                rootMap.put(root.getName(), map);
                return rootMap;
            }
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static Map xmlToMapUtil(Element e) {
        Map map = new LinkedHashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = xmlToMapUtil(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), m);
                    }
                } else if (iter.elements().size() == 0) {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), iter.getText());
                    }
                } else {
                }
            }
        } else {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    public static String mapToXml(Map<String, Object> map, String rootName) {
        //map中没有根节点的键
        try {
            Document document = DocumentHelper.createDocument();
            Element root = DocumentHelper.createElement(rootName);
            document.add(root);
            mapToXmlUtil(map, root);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            StringWriter writer = new StringWriter();
            XMLWriter xmlWriter = new XMLWriter(writer, format);
            xmlWriter.write(document);
            xmlWriter.close();
            return writer.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String mapToXml(Map<String, Object> map) {
        //map中含有根节点的键
        try {
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            if (entries.hasNext()) { //获取第一个键创建根节点  
                Map.Entry<String, Object> entry = entries.next();
                Document document = DocumentHelper.createDocument();
                Element root = DocumentHelper.createElement(entry.getKey());
                document.add(root);
                mapToXmlUtil((Map) entry.getValue(), root);
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                StringWriter writer = new StringWriter();
                XMLWriter xmlWriter = new XMLWriter(writer, format);
                xmlWriter.write(document);
                xmlWriter.close();
                return writer.toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static Element mapToXmlUtil(Map<String, Object> map, Element element) {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.startsWith("@")) { //属性  
                element.addAttribute(key.substring(1, key.length()), value.toString());
            } else if (key.equals("#text")) { //有属性时的文本  
                element.setText(value.toString());
            } else if (value instanceof List) {
                List list = (List) value;
                Object obj;
                for (int i = 0; i < list.size(); i++) {
                    obj = list.get(i);
                    //list里是map或String，不会存在list里直接是list的，  
                    if (obj instanceof Map) {
                        Element subElement = element.addElement(key);
                        mapToXmlUtil((Map) list.get(i), subElement);
                    } else {
                        element.addElement(key).setText((String) list.get(i));
                    }
                }
            } else if (value instanceof Map) {
                Element subElement = element.addElement(key);
                mapToXmlUtil((Map) value, subElement);
            } else {
                element.addElement(key).setText(value.toString());
            }
        }
        return element;
    }

    public static String streamToString(InputStream inputStream) {
        //方法内部已经关闭stream了，外部可以不再关闭
        try {
            StringBuilder sb;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
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
