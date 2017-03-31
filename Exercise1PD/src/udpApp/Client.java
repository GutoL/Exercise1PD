/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpApp;

import communicationManager.ConnectionManager;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gprt
 */
public class Client {
    private static final int PORT=2424;
    private static final String ADDRESS="localhost";
    
    public static void main(String args[]) throws UnsupportedEncodingException{
        
       ConnectionManager cm = new ConnectionManager();
       String s = ""; 
       
       while(!s.equals("exit")){
           
           try {
            InetAddress address=InetAddress.getByName(ADDRESS);
            
            Scanner scanner = new Scanner(System.in);
       
            System.out.println("Digite a operação simples(exemplo:2+2) ou 'exit' para sair:");
            s = scanner.nextLine();
       
            if(cm.sendDataUDP(s.getBytes(), address, PORT)&&(!s.equals("exit"))){
                System.out.println("ClienteUDP: Enviando operação. Esperando resposta...");
           
                DatagramPacket serverPacket=cm.getDataUDP();
                byte[] data = serverPacket.getData();
                String msg = new String(data, "UTF-8");
                System.out.println(msg);
                //cm.closeSocketUDP();
            }
           
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       }
       cm.closeSocketUDP();
       
    }
    
}
