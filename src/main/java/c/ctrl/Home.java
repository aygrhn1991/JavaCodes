/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/home")
public class Home {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
