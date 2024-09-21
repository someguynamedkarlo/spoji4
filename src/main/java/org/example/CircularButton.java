package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircularButton extends JButton {
    public CircularButton(String label) {
        super(label);
        // Make the button content area non-rectangular
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public boolean contains(int x, int y) {
        // Only consider clicks inside the circular area
        Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return shape.contains(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Circular Buttons Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Use a JPanel to ensure square buttons
            JPanel panel = new JPanel(new GridLayout(5, 5, 5, 5)) {
                @Override
                public Dimension getPreferredSize() {
                    Dimension d = super.getPreferredSize();
                    int size = Math.min(d.width, d.height);
                    return new Dimension(size, size);
                }
            };

            for (int i = 1; i <= 25; i++) {
                panel.add(new CircularButton("Button " + i));
            }

            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
