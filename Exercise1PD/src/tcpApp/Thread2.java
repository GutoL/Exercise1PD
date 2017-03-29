/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpApp;

import communicationManager.ConnectionManager;
import services.Calculator;
import services.CalculatorHelper;

/**
 *
 * @author guto
 */
public class Thread2 extends Thread{
    
    ConnectionManager cm;

    public Thread2() {
    }
    
    
    
    public Thread2(ConnectionManager cm){
        this.cm = cm;
    }
    
    public void run(){
        
        
        try {
            
             System.out.println("Uma requisição!");
             byte[] data = cm.getDataTCP();
             String msg = new String(data, "UTF-8");
             String[] datas = msg.split(",");
             String response = "";
             
             Calculator calculator = new Calculator();
             
             CalculatorHelper calcHelper=new CalculatorHelper();
             response=calcHelper.calc(datas, calculator);
             
             this.cm.sendDataTCP(response.getBytes());
             System.out.println("Enviando para o servidor 1");
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
