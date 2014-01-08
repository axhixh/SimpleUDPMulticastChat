package com.zweifreunde.org.client;

import java.io.IOException;
import java.rmi.NotBoundException;

import com.zweifreunde.org.client.controller.ClientInputController;
import com.zweifreunde.org.client.model.ClientModel;
import com.zweifreunde.org.client.view.ClientInputView;
import com.zweifreunde.org.client.view.ClientMessageView;
import com.zweifreunde.org.client.view.ClientWindow;
import com.zweifreunde.org.multicast.UDPMulticastClient;
import java.text.MessageFormat;
import java.util.ResourceBundle;

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

        // UDP Multicast
        UDPMulticastClient client = new UDPMulticastClient(clientModel, 1337);
        client.startListening();
        String hasJoinedMessage = ResourceBundle.getBundle("com/zweifreunde/etc/Messages").getString("joined");
        client.sendMessage(MessageFormat.format(hasJoinedMessage, clientModel.getName()));
    }
}
