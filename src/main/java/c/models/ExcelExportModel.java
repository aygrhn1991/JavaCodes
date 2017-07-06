/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.models;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ExcelExportModel {

    public ExcelExportModel(int id, String str, boolean bool, Date date, long num1, float num2, double num3) {
        this.id = id;
        this.str = str;
        this.bool = bool;
        this.date = date;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public ExcelExportModel() {
    }

    private int id;
    private String str;
    private boolean bool;
    private Date date;
    private long num1;
    private float num2;
    private double num3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean getBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getNum1() {
        return num1;
    }

    public void setNum1(long num1) {
        this.num1 = num1;
    }

    public float getNum2() {
        return num2;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    public double getNum3() {
        return num3;
    }

    public void setNum3(double num3) {
        this.num3 = num3;
    }
}
