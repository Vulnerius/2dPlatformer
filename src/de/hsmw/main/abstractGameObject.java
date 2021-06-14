package de.hsmw.main;

import java.awt.*;

public abstract class abstractGameObject {
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public abstractGameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setID(ID id){
        this.id = id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public ID getId() {
        return id;
    }
}
