package com.basictank;

import lombok.Data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Linuxea on 11/3/17.
 */
@Data
public class ClientMain extends Frame {

    ExecutorService executorService = Executors.newCachedThreadPool();

    private Image offScreenImage;
    private Tank myTank = new Tank(50, 50, this, true);
    private List<Tank> tankList = new ArrayList<>();
    private Queue<Missle> missleQueue = new LinkedList<Missle>();
    private Queue<Explode> explodes = new LinkedList<>();

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
        myTank.draw(g);
        Iterator<Missle> iterator = missleQueue.iterator();
        while (iterator.hasNext()) {
            Missle temp = iterator.next();
            if (temp.isAlive()) {
                for (int i = 0; i < tankList.size(); i++) {
                    temp.bitTank(tankList.get(i));
                }
                temp.draw(g);
            } else {
                iterator.remove();
            }
        }

        Iterator<Tank> tankIterator = tankList.iterator();
        while (tankIterator.hasNext()) {
            Tank tank = tankIterator.next();
            if (tank.isAlive()) {
                tank.draw(g);
            } else {
                tankIterator.remove();
            }
        }


        Iterator<Explode> explodeIterator = explodes.iterator();
        while (explodeIterator.hasNext()) {
            Explode temp = explodeIterator.next();
            if (temp.isAlive()) {
                temp.draw(g);
            } else {
                explodeIterator.remove();
            }
        }
    }

    private void launch() throws InterruptedException {
        this.init();
        this.showEnemys();
        while (true) {
            repaint();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    private void init() {
        super.setLocation(400, 300);
        super.setSize(Screen.WIDTH, Screen.HEIGHT);
        super.setVisible(true);
        super.setResizable(false);
        super.setTitle("tank basic version");
        super.setAlwaysOnTop(true);
        super.setBackground(Color.white);
        super.addKeyListener(new KeyListener());
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                executorService.shutdown();
                System.exit(0);
            }
        });

    }

    public void showEnemys() {

        for (int i = 0; i < 10; i++) {
            Tank tank = new Tank(i * 10, i * 40 + 1, this, false);
            tank.setDirection(Direction.D);
            tankList.add(tank);
            tank.makeLocation(); // 这个设计得非常有问题有空再.
            executorService.execute(() -> {
                while (true) {
                    if (tank.isAlive() == false) return;
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
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
