package com.zweifreunde.org.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.zweifreunde.org.client.controller.SendMessageListener;

public class UDPClientSender implements SendMessageListener {
	private int port;
	private DatagramSocket socket;

	public UDPClientSender(int port, DatagramSocket socket)
			throws SocketException {
		this.port = port;
		this.socket = socket;

	}

	@Override
	public void sendMessage(String msg) {
		byte[] b = msg.getBytes();

		try {
			DatagramPacket p = new DatagramPacket(b, b.length,
					InetAddress.getByName("127.0.0.1"), port);

			socket.send(p);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
