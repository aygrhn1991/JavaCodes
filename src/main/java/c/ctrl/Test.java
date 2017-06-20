/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.ctrl;

import c.jdbc.JdbcUtil;
import c.util.PropertiesUtil;
import java.util.List;
import oracle.sql.SQLUtil;
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

    @RequestMapping("/index2")
    public @ResponseBody
    List index2() {
        PropertiesUtil propertiesUtil = new PropertiesUtil("connection.properties");
        String url = propertiesUtil.getValueByKey("mysql.url");
        String user = propertiesUtil.getValueByKey("mysql.user");
        String password = propertiesUtil.getValueByKey("mysql.password");
        String driver = propertiesUtil.getValueByKey("mysql.driver");
        JdbcUtil u = new JdbcUtil(user, password, url, driver);
        u.openConnection();
        List<String> re = u.queryObjectList("select name from usertb", String.class);
        u.closeConnection();
        return re;
    }
}
