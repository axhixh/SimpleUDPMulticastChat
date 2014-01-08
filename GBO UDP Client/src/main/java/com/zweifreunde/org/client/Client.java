package com.zweifreunde.org.client;

import java.io.IOException;
import java.rmi.NotBoundException;

import com.zweifreunde.org.client.controller.ClientInputController;
import com.zweifreunde.org.client.model.ClientModel;
import com.zweifreunde.org.client.view.ClientInputView;
import com.zweifreunde.org.client.view.ClientMessageView;
import com.zweifreunde.org.client.view.ClientWindow;
import com.zweifreunde.org.multicast.UDPMulticastClient;

public class Client {
	public static void main(String[] args) throws NotBoundException,
			IOException {

		ClientModel clientModel = new ClientModel();

		ClientInputView civ = new ClientInputView();
		ClientMessageView cmv = new ClientMessageView();
		ClientWindow window = new ClientWindow(civ, cmv);

		ClientInputController cIController = new ClientInputController(
				clientModel, civ, window);
		// Registriere ClientMessagesView f��r neue Nachrichten
		clientModel.addNewMessageListener(cmv);

		// UDP standard
		// DatagramSocket socket = new DatagramSocket();
		// clientModel.addSendMessageListener(new UDPClientSender(1337,
		// socket));
		// UDPClientReceiver receiver = new UDPClientReceiver(clientModel,
		// socket);
		// receiver.startListening();

		// UDP Multicast
		UDPMulticastClient client = new UDPMulticastClient(clientModel, 1337);
		client.startListening();

	}
}
