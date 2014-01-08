package com.zweifreunde.org.client.model;

import java.util.ArrayList;

import com.zweifreunde.org.client.controller.NewMessagesListener;
import com.zweifreunde.org.client.controller.SendMessageListener;

public class ClientModel {
	private ArrayList<NewMessagesListener> newMsgListeners;
	private ArrayList<SendMessageListener> sendMsgListener;

	private String ID;

	public ClientModel() {
		this.newMsgListeners = new ArrayList<NewMessagesListener>();
		this.sendMsgListener = new ArrayList<SendMessageListener>();

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

	public void sendMessage(String newMsg) {
		if (!"".equals(newMsg)) {
			synchronized (this.sendMsgListener) {
				for (SendMessageListener l : this.sendMsgListener) {
					l.sendMessage(this.getName() + ": " + newMsg);
				}
			}
		}
	}

	public void newMessage(String msg) {
		notifyListeners(msg);
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
}
