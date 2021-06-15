package de.hsmw.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private final Handler handler;
    private final Random r = new Random();
    private final HUD hud;
    private final Spawn spawner;
    private Menu menu;

    public enum State {
        Menu,
        Game,
        End,
    }
    public State gameState = State.Menu;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this,handler,hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "newGame", this);
        spawner = new Spawn(handler, hud);

    }


    public synchronized void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == State.Game) {
            hud.render(g);
        }  else if( gameState == State.Menu || gameState == State.End){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
        if (gameState == State.Game) {
            hud.tick();
            spawner.tick();
            if(hud.getHEALTH() <= 0){
                handler.removeAll();
                gameState = State.End;
            }
        } else if( gameState == State.Menu || gameState == State.End){
            menu.tick();
        }
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max)
            var = max;
        else if (var <= min)
            var = min;
        return var;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public static void main(String[] args) {
        new Game();
    }

}
