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
public class ButtonGroupModel extends ButtonBase {

    public ButtonGroupModel(String name, ButtonModel... sub_button) {
        this.name = name;
        this.sub_button = new ArrayList<>();
        for (int i = 0; i < sub_button.length; i++) {
            this.sub_button.add(sub_button[i]);
        }
    }
}
