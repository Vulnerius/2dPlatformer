package de.hsmw.main;


import java.awt.*;

public class HUD {
    public static int HEALTH = 100;
    private int score = 0;
    private int level = 1;
    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(15,15,200,32);
        g.setColor(Color.green);
        g.fillRect(15,15,HEALTH*2,32);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
