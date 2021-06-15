package de.hsmw.main;

import java.util.Random;

import static de.hsmw.main.Game.HEIGHT;
import static de.hsmw.main.Game.WIDTH;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 500) {
            hud.setLevel(hud.getLevel() + 1);
            scoreKeep = 0;

            if (hud.getLevel() % 3 == 0) {
                hud.setEnemies(hud.getEnemies() + 1);
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
            }
            if (hud.getLevel() % 5 == 0) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 45), r.nextInt(HEIGHT - 45), ID.FastEnemy, handler));
                hud.setEnemies(hud.getEnemies() + 1);
            }
            if (hud.getLevel() % 7 == 0) {
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 120), r.nextInt(HEIGHT - r.nextInt(60)), ID.SmartEnemy, handler));
                hud.setEnemies(hud.getEnemies() + 1);
            }

            if (hud.getLevel() % 10 == 0) {
                for (int i = 0; i < 3; i++)
                    handler.removeEnemy();
                hud.setEnemies(hud.getEnemies()-3);
            }
            if(HUD.HEALTH < 20){
                handler.addObject(new Heal(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Heal,handler));
            }
        }


    }

}

