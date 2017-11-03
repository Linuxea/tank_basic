package com.basictank;

import lombok.Data;

import java.awt.*;
import java.util.List;

/**
 * Created by Linuxea on 11/3/17.
 */
@Data
public class Missle {

    private Color color;
    private Direction direction;
    private int x;
    private int y;
    private int WIDTH = 10;
    private int HEIGHT = 10;
    private int moveStep = 1;
    private boolean isAlive = true;
    private boolean good;


    public Missle(int x, int y, boolean good, Direction direction) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.good = good;
    }

    public void draw(Graphics graphics) {
        if (isAlive == false) return;
        Color oldColor = graphics.getColor();
        Color color = Color.black;
        graphics.setColor(color);
        graphics.fillOval(x, y, WIDTH, HEIGHT);
        graphics.setColor(oldColor);

        move();
    }

    public void move() {
        switch (direction) {
            case L:
                moveLeft();
                break;
            case R:
                moveRight();
                break;
            case U:
                moveUp();
                break;
            case D:
                moveDown();
                break;
            case STOP:
                moveRight();
                break;
        }

    }

    public boolean isAlive() {
        if (isAlive == false) return false; // 击中坦克的情况下就不必检测是否在视野之外了
        isAlive = getX() >= 0 && getY() >= 0 && getX() <= Screen.WIDTH && getY() <= Screen.HEIGHT;
        return isAlive;
    }

    // 碰撞检测

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean bitTank(Tank tank) {
        //tank.isAlive() &&
        if (this.good != tank.isGoodGuy() && buildRect().intersects(tank.buildRect())) {
            tank.setAlive(false); // tank disappear
            this.setAlive(false); // missle disappear
            Explode explode = new Explode(x, y, tank.getClientMain());
            tank.getClientMain().getExplodes().add(explode);
            return true;
        }
        return false;
    }

    public boolean bitTankList(List<Tank> tankList) {
        for (int i = 0; i < tankList.size(); i++) {
            return bitTank(tankList.get(i));
        }
        return false;
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

    public Rectangle buildRect() {
        return new Rectangle(getX(), getY(), WIDTH, HEIGHT);
    }
}
