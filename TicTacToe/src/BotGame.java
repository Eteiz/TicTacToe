import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BotGame extends JFrame {
    private char currentSymbol;
    private char playerSymbol;
    private char botSymbol;
    private JButton[] buttons;
    private JLabel message;
    private boolean gameEnded;
    private int moveCount;

    public BotGame() {

        // Symbols for player and bot on board
        playerSymbol = 'X';
        botSymbol = 'O';
        // Player starts first
        currentSymbol = playerSymbol;
        buttons = new JButton[9];
        gameEnded = false;
        moveCount = 0;

        setTitle("TicTacToe - Against Bot Game");
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
                        if (!gameEnded) {
                            JButton buttonClicked = (JButton) e.getSource();
                            buttonClicked.setText(String.valueOf(currentSymbol));
                            buttonClicked.setEnabled(false);
                            moveCount++;
                            CheckWin();

                            if (!gameEnded && moveCount < 9) {
                                currentSymbol = botSymbol;
                                botMove();
                            }
                        }
                    }
                });
                buttons[j + i*3].setBounds(80 * i + 20, 80 * j, 80, 80);
                add(buttons[j + i*3]);
            }
        }
        message = new JLabel();
        message.setText("Your turn");
        message.setBounds(0,235,300,60);
        message.setFont(new Font("Arial", Font.BOLD, 30));
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        add(message);
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
        String winnerName;
        if (winner == playerSymbol) { winnerName = "Player"; }
        else { winnerName = "Bot"; }

        message.setText(winnerName + " wins!");
        gameEnded = true;
        for(int i = 0; i < 9; i++) { buttons[i].setEnabled(false); }
    }

    public void botMove() {
        // Disable all buttons
        for(int i = 0; i < 9; i++) { buttons[i].setEnabled(false); }
        message.setText("Bot turn");

        // Timer - after 1000 milliseconds of delay caused by clicking button by player
        // function will do things defined down there
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton emptyButton = getRandomEmptyButton();
                emptyButton.setText(String.valueOf(currentSymbol));
                emptyButton.setEnabled(false);
                moveCount++;
                CheckWin();
                if (!gameEnded && moveCount < 9) {
                    currentSymbol = playerSymbol; // Switching back to player's symbol
                }

                // Enable all empty buttons again
                for (int i = 0; i < 9; i++) {
                    if (buttons[i].getText().equals(" ")) { buttons[i].setEnabled(true); }
                }
                message.setText("Your turn");

            }
        });
        // Timer will ony loop once
        timer.setRepeats(false);
        timer.start();
    }

    public JButton getRandomEmptyButton() {
        Random rand = new Random();
        JButton emptyButton;
        do {
            emptyButton = buttons[rand.nextInt(buttons.length)];
        } while(!emptyButton.getText().equals(" "));
        return emptyButton;
    }
}