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
public class Server2 {
    
    
    public static void main(String args[]){
        
        ConnectionManager cm = new ConnectionManager();
        
        cm.startServerTCP(2425);
        
        while (true) {            
            
            System.out.println("Server 2: esperando...");
            if(cm.listenerTCP() == true){
               
                Thread2 t2 = new Thread2(cm);
                t2.start();
                
            }
        }
        
    }
    
}
