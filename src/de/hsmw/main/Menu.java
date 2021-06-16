package de.hsmw.main;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

        if (game.gameState.equals(Game.State.Menu)) {
            //play Button
            if (hovering(mx, my, 250, 120, 150, 64)) {
                game.setGameState(Game.State.Select);
                //AudioPlayer.getSound("menu_sound").play();
            }
        }

        if (game.gameState.equals(Game.State.Select)) {
            //normal difficulty
            if (hovering(mx, my, 220, 140, 100, 50)) {
                game.difficulty = 0;
                hud.setHEALTH(100);
                hud.setScore(0);
                hud.setLevel(1);
                hud.setEnemies(1);
                handler.removeAll();
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 16, ID.Player, handler, hud));
                handler.addObject(new SmartEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.SmartEnemy, handler, hud));
                game.setGameState(Game.State.Game);
            }
            //hard difficulty
            else if (hovering(mx, my, 220, 240, 100, 50)) {
                game.difficulty = 1;
                hud.setHEALTH(100);
                hud.setScore(0);
                hud.setLevel(1);
                hud.setEnemies(1);
                handler.removeAll();
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 16, ID.Player, handler, hud));
                handler.addObject(new FastEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.FastEnemy, handler));
                game.setGameState(Game.State.Game);
            }
            //saveState
            else if (hovering(mx, my, 220, 340, 100, 50)) {
                handler.removeAll();
                NodeList list = game.save.readFromSaveState("./saveFiles/saveFile.xml");
                for (int i = 0; i < list.getLength(); i++) {
                    Node ele = list.item(i);
                    Element element = (Element) ele;


                    if (element.getTagName().equals("GameObject")) {
                        String id = element.getAttribute("id");
                        switch (id) {
                            case "HUD" -> {
                                hud.setEnemies(Integer.parseInt(element.getAttribute("Enemies")));
                                hud.setHEALTH(Float.parseFloat(element.getAttribute("Health")));
                                hud.setLevel(Integer.parseInt(element.getAttribute("Level")));
                                hud.setScore(Integer.parseInt(element.getAttribute("Score")));
                            }
                            case "FastEnemy" -> handler.addObject(new FastEnemy(Float.parseFloat(String.valueOf(element.getAttribute("x"))), Float.parseFloat(String.valueOf(element.getAttribute("y"))), ID.FastEnemy, handler));
                            case "BasicEnemy" -> handler.addObject(new BasicEnemy(Float.parseFloat(String.valueOf(element.getAttribute("x"))), Float.parseFloat(String.valueOf(element.getAttribute("y"))), ID.BasicEnemy, handler));
                            case "SmartEnemy" -> handler.addObject(new SmartEnemy(Float.parseFloat(String.valueOf(element.getAttribute("x"))), Float.parseFloat(String.valueOf(element.getAttribute("y"))), ID.SmartEnemy, handler, hud));
                            case "Player" -> handler.addObject(new Player(Float.parseFloat(String.valueOf(element.getAttribute("x"))), Float.parseFloat(String.valueOf(element.getAttribute("y"))), ID.Player, handler, hud));
                            case "Heal" -> handler.addObject(new Heal(Float.parseFloat(String.valueOf(element.getAttribute("x"))), Float.parseFloat(String.valueOf(element.getAttribute("y"))), ID.Heal, handler, hud));
                            case "Boss" -> handler.addObject(new Boss(Float.parseFloat(String.valueOf(element.getAttribute("x"))), Float.parseFloat(String.valueOf(element.getAttribute("y"))), ID.Boss, handler));
                        }
                    }
                }
                game.setGameState(Game.State.Game);
            }
        }

        if (game.gameState.equals(Game.State.Save)) {
            //saving
            if (hovering(mx, my, 330, 120, 100, 50)) {
                game.save.writeToFile();
            }
            //back
            else if (hovering(mx, my, 330, 300, 100, 50)) {
                game.setGameState(Game.State.Game);
                Game.paused = false;
            }
        }

        //AudioPlayer.getSound("menu_sound").play();

        //try-again Button
        if (game.gameState.equals(Game.State.End))
            if (hovering(mx, my, 320, 200, 100, 50)) {
                //AudioPlayer.getSound("menu_sound").play();
                game.setGameState(Game.State.Select);
                //hud.setHEALTH(100);
                handler.removeAll();
                hud.setEnemies(1);
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
        if (game.gameState.equals(Game.State.Menu)) {
            g.drawString("Menu", 100, 100);
            g.drawRect(250, 120, 150, 64);
            g.setFont(heading2);
            g.drawString("Play", 270, 160);
        } else if (game.gameState.equals(Game.State.End)) {
            g.setFont(heading);
            g.drawString("Game Over", 50, 50);
            g.setFont(heading3);
            g.drawString("You lost with a score of: " + hud.getScore(), 240, 160);
            g.drawRect(320, 200, 100, 50);
            g.drawString("Try again", 320, 230);
        } else if (game.gameState.equals(Game.State.Select)) {
            g.setFont(heading2);
            g.drawString("Select difficulty", 50, 50);
            g.setFont(heading3);
            g.drawRect(220, 140, 100, 50);
            g.drawString("normal", 240, 160);
            g.drawRect(220, 240, 100, 50);
            g.drawString("hard", 240, 260);
            g.drawRect(220, 340, 100, 50);
            g.setFont(new Font("verdana", Font.BOLD, 15));
            g.drawString("saveState", 230, 380);
        } else if (game.gameState.equals(Game.State.Save)) {
            g.setColor(Color.white);
            g.setFont(heading3);
            g.drawRect(330, 120, 100, 50);
            g.drawString("Save", 340, 140);
            g.drawRect(330, 300, 100, 50);
            g.drawString("Back", 340, 340);
        }
    }
}
