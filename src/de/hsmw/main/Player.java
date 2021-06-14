package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class Player extends abstractGameObject {
    Random r = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        if (id == ID.Player)
            g.setColor(Color.WHITE);
        if (id == ID.Player2)
            g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);
    }
}