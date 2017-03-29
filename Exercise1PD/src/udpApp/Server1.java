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
public class Server1 {
    private static final int PORT=2424;
    public static void main(String[] args) {
       
    ConnectionManager cm = new ConnectionManager();
        
        cm.startServerUDP(PORT);
        System.out.println("Server 1: esperando...");
           
        while (true) {            
            
             DatagramPacket datagramPacket=cm.listenerUDP();
             if(datagramPacket!=null){
               Thread1 t1 = new Thread1(cm,datagramPacket);
                t1.start();    
             
             }
                
        }
    }
    
}
