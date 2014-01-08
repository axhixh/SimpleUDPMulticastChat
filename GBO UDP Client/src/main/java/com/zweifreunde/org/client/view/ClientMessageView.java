package com.zweifreunde.org.client.view;

import java.awt.Insets;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.zweifreunde.org.client.controller.NewMessagesListener;

public class ClientMessageView extends JScrollPane implements
		NewMessagesListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;
	private JEditorPane textArea;
	private HTMLEditorKit kit;
	private HTMLDocument doc;

	public ClientMessageView() {
		this.textArea = new JEditorPane("text/html", null);
		this.kit = new HTMLEditorKit();
		this.doc = new HTMLDocument();
		this.textArea.setEditorKit(kit);
		this.textArea.setDocument(doc);
		this.textArea.setEditable(false);
		this.textArea.setMargin(new Insets(10, 10, 10, 10));

		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.setViewportView(this.textArea);
	}

	public void addMessage(String msg) {
		try {
			this.kit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
		} catch (BadLocationException | IOException e) {
		}
	}

	@Override
	public void newMessage(String msg) {

		this.addMessage(String.format("%s %s", this.getHTMLTime(), msg));

	}

	public String getTime() {
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		return formatTime.format(now);
	}

	public String getHTMLTime() {
		return "<a style='color: #999999'>&lt;" + getTime() + "&gt;</a>";
	}
}
