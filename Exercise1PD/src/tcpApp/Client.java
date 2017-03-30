/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpApp;

import communicationManager.ConnectionManager;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author guto
 */
public class Client {
    
    public static void main(String args[]) throws UnsupportedEncodingException{
        
       ConnectionManager cm = new ConnectionManager();
       Scanner scanner = new Scanner(System.in);
       
       System.out.println("Digite a operação:");
       String s = scanner.nextLine();
       
       if(cm.connectionServerTCP("localhost", 2424) == true){
           cm.sendDataTCP(s.getBytes());
           
           System.out.println("ClienteTCP: Enviando operação. Esperando resposta...");
           
           byte[] data = cm.getDataTCP();
           String msg = new String(data, "UTF-8");
           System.out.println(msg);
       }
      
    }
    
}
