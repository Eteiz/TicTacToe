

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OfflineGame extends JFrame {
    private char currentSymbol;
    private JButton[] buttons;
    private JLabel message;
    private boolean isPlayerTurn;
    private boolean gameEnded;
    private int moveCount;

    public OfflineGame() {

        currentSymbol = 'X';
        buttons = new JButton[9];
        isPlayerTurn = true;
        gameEnded = false;
        moveCount = 0;

        setTitle("TicTacToe - Offline Game");
        setSize(300, 320);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[j + i*3] = new JButton(" ");
                buttons[j + i*3].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isPlayerTurn) {
                            JButton buttonClicked = (JButton) e.getSource();
                            buttonClicked.setText(String.valueOf(currentSymbol));
                            buttonClicked.setEnabled(false);

                            // Increasing moveCount with each move
                            moveCount++;
                            // Checking for win or draw before switching player
                            CheckWin();
                            if (!gameEnded) { switchPlayer(); }
                        }
                    }
                });
                buttons[j + i*3].setBounds(80 * i + 20, 80 * j, 80, 80);
                add(buttons[j + i*3]);
            }
        }
        message = new JLabel();
        // First turn is X's
        message.setText("X Turn");
        message.setBounds(0,235,300,60);
        message.setFont(new Font("Arial", Font.BOLD, 30));
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        add(message);
    }

    public void switchPlayer() {
        if (currentSymbol == 'X') { currentSymbol = 'O'; }
        else { currentSymbol = 'X'; }
        message.setText(currentSymbol + " Turn");
    }

    public void CheckWin() {
        // Check horizontal lines.
        for (int i = 0; i < 9; i += 3) {
            if (CheckLine(i, i + 1, i + 2)) EndGame(buttons[i].getText().charAt(0));
        }
        // Check vertical lines.
        for (int i = 0; i < 3; i++) {
            if (CheckLine(i, i + 3, i + 6)) EndGame(buttons[i].getText().charAt(0));
        }
        // Check the diagonals.
        if (CheckLine(0, 4, 8) || CheckLine(2, 4, 6)) EndGame(buttons[4].getText().charAt(0));

        // Check for draw. If all fields are filled and no winner
        if(moveCount == 9 && !gameEnded) {
            message.setText("It's a Draw!");
            gameEnded = true;
        }
    }

    public boolean CheckLine(int a, int b, int c) {
        return buttons[a].getText().equals(buttons[b].getText())
                && buttons[a].getText().equals(buttons[c].getText())
                && !buttons[a].getText().equals(" ");
    }

    public void EndGame(char winner) {
        message.setText(winner + " wins!");
        gameEnded = true; // Game has ended

        // Turning off all buttons to be unclickable
        for (JButton button : buttons) { button.setEnabled(false); }
    }
}