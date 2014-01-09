package com.zweifreunde.org.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.SwingUtilities;

import com.zweifreunde.org.client.controller.SendMessageListener;
import com.zweifreunde.org.client.model.ClientModel;

public class UDPMulticastClient implements SendMessageListener {

    private MulticastSocket socket;
    private InetAddress addr;
    private int port;
    private ClientModel model;

    public UDPMulticastClient(ClientModel model, int port) throws IOException {
        this.socket = new MulticastSocket(1337);
        this.addr = InetAddress.getByName("238.254.254.254");
        this.port = port;
        this.socket.joinGroup(this.addr);
        this.model = model;
        this.model.addSendMessageListener(this);
    }

    @Override
    public void sendMessage(String msg) {
        DatagramPacket packet = new DatagramPacket(msg.getBytes(),
                msg.getBytes().length, this.addr, this.port);
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void startListening() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    byte[] b = new byte[2048];
                    DatagramPacket read = new DatagramPacket(b, b.length);
                    try {
                        socket.receive(read);
                    } catch (IOException e) {
                        System.out.println("Error receiving.");
                    }
                    String msg = new String(read.getData());
                    SwingUtilities.invokeLater(new MsgRunnable(model, msg));
                }

            }
        });
        thread.setDaemon(true);
        thread.start();

    }

    class MsgRunnable implements Runnable {

        private String msg;
        private ClientModel model;

        public MsgRunnable(ClientModel model, String msg) {
            this.model = model;
            this.msg = msg;
        }

        @Override
        public void run() {
            this.model.newMessage(this.msg.trim());

        }

    }
}
