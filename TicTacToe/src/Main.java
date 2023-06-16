import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    JButton botGame,offlineGame,hostGame,joinGame;
    public Main() {

        setTitle("TicTacToe");
        setSize(300, 320);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        botGame = new JButton("Game Against Bot");
        botGame.setBounds(50,20,200,50);
        botGame.addActionListener(this);

        offlineGame = new JButton("Local Game");
        offlineGame.setBounds(50,80,200,50);
        offlineGame.addActionListener(this);

        hostGame = new JButton("Host Game");
        hostGame.setBounds(50,150,200,50);
        hostGame.addActionListener(this);

        joinGame = new JButton("Join Online Game");
        joinGame.setBounds(50,220,200,50);
        joinGame.addActionListener(this);

        add(botGame);
        add(offlineGame);
        add(hostGame);
        add(joinGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(botGame)) {
            new BotGame().setVisible(true);
            dispose();
        }

        if(e.getSource().equals(offlineGame)) {
            new OfflineGame().setVisible(true);
            dispose();
        }

        if(e.getSource().equals(hostGame)) {
            new NetworkServer().setVisible(true);
            dispose();
        }
        if(e.getSource().equals(joinGame)) {
            new NetworkClient().setVisible(true);
            dispose();
        }
    }
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
