package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class BossParticle extends abstractGameObject {

    private final Handler handler;
    Random r = new Random();

    public BossParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    @Override
    public void tick() {
        handler.addObject(new Trail(x, y, ID.Trail, handler, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), 16, 16, 0.032f));
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}

