package com.basictank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by Linuxea on 11/3/17.
 */

public class ClientMain extends Frame {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private Image offScreenImage;
    private Tank myTank = new Tank(30, 20, 800, 600);

    public static void main(String[] args) throws InterruptedException {
        ClientMain main = new ClientMain();
        main.launch();
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
        myTank.draw(g);
    }

    private void launch() throws InterruptedException {
        this.init();
        while (true) {
            repaint();
            TimeUnit.MILLISECONDS.sleep(5);
        }
    }

    private void init(){
        super.setLocation(400, 300);
        super.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        super.setVisible(true);
        super.setResizable(false);
        super.setTitle("tank basic version");
        super.setAlwaysOnTop(true);
        super.setBackground(Color.green);
        super.addKeyListener(new KeyListener());
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }


    /**
     * key listener
     */
    private class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.move(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyRelease(e);
        }
    }

}
