/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.models.menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MenuModel {

    public MenuModel(ButtonBase... button) {
        this.button = new ArrayList<>();
        for (int i = 0; i < button.length; i++) {
            this.button.add(button[i]);
        }
    }

    public List<ButtonBase> button;
}
