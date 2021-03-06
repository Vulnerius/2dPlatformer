package de.hsmw.main;


import java.awt.*;

public class HUD {
    public float HEALTH = 3;
    private float greenValue = 255;
    private int score;
    private int level;
    private int enemies;

    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
        greenValue = Game.clamp(greenValue,0,100);
        greenValue = HEALTH * 2;
        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(15,15,200,32);
        g.setColor(new Color(95,(int)greenValue,0));
        g.fillRect(15,15,(int) HEALTH*2,32);
        g.setColor(Color.white);

        g.drawString("Score: " + score, 10,60);
        g.drawString("Level: " + level, 10,80);
        g.drawString("Enemies: " + enemies, 10,100);
    }

    public void setEnemies(int enemies) {
        this.enemies = enemies;
    }

    public int getEnemies() {
        return enemies;
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

    public float getHEALTH() {
        return HEALTH;
    }

    public void setHEALTH(float HEALTH) {
        this.HEALTH = HEALTH;
    }
}
