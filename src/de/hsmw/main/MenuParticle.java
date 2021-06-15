package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends abstractGameObject {

    private final Handler handler;
    private Random r = new Random();
    private Color col;

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = (r.nextInt(5 - -5) + -5);

        if (velX == 0) velX = 1;
        if (velY == 0) velY = 1;
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

    }

    @Override
    public void tick() {
        handler.addObject(new Trail(x, y, ID.Trail, handler, col, 16, 16, 0.032f));
        x += velX;
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32)
            velX *= -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}

