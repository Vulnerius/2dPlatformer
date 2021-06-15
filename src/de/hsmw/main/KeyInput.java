package de.hsmw.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        for (boolean b : keyDown)
            b = false;
        this.game = game;
    }

    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject tempObj = handler.object.get(i);
            if (tempObj.getId() == ID.Player) {
                //keyEvent Player1
                if (key == KeyEvent.VK_W) {
                    tempObj.setVelY((-5));
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObj.setVelX((-5));
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObj.setVelX((5));
                    keyDown[3] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObj.setVelY((5));
                    keyDown[1] = true;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(key == KeyEvent.VK_P) {
            if(game.gameState == Game.State.Game)
            Game.paused = !Game.paused;
        }
    }

    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ID.Player) {
                //keyEvent Player1
                if (key == KeyEvent.VK_W)
                    keyDown[0] = false;
                if (key == KeyEvent.VK_A)
                    keyDown[2] = false;
                if (key == KeyEvent.VK_D)
                    keyDown[3] = false;
                if (key == KeyEvent.VK_S)
                    keyDown[1] = false;

                if(!keyDown[0] && !keyDown[1])
                    tempObj.setVelY(0);
                if(!keyDown[2] && !keyDown[3])
                    tempObj.setVelX(0);
            }
        }
    }
}
