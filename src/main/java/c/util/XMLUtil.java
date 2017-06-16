/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLUtil {

    public static String getNodeText(String str, String tagName) {
        try {
            Element element;
            try (InputStream stream = DataTransferUtil.stringToInputStream(str)) {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.parse(stream);
                Element rootElement = document.getDocumentElement();
                NodeList nodeList = rootElement.getElementsByTagName(tagName);
                element = (Element) nodeList.item(0);
            }
            return element.getTextContent();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
