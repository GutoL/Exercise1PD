/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpApp;

import communicationManager.ConnectionManager;

/**
 *
 * @author guto
 */
public class Server1 {
    
    public static void main(String args[]){
       
        ConnectionManager cm = new ConnectionManager();
        
        cm.startServerTCP(2424);
        
        while (true) {            
            
            System.out.println("Server 1: esperando...");
            if(cm.listenerTCP() == true){
               
                Thread1 t1 = new Thread1(cm);
                t1.start();
                
                
            }
        }
        
        
    }
    
}
