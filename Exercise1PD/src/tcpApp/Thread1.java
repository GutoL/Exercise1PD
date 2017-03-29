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
public class Thread1 extends Thread{
    
    ConnectionManager cm1;
    ConnectionManager cm2;

    public Thread1() {
    }
    
    public Thread1(ConnectionManager socket){
        this.cm1 = socket;
    }
    
    public void run(){
        
        try {
       
            byte[] data = this.cm1.getDataTCP();
            
            this.cm2 = new ConnectionManager();
            
            if(this.cm2.connectionServerTCP("localhost", 2425) == true){
                this.cm2.sendDataTCP(data);// enviando para o segundo servidor
                System.out.println("Enviando para o servidor 2");
                
                data = this.cm2.getDataTCP();
                this.cm1.sendDataTCP(data);
                System.out.println("Enviando para o cliente");
            }
            
        } catch (Exception e) {
        }
       
    
    }
    
    
    
}
