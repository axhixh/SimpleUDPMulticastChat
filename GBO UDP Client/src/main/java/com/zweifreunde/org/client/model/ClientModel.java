package com.zweifreunde.org.client.model;

import java.util.ArrayList;

import com.zweifreunde.org.client.controller.NewMessagesListener;
import com.zweifreunde.org.client.controller.SendMessageListener;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ClientModel {

    private static final String WHO = "!WHO";

    private ArrayList<NewMessagesListener> newMsgListeners;
    private ArrayList<SendMessageListener> sendMsgListener;

    private String ID;

    private int roomNo;

    public ClientModel() {
        this.newMsgListeners = new ArrayList<>();
        this.sendMsgListener = new ArrayList<>();

        this.ID = this.toString().substring(this.toString().indexOf("@") + 1);

    }

    public void addNewMessageListener(NewMessagesListener listener) {
        synchronized (this.newMsgListeners) {
            this.newMsgListeners.add(listener);
        }
    }

    private void notifyListeners(String msg) {
        synchronized (this.newMsgListeners) {
            for (NewMessagesListener l : this.newMsgListeners) {
                l.newMessage(msg);
            }
        }
    }

    public void sendHello() {
        String hasJoinedMessage = ResourceBundle.getBundle("com/zweifreunde/etc/Messages").getString("joined");
        send(MessageFormat.format(hasJoinedMessage, getName()));
    }

    public void sendWho() {
        send(WHO);
    }

    public void sendMessage(String newMsg) {
        newMsg = newMsg.trim();
        if ("".equals(newMsg)) {
            return;
        }

        if (WHO.equalsIgnoreCase(newMsg)) {
            sendWho();
        } else {
            send(getName() + ": " + newMsg);
        }
    }

    private void send(String newMsg) {
        synchronized (this.sendMsgListener) {
            for (SendMessageListener l : this.sendMsgListener) {
                l.sendMessage(newMsg);
            }
        }
    }

    public void newMessage(String msg) {
        if (WHO.equals(msg)) {
            send(MessageFormat.format(ResourceBundle.getBundle("com/zweifreunde/etc/Messages").getString("user online"), getName()));
        } else {
            notifyListeners(msg);
        }
    }

    public void addSendMessageListener(SendMessageListener listener) {
        synchronized (this.sendMsgListener) {
            this.sendMsgListener.add(listener);
        }
    }

    public void removeSendMessageListener(SendMessageListener listener) {
        synchronized (this.sendMsgListener) {
            this.sendMsgListener.remove(listener);
        }
    }

    public void setName(String name) {
        this.ID = name;
    }

    public String getName() {
        return this.ID;
    }

    public void setRoom(int room) {
        this.roomNo = room;
    }

    public int getRoom() {
        return roomNo;
    }
}
