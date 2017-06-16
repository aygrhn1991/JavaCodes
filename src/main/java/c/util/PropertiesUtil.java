/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class PropertiesUtil {

    public PropertiesUtil(String propertiesFileName) {
        this.propertiesFilePath = "/" + propertiesFileName;
    }
    private final String propertiesFilePath;

    public String getValueByKey(String key) {
        Properties properties = new Properties();
        try {
            String value;
            try (InputStream inputStream = new FileInputStream(PropertiesUtil.class.getResource(this.propertiesFilePath).getPath())) {
                properties.load(inputStream);
                value = properties.getProperty(key);
            }
            return value;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void setValueByKey(String key, String value) {
        Properties properties = new Properties();
        try {
            try (InputStream inputStream = new FileInputStream(PropertiesUtil.class.getResource(this.propertiesFilePath).getPath())) {
                properties.load(inputStream);
            }
            try (OutputStream outputStream = new FileOutputStream(PropertiesUtil.class.getResource(this.propertiesFilePath).getPath())) {
                properties.setProperty(key, value);
                properties.store(outputStream, "");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Map<String, String> getValues() {
        Properties properties = new Properties();
        try {
            Map<String, String> map;
            try (InputStream inputStream = new FileInputStream(PropertiesUtil.class.getResource(this.propertiesFilePath).getPath())) {
                properties.load(inputStream);
                map = new HashMap<>();
                Iterator<String> it = properties.stringPropertyNames().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    map.put(key, properties.getProperty(key));
                }
            }
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void setValues(Map<String, String> map) {
        Properties properties = new Properties();
        try {
            try (InputStream inputStream = new FileInputStream(PropertiesUtil.class.getResource(this.propertiesFilePath).getPath())) {
                properties.load(inputStream);
            }
            try (OutputStream outputStream = new FileOutputStream(PropertiesUtil.class.getResource(this.propertiesFilePath).getPath())) {
                for (Map.Entry<String, String> obj : map.entrySet()) {
                    properties.setProperty(obj.getKey(), obj.getValue());
                }
                properties.store(outputStream, "");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
