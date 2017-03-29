/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author demis
 */
public class CalculatorHelper {
    
    public String calc(String[] datas, Calculator calculator){
        String response="NaN";
        if(datas[0].equals("add")){
                 response = Float.toString(calculator.add(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             else if(datas[0].equals("sub")){
                 response = Float.toString(calculator.sub(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             else if(datas[0].equals("mult")){
                 response = Float.toString(calculator.mult(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             else if(datas[0].equals("div")){
                 response = Float.toString(calculator.div(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
        return response;
    }
    
}
