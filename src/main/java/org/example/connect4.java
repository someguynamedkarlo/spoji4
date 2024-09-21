package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class connect4 implements ActionListener {
    private boolean zuti = false;
    private JFrame frame;
    private JPanel panel;
    private CircularButton[][] polje = new CircularButton[6][7]; // 6 redova, 7 stupaca

    public connect4() {
        frame = new JFrame();
        frame.setTitle("Connect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        panel.setLayout(new GridLayout(6, 7)); // 6 redova, 7 stupaca

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                polje[row][col] = new CircularButton(" ");
                polje[row][col].addActionListener(this);
                panel.add(polje[row][col]);
                polje[row][col].setFocusPainted(false);
                polje[row][col].setForeground(Color.WHITE);
                polje[row][col].setBackground(Color.WHITE);
                polje[row][col].setEnabled(false);
            }
        }
        for (int col = 0; col < 7; col++) {
            polje[5][col].setEnabled(true);
        }

        frame.add(panel);
        panel.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 500);
        frame.setUndecorated(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CircularButton button = (CircularButton) e.getSource();
        button.setBackground(Color.green);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (polje[row][col].getBackground() == Color.green) {
                    if (row - 1 > -1) {
                        polje[row - 1][col].setEnabled(true);
                    }
                    polje[row][col].setBackground(Color.white);
                }
            }
        }
        if (zuti) {
            button.setBackground(Color.YELLOW);
        } else {
            button.setBackground(Color.RED);
        }

        button.setEnabled(false);

        zuti = !zuti;
        checkWin();
    }

    public void checkWin() {
        Color boja = polje[0][0].getBackground();
        int pon = 0;
        Color bb = new Color(255, 255, 255);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (equalColors(boja, polje[i][j].getBackground()) && !equalColors(bb, boja)) {
                    pon++;
                } else {
                    pon = 0;
                }
                if (pon == 3) {
                    System.out.println("pobjeda");
                    if (polje[i][j].getBackground() == Color.red) {
                        JOptionPane.showMessageDialog(null, "Pobjeda za crvenog");
                        System.exit(0);
                    } else if (polje[i][j].getBackground() == Color.yellow) {
                        JOptionPane.showMessageDialog(null, "Pobjeda za zutog");
                        System.exit(0);
                    }
                }
                boja = polje[i][j].getBackground();
            }
            pon = 0;
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (equalColors(boja, polje[j][i].getBackground()) && !equalColors(bb, boja)) {
                    pon++;
                } else {
                    pon = 0;
                }
                if (pon == 3) {
                    System.out.println("pobjeda");
                    if (polje[j][i].getBackground() == Color.red) {
                        JOptionPane.showMessageDialog(null, "Pobjeda za crvenog");
                        System.exit(0);
                    } else if (polje[j][i].getBackground() == Color.yellow) {
                        JOptionPane.showMessageDialog(null, "Pobjeda za zutog");
                        System.exit(0);
                    }
                }
                boja = polje[j][i].getBackground();
            }
            pon = 0;
        }
    ///kod


    }


    public boolean equalColors(Color a, Color b) {
        if (a.getRed() == b.getRed() && a.getGreen() == b.getGreen() && a.getBlue() == b.getBlue()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        new connect4();
    }

}





