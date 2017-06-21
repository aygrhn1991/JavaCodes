/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.database.models;

/**
 *
 * @author Administrator
 */
public class JdbcDataModel {

    public JdbcDataModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int id;
    public String name;
}
