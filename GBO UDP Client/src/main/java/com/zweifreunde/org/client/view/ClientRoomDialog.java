package com.zweifreunde.org.client.view;

import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class ClientRoomDialog {

    private static final long serialVersionUID = 1337L;
    private static final Object QUESTION = ResourceBundle.getBundle("com/zweifreunde/etc/Messages").getString("Which room?");
    private static final String TITLE = ResourceBundle.getBundle("com/zweifreunde/etc/Messages").getString("Room");
    private ClientWindow window;

    public ClientRoomDialog(ClientWindow window) {
        this.window = window;
    }

    public int askForRoom() {
        boolean valid = false;
        int roomNo = 0;
        do {
            String room = JOptionPane.showInputDialog(this.window, QUESTION, TITLE,
                    JOptionPane.PLAIN_MESSAGE);
            if (room == null || room.trim().length() == 0) {
                valid = true;
            } else {
                try {
                    int no = Integer.parseInt(room);
                    if (no < 10000 && no > 0) {
                        roomNo = no;
                        valid = true;
                    }
                } catch (NumberFormatException err) {

                }
            }

        } while (!valid);

        return roomNo;
    }
}
