package com.basictank;

import java.awt.*;

/**
 * Created by Linuxea on 11/4/17.
 */
public class Explode {

    private int x;
    private int y;
    private boolean isAlive = true;
    private int[] radius = {40, 70, 100, 120, 83, 42, 15, 7, 1, 0};
    private int step;
    private ClientMain clientMain;

    public Explode() {
    }

    public Explode(int x, int y, ClientMain clientMain) {
        this.x = x;
        this.y = y;
        this.clientMain = clientMain;
    }

    public void draw(Graphics graphics) {
        if (isAlive == false) return;

        if (step == radius.length) {
            step = 0;
            isAlive = false;
            return;
        }

        Color oldColor = graphics.getColor();
        Color color = Color.orange;
        graphics.setColor(color);
        graphics.fillOval(x, y, radius[step], radius[step]);
        step++;
        graphics.setColor(oldColor);
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int[] getRadius() {
        return radius;
    }

    public void setRadius(int[] radius) {
        this.radius = radius;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public ClientMain getClientMain() {
        return clientMain;
    }

    public void setClientMain(ClientMain clientMain) {
        this.clientMain = clientMain;
    }
}
