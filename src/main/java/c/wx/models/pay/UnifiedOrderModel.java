/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.models.pay;

import c.wx.pay.UnifiedOrderTradeTypeEnum;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "xml")
public class UnifiedOrderModel {

    public String appid;
    public String mch_id;
    private String device_info;
    public String nonce_str;
    public String sign;
    private String sign_type;
    public String body;
    private String detail;
    private String attach;
    public String out_trade_no;
    private String fee_type;
    public int total_fee;
    public String spbill_create_ip;
    private String time_start;
    private String time_expire;
    private String goods_tag;
    public String notify_url;
    public UnifiedOrderTradeTypeEnum trade_type;
    private String product_id;
    private String limit_pay;
    public String openid;
}
