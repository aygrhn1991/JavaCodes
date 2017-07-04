/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.models.pay;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "xml")
public class PayCallBackModel {

    public String return_code;
    public String return_msg;
    public String appid;
    public String mch_id;
    public String device_info;
    public String nonce_str;
    public String sign;
    public String sign_type;
    public String result_code;
    public String err_code;
    public String err_code_des;
    public String openid;
    public String is_subscribe;
    public String trade_type;
    public String bank_type;
    public int total_fee;
    public int settlement_total_fee;
    public String fee_type;
    public int cash_fee;
    public String cash_fee_type;
    public int coupon_fee;
    public int coupon_count;
    public String coupon_type_$n;
    public String coupon_id_$n;
    public int coupon_fee_$n;
    public String transaction_id;
    public String out_trade_no;
    public String attach;
    public String time_end;
}
