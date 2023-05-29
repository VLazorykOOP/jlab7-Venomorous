/*
package Rabbits;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RabbitHabitat extends JPanel {

    private static final int HABITAT_WIDTH = 800;
    private static final int HABITAT_HEIGHT = 400;
    private static final int RABBIT_SIZE = 20;
    private static final int ALBINO_SIZE = 30;
    private static final double RABBIT_SPEED = 150; // pixels/second
    private static final double ALBINO_SPEED = 200; // radians/second

    private Thread ordinaryRabbitThread;
    private Thread albinoThread;

    private OrdinaryRabbit ordinaryRabbit;
    private Albino albino;

    public RabbitHabitat() {
        ordinaryRabbit = new OrdinaryRabbit();
        albino = new Albino();

        // Create threads for the rabbits
        ordinaryRabbitThread = new Thread(ordinaryRabbit);
        albinoThread = new Thread(albino);

        // Start the threads
        ordinaryRabbitThread.start();
        albinoThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable anti-aliasing for smoother rendering
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the habitat
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the ordinary rabbit
        g2d.setColor(Color.GRAY);
        g2d.fill(ordinaryRabbit.getShape());

        // Draw the albino
        g2d.setColor(Color.WHITE);
        g2d.fill(albino.getShape());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(HABITAT_WIDTH, HABITAT_HEIGHT);
    }

    private class OrdinaryRabbit implements Runnable {
        private double x;
        private double y;
        private double destinationX;
        private double destinationY;

        public OrdinaryRabbit() {
            x = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
            y = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
            destinationX = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
            destinationY = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
        }

        public void updatePosition() {
            double dx = destinationX - x;
            double dy = destinationY - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double normalizedDx = dx / distance;
            double normalizedDy = dy / distance;

            double elapsedTime = 0.02; // Adjust the update interval as desired

            if (distance <= RABBIT_SPEED * elapsedTime) {
                x = destinationX;
                y = destinationY;
                destinationX = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
                destinationY = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
            } else {
                x += normalizedDx * RABBIT_SPEED * elapsedTime;
                y += normalizedDy * RABBIT_SPEED * elapsedTime;
            }

            // Wrap around the habitat if the rabbit goes beyond the boundaries
            if (x < 0) {
                x = HABITAT_WIDTH - 1;
            } else if (x >= HABITAT_WIDTH) {
                x = 0;
            }

            if (y < 0) {
                y = HABITAT_HEIGHT - 1;
            } else if (y >= HABITAT_HEIGHT) {
                y = 0;
            }
        }

        public Ellipse2D.Double getShape() {
            return new Ellipse2D.Double(x, y, RABBIT_SIZE, RABBIT_SIZE);
        }

        @Override
        public void run() {
            while (true) {
                updatePosition();
                repaint();

                try {
                    Thread.sleep(20); // Adjust the delay as desired
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Albino implements Runnable {
        private double x;
        private double y;
        private double velocityX;
        private double velocityY;

        public Albino() {
            x = 0;
            y = HABITAT_HEIGHT / 2.0;
            velocityX = 150; // Adjust the initial velocity as desired
            velocityY = 0;
        }

        public void updatePosition() {
            double elapsedTime = 0.02; // Adjust the update interval as desired

            x += velocityX * elapsedTime;
            y += velocityY * elapsedTime;

            if (x <= 0) {
                x = 0;
                velocityX = Math.abs(velocityX); // Reverse the X velocity
            } else if (x >= HABITAT_WIDTH - ALBINO_SIZE) {
                x = HABITAT_WIDTH - ALBINO_SIZE;
                velocityX = -Math.abs(velocityX); // Reverse the X velocity
            }

            if (y <= 0) {
                y = 0;
                velocityY = Math.abs(velocityY); // Reverse the Y velocity
            } else if (y >= HABITAT_HEIGHT - ALBINO_SIZE) {
                y = HABITAT_HEIGHT - ALBINO_SIZE;
                velocityY = -Math.abs(velocityY); // Reverse the Y velocity
            }
        }

        public Ellipse2D.Double getShape() {
            return new Ellipse2D.Double(x, y, ALBINO_SIZE, ALBINO_SIZE);
        }

        @Override
        public void run() {
            while (true) {
                updatePosition();
                repaint();

                try {
                    Thread.sleep(20); // Adjust the delay as desired
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rabbit Habitat");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            RabbitHabitat rabbitHabitat = new RabbitHabitat();

            frame.getContentPane().add(rabbitHabitat);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
*/
package Rabbits;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RabbitHabitat extends JPanel {

    private static final int HABITAT_WIDTH = 800;
    private static final int HABITAT_HEIGHT = 400;
    private static final int RABBIT_SIZE = 20;
    private static final int ALBINO_SIZE = 30;
    private static final double RABBIT_SPEED = 150; // pixels/second
    private static final double ALBINO_SPEED = 200; // radians/second
    private static final int CHANGE_DIRECTION_INTERVAL = 1000; // Change direction every 5 seconds

//    private Timer directionTimer;

    private Thread ordinaryRabbitThread;
    private Thread albinoThread;

    private OrdinaryRabbit ordinaryRabbit;
    private Albino albino;

    public RabbitHabitat() {
        ordinaryRabbit = new OrdinaryRabbit();
        albino = new Albino();

//        directionTimer = new Timer(CHANGE_DIRECTION_INTERVAL, e -> ordinaryRabbit.changeDirection());
        // Create threads for the rabbits
        ordinaryRabbitThread = new Thread(ordinaryRabbit);
        albinoThread = new Thread(albino);

        // Start the threads
        ordinaryRabbitThread.start();
//        directionTimer.start();
        albinoThread.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable anti-aliasing for smoother rendering
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the habitat
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the ordinary rabbit
        g2d.setColor(Color.GRAY);
        ordinaryRabbit.paint(g2d);

        // Draw the albino
        g2d.setColor(Color.WHITE);
        albino.paint(g2d);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(HABITAT_WIDTH, HABITAT_HEIGHT);
    }

    private class OrdinaryRabbit implements Runnable {
        private double x;
        private double y;
        private double destinationX;
        private double destinationY;
        private BufferedImage image;
        private long lastDirectionChangeTime;

        public OrdinaryRabbit() {
            // Load the rabbit image
            try {
                image = ImageIO.read(new File("src/rabbit.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set initial positions
            x = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
            y = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
            destinationX = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
            destinationY = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
            lastDirectionChangeTime = System.currentTimeMillis();
        }

        public void updatePosition() {
            double dx = destinationX - x;
            double dy = destinationY - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double normalizedDx = dx / distance;
            double normalizedDy = dy / distance;

            double elapsedTime = 0.02; // Adjust the update interval as desired

            if (distance <= RABBIT_SPEED * elapsedTime) {
                x = destinationX;
                y = destinationY;
                destinationX = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
                destinationY = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
            } else {
                x += normalizedDx * RABBIT_SPEED * elapsedTime;
                y += normalizedDy * RABBIT_SPEED * elapsedTime;
            }

            // Wrap around the habitat if the rabbit goes beyond the boundaries
            if (x < 0) {
                x = HABITAT_WIDTH - 1;
            } else if (x >= HABITAT_WIDTH) {
                x = 0;
            }

            if (y < 0) {
                y = HABITAT_HEIGHT - 1;
            } else if (y >= HABITAT_HEIGHT) {
                y = 0;
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDirectionChangeTime >= CHANGE_DIRECTION_INTERVAL) {
                lastDirectionChangeTime = currentTime;
                destinationX = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
                destinationY = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
            }
        }

//        public Ellipse2D.Double getShape() {
//            return new Ellipse2D.Double(x, y, RABBIT_SIZE, RABBIT_SIZE);
//        }
        public void paint(Graphics2D g2d) {
            g2d.drawImage(image, (int) x, (int) y, RABBIT_SIZE, RABBIT_SIZE, null);
        }
//        public void changeDirection() {
//            try {
//                Thread.sleep(CHANGE_DIRECTION_INTERVAL);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            destinationX = Math.random() * (HABITAT_WIDTH - RABBIT_SIZE);
//            destinationY = Math.random() * (HABITAT_HEIGHT - RABBIT_SIZE);
//        }
        @Override
        public void run() {
            while (true) {
                updatePosition();
                repaint();

                try {
                    Thread.sleep(20); // Adjust the delay as desired
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Albino implements Runnable {
        private double x;
        private double y;
        private double velocityX;
        private double velocityY;
        private BufferedImage image;

        public Albino() {
            // Load the albino image
            try {
                image = ImageIO.read(new File("src/albino.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set initial positions and velocities
            x = 0;
            y = HABITAT_HEIGHT / 2.0;
            velocityX = 150; // Adjust the initial velocity as desired
            velocityY = 0;
        }

        public void updatePosition() {
            double elapsedTime = 0.02; // Adjust the update interval as desired

            x += velocityX * elapsedTime;
            y += velocityY * elapsedTime;

            if (x <= 0) {
                x = 0;
                velocityX = Math.abs(velocityX); // Reverse the X velocity
            } else if (x >= HABITAT_WIDTH - ALBINO_SIZE) {
                x = HABITAT_WIDTH - ALBINO_SIZE;
                velocityX = -Math.abs(velocityX); // Reverse the X velocity
            }

            if (y <= 0) {
                y = 0;
                velocityY = Math.abs(velocityY); // Reverse the Y velocity
            } else if (y >= HABITAT_HEIGHT - ALBINO_SIZE) {
                y = HABITAT_HEIGHT - ALBINO_SIZE;
                velocityY = -Math.abs(velocityY); // Reverse the Y velocity
            }
        }

        public void paint(Graphics2D g2d) {
            g2d.drawImage(image, (int) x, (int) y, ALBINO_SIZE, ALBINO_SIZE, null);
        }

        @Override
        public void run() {
            while (true) {
                updatePosition();
                repaint();

                try {
                    Thread.sleep(20); // Adjust the delay as desired
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rabbit Habitat");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            RabbitHabitat rabbitHabitat = new RabbitHabitat();

            frame.getContentPane().add(rabbitHabitat);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
