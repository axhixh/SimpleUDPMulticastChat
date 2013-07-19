package com.zweifreunde.org.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.zweifreunde.org.client.model.ClientModel;

public class UDPClientReceiver {

	private ClientModel clientModel;
	private DatagramSocket serverSocket;

	public UDPClientReceiver(ClientModel clientModel, DatagramSocket socket) {
		this.clientModel = clientModel;
		this.serverSocket = socket;
	}

	public void startListening() throws SocketException {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("TRUE");
					byte[] buffer = new byte[2048];

					DatagramPacket p = new DatagramPacket(buffer, buffer.length);
					try {
						serverSocket.receive(p);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String msg = new String(p.getData());
					System.out.println(msg);
					clientModel.newMessage(msg);

				}

			}
		});
		t.start();
	}
}
