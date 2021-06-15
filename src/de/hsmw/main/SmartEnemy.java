package de.hsmw.main;

import java.awt.*;

public class SmartEnemy extends abstractGameObject {
    private Handler handler;
    private abstractGameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player)
                player = handler.object.get(i);
        }

    }

    @Override
    public void tick() {
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.magenta, 16, 16, 0.083f));
        x += velX;
        y += velY;

        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = ((-1 / distance) * diffX);
        velY = ((-1 / distance) * diffY);

        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32)
            velX *= -1;
        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject ago = handler.object.get(i);
            if (ago.getId() == ID.SmartEnemy && !(ago.equals(this)))
                if (getBounds().intersects(ago.getBounds()))
                    handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
