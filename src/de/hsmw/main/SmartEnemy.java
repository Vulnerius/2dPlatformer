package de.hsmw.main;

import java.awt.*;

public class SmartEnemy extends abstractGameObject {
    private final Handler handler;
    private abstractGameObject player;
    private HUD hud;

    public SmartEnemy(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId().equals(ID.Player))
                player = handler.object.get(i);
        }
    }

    @Override
    public void tick() {
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.magenta, 16, 16, 0.078f));

        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        this.setVelX((-1 / distance) * diffX);
        this.setVelY((-1 / distance) * diffY);

        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 48)
            velX *= -1;

        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject ago = handler.object.get(i);
            if (ago.getId().equals(ID.SmartEnemy) && !(ago.equals(this)))
                if (getBounds().intersects(ago.getBounds())) {
                    handler.removeObject(this);
                    hud.setEnemies(hud.getEnemies() - 1);
                }
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
