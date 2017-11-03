package com.basictank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by Linuxea on 11/3/17.
 */

public class ClientMain extends Frame {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    private static final int TANK_START_X = 50;
    private static final int TANK_START_Y = 50;

    private static int TANK_WIDTH = 30;
    private static int TANK_HEIGHT = 30;

    private static int TANK_OFFSET_X = 0;
    private static int TANK_OFFSET_Y = 0;

    private Image offScreenImage;

    public static void main(String[] args) throws InterruptedException {
        ClientMain main = new ClientMain();
        main.launch();

        while (true) {
            main.repaint();
            TimeUnit.MILLISECONDS.sleep(500);
        }

    }

    @Override
    public void update(Graphics g) { // repaint : update -> paint
        if (offScreenImage == null) {
            offScreenImage = this.createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        }

        Graphics graphics = offScreenImage.getGraphics(); // off graphics
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
        offScreenImage = null;
    }

    @Override
    public void paint(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(Color.red);
        g.fillOval(TANK_START_X + TANK_OFFSET_X, TANK_START_Y + TANK_OFFSET_Y, TANK_WIDTH, TANK_HEIGHT);
        g.setColor(oldColor);

        this.move();
    }

    private void move() {
        ClientMain.TANK_OFFSET_X += 10;
        ClientMain.TANK_OFFSET_Y += 30;
    }

    private void launch() {
        this.init();
    }

    private void init(){
        super.setLocation(400, 300);
        super.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        super.setVisible(true);
        super.setResizable(false);
        super.setTitle("tank basic version");
        super.setAlwaysOnTop(true);
        super.setBackground(Color.green);
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }

}
