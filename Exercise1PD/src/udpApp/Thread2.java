/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpApp;

import communicationManager.ConnectionManager;
import java.net.DatagramPacket;
import services.Calculator;

/**
 *
 * @author gprt
 */
public class Thread2 extends Thread{
    
    ConnectionManager cm;
    DatagramPacket receiveData;
    private static final int SERVER2_PORT=2425;

    
    public Thread2(DatagramPacket datagramPacket){
        this.receiveData=datagramPacket;
    }
    
    public void run(){
        
        
        try {
            this.cm=new ConnectionManager();
            
             System.out.println("Uma requisição!");
             DatagramPacket clientPacket=this.receiveData;
             System.out.println("udpApp.Thread2.run() received data");
             byte[] data = clientPacket.getData();
             int clientPort=clientPacket.getPort();
             String clientAddress=clientPacket.getAddress().toString();
             System.out.println("udpApp.Thread2.run() get address");
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
             
             System.out.println("udpApp.Thread2.run() sending...");
             this.cm.sendDataUDP(resposta.getBytes(),clientAddress,clientPort);
             
             System.out.println("udpApp.Thread2.run() sent");
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
