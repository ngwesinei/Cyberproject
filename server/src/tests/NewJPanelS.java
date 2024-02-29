/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ei
 */
public class NewJPanelS extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanelS
     */
    private static Socket socket;

    public NewJPanelS() {
        try {
            initComponents();

            backgroundListening();
        } catch (Exception ex) {
            Logger.getLogger(NewJPanelS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_recieveMessage = new javax.swing.JTextArea();
        btn_Receive = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        txt_recieveMessage.setColumns(20);
        txt_recieveMessage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_recieveMessage.setRows(5);
        jScrollPane1.setViewportView(txt_recieveMessage);

        btn_Receive.setBackground(new java.awt.Color(51, 51, 0));
        btn_Receive.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Receive.setForeground(new java.awt.Color(255, 255, 255));
        btn_Receive.setText("Read");
        btn_Receive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReceiveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Message Receive");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Receive)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)))
                .addGap(10, 10, 10)
                .addComponent(btn_Receive)
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ReceiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReceiveActionPerformed
        // TODO add your handling code here:
        // listeningMessage();
        readingMessageByEncryptor();

    }//GEN-LAST:event_btn_ReceiveActionPerformed

    public void readingMessageByEncryptor() {
        String str_message = txt_recieveMessage.getText();

        if (!str_message.equals("")) {

            String str_key = JOptionPane.showInputDialog(this, "Enter Key: ");
            if (str_key.equals("")) {

            } else {
                if (str_key.length() == 16) {
                    try {
                        str_message = Encryptor.decrypt(str_key, "eingwesin0000000", str_message);
                        String receiveMessage = str_message + "\n";

                        txt_recieveMessage.setText(receiveMessage);
                    } catch (Exception ex) {
                        Logger.getLogger(NewJPanelS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    readingMessageByEncryptor();
                }

            }

        }

    }

    public void backgroundListening() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    int port = 25000;
                    ServerSocket serverSocket = new ServerSocket(port);
                    //Server is running always. This is done using this while(true) loop
                    while (true) {
                        //Reading the message from the client

                        if (socket == null || !socket.isConnected() || socket.isClosed()) {

                            socket = serverSocket.accept();
                            System.out.println("Client has connected!");

                        }

                        InputStream is = socket.getInputStream();

                        InputStreamReader isr = new InputStreamReader(is);

                        BufferedReader br = new BufferedReader(isr);

                        if (br.ready()) {
                            String number = br.readLine();
                            System.out.println("Message received from client is " + number);
                            if (number != null) {
                                txt_recieveMessage.setText(number);
                            }

                        }

//                        OutputStream os = socket.getOutputStream();
//                        OutputStreamWriter osw = new OutputStreamWriter(os);
//                        BufferedWriter bw = new BufferedWriter(osw);
//                        bw.write(number);
//                        System.out.println("Message sent to the client is " + number);
//                        bw.flush();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    /**
     * Not Using old codes*
     */
    public void readingMessageByEncryptor1() {
//         String str_message=txt_recieveMessage.getText();
//          
//            if(!str_message.equals("")){
//                
//                String str_key=JOptionPane.showInputDialog("Enter Key: ");
//                if(str_key.equals("")){
//                    
//                }else{
//                    try {
//                        byte[] key=str_key.getBytes(StandardCharsets.UTF_8);
//                        byte[] plainText=str_message.getBytes(StandardCharsets.UTF_8);
//                       
//                        Encryptor1 obj2=new Encryptor1(key);
//                        
//                        // AESEncryptDecrypt obj=new AESEncryptDecrypt(key);
//                       //  AESEncryptDecrypt obj=new AESEncryptDecrypt(key);
//                        System.out.println("This is before Read press ="+plainText);
//                        byte[] ciphterText=obj2.decrypt(plainText);
//                        str_message=ciphterText.toString();
//                        System.out.println("This is after Read press ="+str_message);
//                        String sendMessage = str_message + "\n";
//                        txt_sendMessage.setText(sendMessage);
//                    } catch (Exception ex) {
//                        Logger.getLogger(NewJPanelS.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//               
//            }
    }

    public void listeningMessage() {
//               
//         try
//    {
//        int port = 25000;
//        ServerSocket serverSocket = new ServerSocket(port);
//       
//       
//            //Reading the message from the client
//            socket = serverSocket.accept();
//            System.out.println("Client has connected!");
//            InputStream is = socket.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//            String receiveMessage = br.readLine();
//            System.out.println("Message received from client is "+receiveMessage);
//
//          
//            String returnMessage = receiveMessage + "\n";
//            txt_recieveMessage.setText(returnMessage);
//
////            //Sending the response back to the client.
////            OutputStream os = socket.getOutputStream();
////            OutputStreamWriter osw = new OutputStreamWriter(os);
////            BufferedWriter bw = new BufferedWriter(osw);
////            bw.write(returnMessage);
////            System.out.println("Message sent to the client is "+returnMessage);
////            bw.flush();
//        
//    }
//    catch (Exception e)
//    {
//        e.printStackTrace();
//    }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Receive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea txt_recieveMessage;
    // End of variables declaration//GEN-END:variables
}