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
import org.apache.log4j.Logger;
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
        Logger logger = Logger.getLogger(Test.class);
        logger.debug("this is a debug message");
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is a error message");
        logger.fatal("this is a fatal message");
        return "test";
    }
}
