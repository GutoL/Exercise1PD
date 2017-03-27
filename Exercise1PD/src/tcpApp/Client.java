/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpApp;

import communicationManager.ConnectionManager;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author guto
 */
public class Client {
    
    public static void main(String args[]) throws UnsupportedEncodingException{
        
       ConnectionManager cm = new ConnectionManager();
       
       if(cm.connectionServerTCP("localhost", 2424) == true){
           cm.sendDataTCP("div,1,2".getBytes());
           
           System.out.println("Cliente: Enviei, esperando resposta");
           
           byte[] data = cm.getDataTCP();
           String msg = new String(data, "UTF-8");
           System.out.println(msg);
       }
    }
    
}
