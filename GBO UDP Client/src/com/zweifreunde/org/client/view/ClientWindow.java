package com.zweifreunde.org.client.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ClientWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;
	private static final String TITLE = "1337z Chat Client";

	public ClientWindow(ClientInputView civ, ClientMessageView cmv) {
		// Setting the Layout
		this.setTitle(TITLE);
		this.setLayout(new BorderLayout());
		this.add(cmv, BorderLayout.CENTER);
		this.add(civ, BorderLayout.SOUTH);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
		Image image = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/com/zweifreunde/etc/icon.png"));
		this.setIconImage(image);

	}

}
