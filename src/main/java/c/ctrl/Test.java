/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.ctrl;

import c.util.DataTransferUtil;
import c.wx.models.pay.UnifiedOrderModel;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("/index")
    public String index() {
        return "test/index";
    }

    @RequestMapping("/test")
    public @ResponseBody
    String test(HttpServletRequest request) {
//        UnifiedOrderModel m = new UnifiedOrderModel();
//        m.simple_id = 10;
//        m.simple_name = "bob";
//        String str = DataTransferUtil.objectToXml(m, UnifiedOrderModel.class);
//        UnifiedOrderModel mm=(UnifiedOrderModel)DataTransferUtil.xmlToObject(str,UnifiedOrderModel.class);
        Map m = new HashMap();
        m.put("aaa", "bbb");
        m.put("ccc", 123);
        String str = DataTransferUtil.mapToJson(m);
        Map mm = DataTransferUtil.jsonToMap(str);
        return "test";
    }
}
