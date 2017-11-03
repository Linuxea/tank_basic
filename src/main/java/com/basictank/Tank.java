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
    private Direction direction = Direction.R;

    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;

    private ClientMain clientMain;


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

    boolean left;
    boolean right;
    boolean up;
    boolean down;

    public Tank(int x, int y, int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.x = x;
        this.y = y;
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.moveStep = 5;
    }

    public Tank(int x, int y, int SCREEN_WIDTH, int SCREEN_HEIGHT, ClientMain clientMain) {
        this.x = x;
        this.y = y;
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.clientMain = clientMain;
        this.moveStep = 5;
    }

    public Tank(int x, int y, int SCREEN_WIDTH, int SCREEN_HEIGHT, int moveStep) {
        this.x = x;
        this.y = y;
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.moveStep = moveStep;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void draw(Graphics graphics) {
        Color oldColor = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.fillOval(getX(), getY(), 30, 30);
        graphics.setColor(oldColor);
    }

    public void moveLeftUp() {
        this.x -= moveStep;
        this.y -= moveStep;
    }

    public void moveLeftDown() {
        this.x -= moveStep;
        this.y += moveStep;
    }

    public void moveRightUp() {
        this.x += moveStep;
        this.y -= moveStep;
    }

    public void moveRightDown() {
        this.x += moveStep;
        this.y += moveStep;
    }

    public void move(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_CONTROL:
                this.clientMain.setMissle(fire());
                break;
        }
        makeLocation();
    }

    void makeLocation() {
        if (left && up && !right && !down) {
            direction = Direction.LU;
        } else if (left && !up && !right && down) {
            direction = Direction.LD;
        } else if (right && up && !left && !down) {
            direction = Direction.RU;
        } else if (right && !up && !left && down) {
            direction = Direction.RD;
        } else if (left) {
            direction = Direction.L;
        } else if (right) {
            direction = Direction.R;
        } else if (up) {
            direction = Direction.U;
        } else if (down) {
            direction = Direction.D;
        } else if (!left && !right && !up && !down) {
            direction = Direction.STOP;
        }

        System.out.println(direction);

        switch (direction) {
            case L:
                moveLeft();
                left = true;
                break;
            case R:
                moveRight();
                right = true;
                break;
            case U:
                moveUp();
                up = true;
                break;
            case D:
                moveDown();
                down = true;
                break;
            case LD:
                moveLeftDown();
                left = true;
                down = true;
                break;
            case LU:
                moveLeftUp();
                left = true;
                up = true;
                break;
            case RD:
                moveRightDown();
                right = true;
                down = true;
                break;
            case RU:
                moveRightUp();
                right = true;
                up = true;
                break;
            case STOP:
                break;
        }

    }

    public void keyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    /**
     * open fire
     *
     * @return
     */
    public Missle fire() {
        return new Missle(this.getX(), this.getY(), this.getDirection());
    }
}
