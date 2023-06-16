import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer extends JFrame {
    private JButton[] buttons = new JButton[9];
    private boolean isPlayerTurn = true;
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private JLabel infoLabel;

    public NetworkServer() {

        setTitle("TicTacToe - Player X, Online Game");
        setSize(300, 320);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[j + i * 3] = new JButton();
                buttons[j + i * 3].setFocusable(false);
                buttons[j + i * 3].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isPlayerTurn) {
                            JButton button = (JButton) e.getSource();
                            button.setText("X");
                            button.setEnabled(false);
                            int index = findButtonIndex(button);
                            output.println("MOVE:" + index);
                            checkGameOver();
                            switchPlayer();
                        }
                    }
                });
                buttons[j + i*3].setBounds(80 * i + 20, 80 * j, 80, 80);
                add(buttons[j + i*3]);
            }
        }
        infoLabel = new JLabel("X Turn");
        infoLabel.setBounds(0,235,300,60);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(infoLabel);

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            clientSocket = serverSocket.accept();
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            // New thread to listen to client's output
            new Thread(new ClientListener()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchPlayer() {
        isPlayerTurn = !isPlayerTurn;

        if (isPlayerTurn) { infoLabel.setText("X Turn"); }
        else { infoLabel.setText("O Turn"); }
    }

    private int findButtonIndex(JButton button) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == button) { return i; }
        }
        return -1;
    }

    private void checkGameOver() {
        // Sprawdzanie warunków wygranej dla X i O
        String[] winningConditions = new String[] { "012", "345", "678", "036", "147", "258", "048", "246" };

        for (String condition : winningConditions) {
            if (buttons[Character.getNumericValue(condition.charAt(0))].getText().equals("X") &&
                    buttons[Character.getNumericValue(condition.charAt(1))].getText().equals("X") &&
                    buttons[Character.getNumericValue(condition.charAt(2))].getText().equals("X")) {
                infoLabel.setText("Gracz X wygrywa!");
                blockButtons();
                return;
            } else if (buttons[Character.getNumericValue(condition.charAt(0))].getText().equals("O") &&
                    buttons[Character.getNumericValue(condition.charAt(1))].getText().equals("O") &&
                    buttons[Character.getNumericValue(condition.charAt(2))].getText().equals("O")) {
                infoLabel.setText("Gracz O wygrywa!");
                blockButtons();
                return;
            }
        }

        // Sprawdzanie warunków remisu
        boolean isDraw = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                isDraw = false;
                break;
            }
        }
        if (isDraw) {
            infoLabel.setText("Remis!");
            blockButtons();
        }
    }

    private void blockButtons() {
        for (int i = 0; i < 9; i++) { buttons[i].setEnabled(false); }
    }

    private class ClientListener implements Runnable {
        @Override
        public void run() {
            try {
                String receivedMessage;
                while ((receivedMessage = input.readLine()) != null) {
                    // Messages are divided by :
                    String[] parts = receivedMessage.split(":");
                    // If the lenght's is 2, it means the message is correct
                    if (parts.length == 2) {
                        // Button is updated
                        int index = Integer.parseInt(parts[1]);
                        buttons[index].setText("O");
                        buttons[index].setEnabled(false);
                        checkGameOver();
                        switchPlayer();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}