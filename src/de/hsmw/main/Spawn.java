package de.hsmw.main;

import java.awt.*;
import java.util.Random;

public class Spawn{
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }
}
