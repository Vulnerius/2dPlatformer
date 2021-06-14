package de.hsmw.main;

import java.awt.*;

public class HUD {
    public static int HEALTH = 100;

    public void tick(){
        HEALTH-=1;
        HEALTH = Game.clamp(HEALTH,0,100);
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(15,15,200,32);
        g.setColor(Color.green);
        g.fillRect(15,15,HEALTH,32);
    }
}
