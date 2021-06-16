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
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 200)+1, r.nextInt(HEIGHT - 100)+1, ID.BasicEnemy, handler));
            }
            if (hud.getLevel() % 5 == 0) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 200)+1, r.nextInt(HEIGHT - 100)+1, ID.FastEnemy, handler));
                hud.setEnemies(hud.getEnemies() + 1);
            }
            if (hud.getLevel() % 7 == 0) {
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 200)+1, r.nextInt(HEIGHT - 100)+1, ID.SmartEnemy, handler, hud));
                hud.setEnemies(hud.getEnemies() + 1);
            }

            if (hud.getLevel() % 28 == 0) {
                for (int i = 0; i < 3; i++)
                    handler.removeEnemy();
                hud.setEnemies(hud.getEnemies() - 3);
            }
            if (hud.getLevel() % 15 == 0) {
                for (int i = 0; i < handler.object.size(); i++) {
                    abstractGameObject temp = handler.object.get(i);
                    if (temp.getId().equals(ID.Player)) {
                        handler.removeAll();
                        handler.addObject(temp);
                        handler.addObject(new Boss(320 - 48, 0, ID.Boss, handler));
                    }
                }
                hud.setEnemies(9999);
            }
            if (hud.getLevel() % 18 == 0) {
                for (int i = 0; i < handler.object.size(); i++) {
                    abstractGameObject temp = handler.object.get(i);
                    if (temp.getId().equals(ID.Boss)) {
                        handler.removeObject(temp);
                    }
                }
                hud.setEnemies(hud.getEnemies()-9999);
            }

            if (hud.getHEALTH() < 33) {
                handler.addObject(new Heal(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Heal, handler, hud));
            }
        }

    }

}

