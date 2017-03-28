/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpApp;

import communicationManager.ConnectionManager;
import java.net.DatagramPacket;

/**
 *
 * @author gprt
 */
public class Thread1 extends Thread{
    
    ConnectionManager cm1;
    ConnectionManager cm2;
    DatagramPacket receiveData;
    private static final int SERVER2_PORT=2425;
    private static final String SERVER2_ADDRESS="localhost";

    public Thread1() {
    }
    
    public Thread1(DatagramPacket datagramPacket){
       this.receiveData = datagramPacket;
    }
   
    
    public void run(){
        
        try {
            this.cm1 = new ConnectionManager();
       
            DatagramPacket clientUdpPacket = this.receiveData;
            byte[] clientData = clientUdpPacket.getData();
            
            System.out.println("Thread1");
            int clientPort=clientUdpPacket.getPort();
            String clientAddress=clientUdpPacket.getAddress().toString();
            
            this.cm2 = new ConnectionManager();
            System.out.println("udpApp.Thread1.run()"+clientData);
            this.cm2.sendDataUDP(clientData, SERVER2_ADDRESS, SERVER2_PORT);// enviando para o segundo servidor
            System.out.println("Enviando para o servidor 2");
                
            //ConnectionManager cm3=new ConnectionManager()
            DatagramPacket serverUdpPacket = this.cm1.getDataUDP();
            byte[] serverData = serverUdpPacket.getData();
            
            this.cm1.sendDataUDP(serverData,clientAddress,clientPort);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    
    }
    
    
    
}
