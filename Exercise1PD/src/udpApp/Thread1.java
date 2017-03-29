/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpApp;

import communicationManager.ConnectionManager;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
    
    public Thread1(ConnectionManager connMannager,DatagramPacket datagramPacket){
       this.receiveData = datagramPacket;
       this.cm1=connMannager;
    }
   
    
    public void run(){
        
        try {   
            DatagramPacket clientUdpPacket = this.receiveData;
            byte[] clientData = clientUdpPacket.getData();
            
            int clientPort=clientUdpPacket.getPort();
            InetAddress clientAddress=clientUdpPacket.getAddress();
            InetAddress server2Address=InetAddress.getByName(SERVER2_ADDRESS);
            
            this.cm2 = new ConnectionManager();
            this.cm2.sendDataUDP(clientData, server2Address, SERVER2_PORT);// enviando para o segundo servidor
            System.out.println("Enviando para o servidor 2");
                
            DatagramPacket serverUdpPacket = this.cm2.getDataUDP();
            byte[] serverData = serverUdpPacket.getData();
            
            this.cm1.sendDataUDP(serverData,clientAddress,clientPort);
            System.out.println("Enviando para o cliente");
           
            
            this.cm2.closeConnectionUDP();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    
    }
    
    
    
}
