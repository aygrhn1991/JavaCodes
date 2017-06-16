/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.models;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class ParameterComplexModel {

    public int complex_id;
    public String complex_name;
    public ParameterSimpleModel model;
    public List<ParameterSimpleModel> list;
}
