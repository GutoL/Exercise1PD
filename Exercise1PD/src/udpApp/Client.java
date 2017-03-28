/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpApp;

import communicationManager.ConnectionManager;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

/**
 *
 * @author gprt
 */
public class Client {
    private static final int PORT=2424;
    private static final String ADDRESS="localhost";
    
    public static void main(String args[]) throws UnsupportedEncodingException{
        
       ConnectionManager cm = new ConnectionManager();
       
       cm.sendDataUDP("div,1,2".getBytes(), ADDRESS, PORT);
           
       System.out.println("ClienteUDP: Enviei, esperando resposta");
           
       DatagramPacket serverPacket=cm.getDataUDP();
       byte[] data = serverPacket.getData();
       String msg = new String(data, "UTF-8");
       System.out.println(msg);
       
    }
    
}
