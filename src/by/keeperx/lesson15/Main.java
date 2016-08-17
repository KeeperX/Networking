package by.keeperx.lesson15;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;



/**
 * Created by Admin on 15.08.2016.
 */
public class Main extends JFrame implements Runnable{
static Socket connection;
    static ObjectOutputStream obOut;
    static ObjectInputStream obIn;
    static JFrame jf;
    static JTextField jT;
    static JButton jB;
 public Main(String name){
  super(name);
  setLayout(new FlowLayout());
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     setSize(300,300);
     setLocationRelativeTo(null);
     jT = new JTextField(10);

     jB = new JButton("Send");
     add(jT);
     add(jB);
     jB.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             sendData(jT.getText());
         }
     });
     setVisible(true);
 }



  public static void main(String[] args){
 new Thread( new Main("Test")).start();
      new Thread(new Server()).start();

  }


    @Override
    public void run() {
        try {

            while(true){
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                obOut= new ObjectOutputStream(connection.getOutputStream());
                obIn = new ObjectInputStream(connection.getInputStream());
                JOptionPane.showMessageDialog(null,(String)obIn.readObject());



            }




        } catch (Exception e){};
    }
    private static void sendData(Object obj){
        try {
            obOut.flush();
            obOut.writeObject(obj);
        }
        catch(Exception e){};

    }
}
