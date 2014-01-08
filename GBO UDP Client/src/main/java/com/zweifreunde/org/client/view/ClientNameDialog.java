package com.zweifreunde.org.client.view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ClientNameDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;;
	private static final Object QUESTION = "Wie ist dein Name?";
	private static final String TITLE = "Login";
	private ClientWindow window;

	public ClientNameDialog(ClientWindow window) {
		this.window = window;
	}

	public String askForName() {
		String name = null;
		while (name == null) {
			name = JOptionPane.showInputDialog(this.window, QUESTION, TITLE,
					JOptionPane.PLAIN_MESSAGE);
		}
		return name;
	}
}
