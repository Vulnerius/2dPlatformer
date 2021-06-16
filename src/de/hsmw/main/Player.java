package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class Player extends abstractGameObject {
    private Random r = new Random();
    private final Handler handler;
    private HUD hud;

    public Player(float x, float y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.setVelX(0);
        this.setVelY(0);
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.WHITE, 32, 32, 0.15f));
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 64);

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject abg = handler.object.get(i);

            if (abg.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(abg.getBounds())) {
                    hud.setHEALTH(hud.getHEALTH() - 1);
                }
            } else if (abg.getId() == ID.FastEnemy) {
                if (getBounds().intersects(abg.getBounds())) {
                    hud.setHEALTH(hud.getHEALTH() - 2);
                }
            } else if (abg.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(abg.getBounds())) {
                    hud.setHEALTH(hud.getHEALTH() - 3);
                }
            } else if (abg.getId() == ID.Boss) {
                if (getBounds().intersects(abg.getBounds())) {
                    hud.setHEALTH(hud.getHEALTH() - 5);
                }
            }
        }
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.green);
        g2d.draw(getBounds());

        if (id == ID.Player)
            g.setColor(Color.WHITE);
        if (id == ID.Player2)
            g.setColor(Color.blue);

        g.fillRect((int) x, (int) y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}