package com.zweifreunde.org.client.view;

import java.awt.Insets;
import java.io.IOException;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.zweifreunde.org.client.controller.NewMessagesListener;
import java.text.DateFormat;

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

    private void addMessage(String msg) {
        try {
            this.kit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
        } catch (BadLocationException | IOException e) {
        }
    }

    @Override
    public void newMessage(String msg) {

        this.addMessage(String.format("%s %s", this.getHTMLTime(), msg));

    }

    private String getTime() {
        DateFormat df = DateFormat.getTimeInstance();
        return df.format(new Date());
    }

    private String getHTMLTime() {
        return "<a style='color: #999999'>&lt;" + getTime() + "&gt;</a>";
    }
}
