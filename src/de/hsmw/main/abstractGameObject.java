package de.hsmw.main;

import java.awt.*;

public abstract class abstractGameObject {
    protected float x, y;
    protected ID id;
    protected float velX, velY;

    public abstractGameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();


    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void setID(ID id){
        this.id = id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public ID getId() {
        return id;
    }
}
