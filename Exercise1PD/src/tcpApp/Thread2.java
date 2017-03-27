/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpApp;

import communicationManager.ConnectionManager;
import services.Calculator;

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
            
             System.out.println("Uma requisição! faz as continha aqui, viado u.u");
             byte[] data = cm.getDataTCP();
             String msg = new String(data, "UTF-8");
             String[] datas = msg.split(",");
             String resposta = "";
             
             Calculator calculator = new Calculator();
             
             if(datas[0].equals("add")){
                 resposta = Float.toString(calculator.add(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             else if(datas[0].equals("sub")){
                 resposta = Float.toString(calculator.sub(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             else if(datas[0].equals("mult")){
                 resposta = Float.toString(calculator.mult(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             else if(datas[0].equals("div")){
                 resposta = Float.toString(calculator.div(Float.parseFloat(datas[1]), Float.parseFloat(datas[2])));
             }
             
             this.cm.sendDataTCP(resposta.getBytes());
             
        } catch (Exception e) {
        }
        
        
    }
}
