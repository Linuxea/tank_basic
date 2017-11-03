package com.basictank;

import lombok.Data;

import java.awt.*;

/**
 * Created by Linuxea on 11/3/17.
 */
@Data
public class Missle {

    private Color color;
    private Shape shape;
    private Direction direction;
    private int x;
    private int y;
    private int moveStep = 1;


    public Missle(int x, int y, Direction direction) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics graphics) {
        Color oldColor = graphics.getColor();
        Color color = Color.black;
        graphics.setColor(color);
        graphics.fillOval(x, y, 10, 10);
        graphics.setColor(oldColor);

        move();
    }

    void move() {
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


}
