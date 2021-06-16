package de.hsmw.main;

import java.awt.*;

public class BasicEnemy extends abstractGameObject{
    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.setVelX(5);
        this.setVelY(4);
        this.handler = handler;

    }

    @Override
    public void tick() {
        handler.addObject(new Trail(x,y,ID.Trail, handler, Color.red, 16,16,0.032f));
        x += velX;
        y += velY;
        if( y <= 0 || y>= Game.HEIGHT - 48)
            velY *=-1;
        if( x <= 0 || x>= Game.WIDTH - 64)
            velX *=-1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
