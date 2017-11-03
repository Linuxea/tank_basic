package com.basictank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Linuxea on 11/3/17.
 */

public class Tank {

    private int x;
    private int y;
    private int moveStep;
    private boolean goodGuy;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, int moveStep) {
        this.x = x;
        this.y = y;
        this.moveStep = moveStep;
    }

    public void moveLeft() {
        this.x -= moveStep;
    }

    public void moveRight() {
        this.x += moveStep;
    }

    public void moveUp() {
        this.y -= moveStep;
    }

    public void moveDown() {
        this.y += moveStep;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMoveStep() {
        return moveStep;
    }

    public void setMoveStep(int moveStep) {
        this.moveStep = moveStep;
    }

    public boolean isGoodGuy() {
        return goodGuy;
    }

    public void setGoodGuy(boolean goodGuy) {
        this.goodGuy = goodGuy;
    }

    public void draw(Graphics graphics) {
        Color oldColor = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.fillOval(getX(), getY(), 30, 30);
        graphics.setColor(oldColor);
    }

    public void move(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
        }
    }
}
