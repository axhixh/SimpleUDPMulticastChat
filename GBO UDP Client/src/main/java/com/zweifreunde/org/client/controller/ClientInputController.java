package com.zweifreunde.org.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zweifreunde.org.client.model.ClientModel;
import com.zweifreunde.org.client.view.ClientInputView;
import com.zweifreunde.org.client.view.ClientNameDialog;
import com.zweifreunde.org.client.view.ClientRoomDialog;
import com.zweifreunde.org.client.view.ClientWindow;

public class ClientInputController implements ActionListener {

    private ClientInputView view;
    private ClientModel model;

    public ClientInputController(ClientModel model, ClientInputView view,
            ClientWindow window) {

        this.view = view;
        this.view.grabFocus();
        this.view.addActionListener(this);
        this.model = model;
        ClientNameDialog dialog = new ClientNameDialog(window);
        String name = dialog.askForName();
        ClientRoomDialog roomDialog = new ClientRoomDialog(window);
        this.model.setRoom(roomDialog.askForRoom());
        this.view.setEditable(true);
        this.model.setName(name);

        window.setTitle(window.getTitle() + ": " + model.getRoom());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClientInputView input = (ClientInputView) e.getSource();
        String msg = input.getText();

        this.model.sendMessage(msg);
        this.view.setText("");
    }

}
