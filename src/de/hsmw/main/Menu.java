package de.hsmw.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static de.hsmw.main.Game.HEIGHT;
import static de.hsmw.main.Game.WIDTH;

public class Menu extends MouseAdapter {
    Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent me) {
        int mx = me.getX();
        int my = me.getY();

        //play-Button
        if (game.gameState == Game.State.Menu)
            if (hovering(mx, my, 250, 120, 150, 64)) {
                game.gameState = Game.State.Game;
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 16, ID.Player, handler,hud));
                handler.addObject(new SmartEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
            }
        //try-again Button
        if (game.gameState == Game.State.End)
            if (hovering(mx, my, 320, 200, 100, 50)) {
                game.setGameState(Game.State.Game);
                hud.setHEALTH(100);
                hud.setScore(0);
                hud.setLevel(1);
                handler.removeAll();
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 16, ID.Player, handler,hud));
                handler.addObject(new SmartEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
            }

    }

    public void mouseReleased(MouseEvent me) {

    }

    private boolean hovering(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width)
            return my > y && my < y + height;
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font heading = new Font("verdana", Font.BOLD, 50);
        Font heading2 = new Font("verdana", Font.BOLD, 30);
        Font heading3 = new Font("verdana", Font.BOLD, 18);
        g.setColor(Color.white);
        g.setFont(heading);
        if (game.gameState == Game.State.Menu) {
            g.drawString("Menu", 100, 100);
            g.drawRect(250, 120, 150, 64);
            g.setFont(heading2);
            g.drawString("Play", 270, 160);
        } else if (game.gameState == Game.State.End) {
            g.setFont(heading);
            g.drawString("Game Over", 50, 50);
            g.setFont(heading3);
            g.drawString("You lost with a score of: " + hud.getScore(), 240, 160);
            g.drawRect(320, 200, 100, 50);
            g.drawString("Try again", 320, 230);
        }

    }

}
