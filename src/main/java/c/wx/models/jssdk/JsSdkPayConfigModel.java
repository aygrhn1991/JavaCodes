/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.models.jssdk;

import c.wx.pay.PaySignTypeEnum;

/**
 *
 * @author Administrator
 */
public class JsSdkPayConfigModel {

    public String appId;
    public long timeStamp;
    public String nonceStr;
    public String pkg;
    public PaySignTypeEnum signType;
    public String paySign;
}
