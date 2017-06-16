/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.messagemanagement;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import c.wx.config.WXConfigModel;

public class MessageUtil {

    public static void messageAndEventHandler(Map<String, String> xmlMap, HttpServletResponse response) {
        switch (xmlMap.get("MsgType")) {
            case "text":
                if (xmlMap.get("Content").contains("hi")) {
                    replyTextMessage(xmlMap, response, "hi");
                } else {
                    replyTextMessage(xmlMap, response, "回复：" + xmlMap.get("Content"));
                }
                break;
            case "image":
                replyTextMessage(xmlMap, response, "image");
                break;
            case "event":
                replyTextMessage(xmlMap, response, "event");
                break;
            default:
                replyTextMessage(xmlMap, response, "default");
        }
    }

    public static void replyTextMessage(Map<String, String> xmlMap, HttpServletResponse response, String replyMessage) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("xml");
            document.appendChild(root);
            Element toUserName = document.createElement("ToUserName");
            toUserName.setTextContent(xmlMap.get("FromUserName"));
            root.appendChild(toUserName);
            Element fromUserName = document.createElement("FromUserName");
            fromUserName.setTextContent(WXConfigModel.getPlatformWechatNumber());
            root.appendChild(fromUserName);
            Element createTime = document.createElement("CreateTime");
            createTime.setTextContent(String.valueOf(System.currentTimeMillis()));
            root.appendChild(createTime);
            Element msgType = document.createElement("MsgType");
            msgType.setTextContent("text");
            root.appendChild(msgType);
            Element content = document.createElement("Content");
            content.setTextContent(replyMessage);
            root.appendChild(content);

            response.setCharacterEncoding("UTF-8");
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            try (StringWriter sWriter = new StringWriter()) {
                tf.transform(new DOMSource(document), new StreamResult(sWriter));
                try (PrintWriter pWriter = response.getWriter()) {
                    pWriter.write(sWriter.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
