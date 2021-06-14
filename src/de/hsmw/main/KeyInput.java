package de.hsmw.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ID.Player) {
                //keyEvent Player1
                if (key == KeyEvent.VK_W)
                    tempObj.setVelY((-5));
                if (key == KeyEvent.VK_A)
                    tempObj.setVelX((-5));
                if (key == KeyEvent.VK_D)
                    tempObj.setVelX((5));
                if (key == KeyEvent.VK_S)
                    tempObj.setVelY((5));
            }

            if (tempObj.getId() == ID.Player2) {
                //keyEvent Player2
                if (key == KeyEvent.VK_UP)
                    tempObj.setVelY((-5));
                if (key == KeyEvent.VK_LEFT)
                    tempObj.setVelX((-5));
                if (key == KeyEvent.VK_RIGHT)
                    tempObj.setVelX((5));
                if (key == KeyEvent.VK_DOWN)
                    tempObj.setVelY((5));
            }

        }
    }

    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            abstractGameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ID.Player) {
                //keyEvent Player1
                if (key == KeyEvent.VK_W)
                    tempObj.setVelY((0));
                if (key == KeyEvent.VK_A)
                    tempObj.setVelX((0));
                if (key == KeyEvent.VK_D)
                    tempObj.setVelX((0));
                if (key == KeyEvent.VK_S)
                    tempObj.setVelY((0));
            }

            if (tempObj.getId() == ID.Player2) {
                //keyEvent Player2
                if (key == KeyEvent.VK_UP)
                    tempObj.setVelY((0));
                if (key == KeyEvent.VK_LEFT)
                    tempObj.setVelX((0));
                if (key == KeyEvent.VK_RIGHT)
                    tempObj.setVelX((0));
                if (key == KeyEvent.VK_DOWN)
                    tempObj.setVelY((0));
            }

        }
    }
}
