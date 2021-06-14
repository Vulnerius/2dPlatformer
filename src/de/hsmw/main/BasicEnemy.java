package de.hsmw.main;

import java.awt.*;

public class BasicEnemy extends abstractGameObject{
    private Handler handler;
    private ID id;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.id = id;
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        handler.addObject(new Trail(x,y,ID.Trail, handler, Color.red, 16,16,0.025f));
        x += velX;
        y += velY;
        if( y <= 0 || y>= Game.HEIGHT - 32)
            velY *=-1;
        if( x <= 0 || x>= Game.WIDTH - 32)
            velX *=-1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x,y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
