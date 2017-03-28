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
public class Server2 {
    public static void main(String args[]){
        
        ConnectionManager cm = new ConnectionManager();
        
        cm.startServerUDP(2425);
          
        System.out.println("Server 2: esperando...");
          
        while (true) {            
            
            DatagramPacket datagramPacket=cm.listenerUDP();
            if(datagramPacket!=null){
                Thread2 t2 = new Thread2(datagramPacket);
                t2.start();
            
            }
               
            
        }
        
    }
}
