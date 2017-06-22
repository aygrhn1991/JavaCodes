/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.ctrl;

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
        return "";
    }
}
