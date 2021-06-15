package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class Boss extends abstractGameObject{
    private final Handler handler;
    Random r = new Random();

    private int timer = 35;
    private int timer2 = 50;

    public Boss(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = -2;
    }

    @Override
    public void tick() {
        x += velX;
        y -= velY;
        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0) timer2--;

        if(timer2 <= 0){
            if(velX == 0)
                velX = 2;
            int spawn = r.nextInt(7);
            if(spawn == 0) handler.addObject(new BossParticle((int)x+24,(int)y+24, ID.Boss, handler));
        }
        if( x <= 0 || x>= Game.WIDTH - 48)
            velX *=-1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,48,48);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,48,48);
    }


}
