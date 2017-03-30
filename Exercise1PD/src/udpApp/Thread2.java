/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpApp;

import communicationManager.ConnectionManager;
import java.net.DatagramPacket;
import java.net.InetAddress;
import services.Calculator;
import services.CalculatorHelper;

/**
 *
 * @author gprt
 */
public class Thread2 extends Thread{
    
    ConnectionManager cm;
    DatagramPacket receiveData;

    
    public Thread2(ConnectionManager connectionManager, DatagramPacket datagramPacket){
        this.receiveData=datagramPacket;
        this.cm=connectionManager;
    }
    
    public void run(){
        
        
        try {
           
            
             System.out.println("Uma requisição!");
             DatagramPacket clientPacket=this.receiveData;
             byte[] data = clientPacket.getData();
             int clientPort=clientPacket.getPort();
             InetAddress clientAddress=clientPacket.getAddress();
             String msg = new String(data, "UTF-8");
             String[] datas = msg.split("");
             String response = "";
             
             Calculator calculator = new Calculator();
             
             CalculatorHelper calcHelper=new CalculatorHelper();
             response=calcHelper.calc(datas, calculator);
             
             this.cm.sendDataTCP(response.getBytes());        
             this.cm.sendDataUDP(response.getBytes(),clientAddress,clientPort); 
             System.out.println("Enviando para o servidor 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
