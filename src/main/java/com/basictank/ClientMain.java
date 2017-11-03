package com.basictank;

import lombok.Data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Linuxea on 11/3/17.
 */
@Data
public class ClientMain extends Frame {

    private Image offScreenImage;
    private Tank myTank = new Tank(50, 50, 800, 600, this);

    private Queue<Missle> missles = new LinkedList<Missle>();

    public static void main(String[] args) throws InterruptedException {
        ClientMain main = new ClientMain();
        main.launch();
    }

    @Override
    public void update(Graphics g) { // repaint : update -> paint
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Screen.WIDTH, Screen.HEIGHT);
        }

        Graphics graphics = offScreenImage.getGraphics(); // off graphics
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
        offScreenImage = null;
    }

    @Override
    public void paint(Graphics g) {
        System.out.println(missles.size());
        Iterator<Missle> iterator = missles.iterator();
        while (iterator.hasNext()) {
            Missle temp = iterator.next();
            if (temp.isAlive()) {
                temp.draw(g);
            } else {
                iterator.remove();
            }
        }
        myTank.draw(g);
    }

    private void launch() {
        this.init();
        while (true) {
            repaint();
        }
    }

    private void init(){
        super.setLocation(400, 300);
        super.setSize(Screen.WIDTH, Screen.HEIGHT);
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
