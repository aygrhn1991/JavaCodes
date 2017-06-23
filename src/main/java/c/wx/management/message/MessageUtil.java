/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.management.message;

import c.util.DataTransferUtil;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import c.wx.config.WXConfigModel;
import java.io.OutputStreamWriter;
import java.util.HashMap;

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
            case "voice":
                replyTextMessage(xmlMap, response, "voice");
                break;
            case "video":
                replyTextMessage(xmlMap, response, "video");
                break;
            case "shortvideo":
                replyTextMessage(xmlMap, response, "shortvideo");
                break;
            case "location":
                replyTextMessage(xmlMap, response, "location");
                break;
            case "link":
                replyTextMessage(xmlMap, response, "link");
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
            Map<String, Object> map = new HashMap<>();
            map.put("ToUserName", xmlMap.get("FromUserName"));
            map.put("FromUserName", WXConfigModel.getPlatformWechatNumber());
            map.put("CreateTime", String.valueOf(System.currentTimeMillis()));
            map.put("MsgType", "text");
            map.put("Content", replyMessage);
            String xml = DataTransferUtil.mapToXml(map, "xml");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"))) {
                out.print(xml);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
