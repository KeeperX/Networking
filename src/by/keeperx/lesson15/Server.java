package by.keeperx.lesson15;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;




/**
 * Created by Admin on 16.08.2016.
 */
public class Server implements Runnable{
    static ServerSocket server;
    static Socket connection;
    static ObjectOutputStream obOut;
    static ObjectInputStream obIn;
    @Override
    public void run() {
        try {
            server = new ServerSocket(5678,10);

            while(true){
                connection = server.accept();
                obOut= new ObjectOutputStream(connection.getOutputStream());
                obIn = new ObjectInputStream(connection.getInputStream());
                obOut.writeObject((String)obIn.readObject() + "15");

            }




        } catch (Exception e){};
    }
}
