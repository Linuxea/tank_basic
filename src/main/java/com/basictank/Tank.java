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
    private Direction direction = Direction.STOP;

    private Direction ptDirection = Direction.D;

    private final int TANK_WIDTH = 30;
    private final int TANK_HEIGHT = 30;

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

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.moveStep = 5;
    }

    public Tank(int x, int y, ClientMain clientMain) {
        this.x = x;
        this.y = y;
        this.clientMain = clientMain;
        this.moveStep = 5;
    }

    public Tank(int x, int y, int moveStep) {
        this.x = x;
        this.y = y;
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
        graphics.fillOval(getX(), getY(), TANK_WIDTH, TANK_HEIGHT);
        graphics.setColor(oldColor);

        switch (ptDirection) {
            case L:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX(), getY() + TANK_HEIGHT / 2);
                break;
            case R:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX() + TANK_WIDTH / 2, getY());
                break;
            case U:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX(), getY() - 5);
                break;
            case D:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX(), getY() + 5);
                break;
            case LD:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX() - 10, getY() + 5);
                break;
            case LU:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX() - 10, getY() + 5);
                break;
            case RD:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX() + 10, getY() + 5);
                break;
            case RU:
                graphics.drawLine(getX() + TANK_WIDTH / 2, getY() + TANK_HEIGHT / 2, getX() + 10, getY() - 5);
                break;
            case STOP:
                break;
        }
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
                this.clientMain.getMissleQueue().offer(fire());
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

        if (this.direction != Direction.STOP) {
            this.ptDirection = direction;
        }

        if (x < 0) x = 0;
        if (y < 30) y = 30;
        if (x + TANK_WIDTH > Screen.WIDTH) x = Screen.WIDTH - TANK_WIDTH;
        if (y + TANK_HEIGHT > Screen.HEIGHT) y = Screen.HEIGHT - TANK_HEIGHT;
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
        return new Missle(this.getX(), this.getY(), this.ptDirection);
    }
}
