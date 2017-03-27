/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author guto
 */
public class ConnectionManager {
    
    ServerSocket serverSocket;
    Socket connection;
    DatagramSocket datagramSocket;

    public ConnectionManager() {
    }
    

//////////////////////////////////////////////// TCP METHODS //////////////////////////////////////////////////////////////////    
    /**
     * method to connect with TCP server
     * @param IP server IP address
     * @param port server number port
     * @return true or false 
     */
    public boolean connectionServerTCP(String IP,int port){
        
        try {
            
            this.connection = new Socket(IP, port);
            return true;
            
        } catch (Exception e) {
            System.out.println("error TCP connection!");
            return false;
        }
        
        
    }// fim do método connectionServerTCP
    
    
    /**
     * method that start server TCP
     * @param port Port number the server will wait for requests
     * @return true or false
     */
    public boolean startServerTCP(int port){
        
        try {
            
            this.serverSocket = new ServerSocket(port);
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }// fim do método startServerTCP
    
    
    /**
     * method that wait client requisitions
     * @return true or false
     */
    public boolean listenerTCP(){
        
        try {
            this.connection = this.serverSocket.accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }//fim do método listenerTCP
    
    
    /**
     * method that send a string (bytes) to server 
     * @param data bytes array that will send to server
     * @return true or false
     */
    public boolean sendDataTCP(byte[] data){
        
        try {
            
            DataOutputStream dos = new DataOutputStream(this.connection.getOutputStream());
            dos.writeInt(data.length);
            dos.write(data);
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
        
    }//fim do método sendDataTCP
    
    /**
     * Method that receives the data from a TCP connection
     * @return bytes array (data received by client)
     */
    public byte[] getDataTCP(){
        
        try {
            
            DataInputStream dis = new DataInputStream(this.connection.getInputStream());
            int dataLength = dis.readInt();
            byte[] data = new byte[dataLength];
            dis.read(data,0,dataLength);
            
            return data;
                    
        } catch (Exception e) {
            return null;
        }
    }// fim do método getDataTCP
    
    /**
     * method that close connection TCP between client and server
     * @return true or false
     */
    public boolean closeConnectionTCP(){
        
        try {
            this.connection.close();
            return true;
        } catch (Exception e) {
            
            return false;
        
        }
    }// fim do método closeConnectionTCP
    

//////////////////////////////////////////////// UDP METHODS //////////////////////////////////////////////////////////////////
    
    /**
     * method that send broadcast package
     * @param data data that will send
     * @param port port that server will listen
     * @return true or false
     */
    public boolean sendDataUDP(byte []data, int port){
        
        try {
            this.datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost"); // localhost ? :/
            DatagramPacket udpPacket = new DatagramPacket(data,data.length,inetAddress,port);
            
            this.datagramSocket.send(udpPacket);
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }//fim do método sendDataUDP
    
    
    /**
     * method that will listen UDP requisitions
     * @param port port that will listen
     * @return data received or null
     */
    public DatagramPacket listenerUDP(int port){
        
        try {
            
            this.datagramSocket = new DatagramSocket(port);
            byte receivedData [] = new byte[1024];
            
            DatagramPacket datagramPacket = new DatagramPacket(receivedData,receivedData.length);
            
            this.datagramSocket.receive(datagramPacket);
            this.datagramSocket.close();
            
                    
            return datagramPacket;        
        } catch (Exception e) {
            return null;
        }
    }// fim do método listenerUDP
    
}
