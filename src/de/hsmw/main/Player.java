package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class Player extends abstractGameObject {
    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x,0,Game.WIDTH-32);
        y = Game.clamp(y,0,Game.HEIGHT-64);

        collision();
    }

    private void collision() {
        for(abstractGameObject abg : handler.object){
            if(abg.getId() == ID.BasicEnemy || abg.getId() == ID.Trail){
                if(getBounds().intersects(abg.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
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

        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

}