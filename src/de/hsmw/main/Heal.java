package de.hsmw.main;

import java.awt.*;

public class Heal extends abstractGameObject {
    private final Handler handler;

    public Heal(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        for (int i = 0; i < handler.object.size(); i++){
            abstractGameObject abg = handler.object.get(i);
            if (abg.getId() == ID.Player)
                if (this.getBounds().intersects(abg.getBounds())) {
                    HUD.HEALTH += 40;
                    handler.removeObject(this);
                }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.green);
        g2d.fillOval((int)x, (int)y, 12, 12);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 12, 12);
    }
}
