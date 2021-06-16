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
    private final Menu menu;
    public int difficulty;     //0 = normal 1 = hard
    public static boolean paused = false;
    public final SaveState save;

    public enum State {
        Menu,
        Select,
        Game,
        Save,
        End,
    }

    public State gameState;

    public Game() {
        gameState = State.Menu;
        handler = new Handler();
        hud = new HUD();
        save = new SaveState(this,"./saveFiles/saveFile.xml",handler,hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
//        AudioPlayer.load();
        //Audioplayer.get("music").loop();

        new Window(WIDTH, HEIGHT, "newGame", this);
        spawner = new Spawn(handler, hud);

        for (int i = 0; i < 7; i++)
            handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Particle, handler));

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
                //        System.out.println("FPS: " + frames);
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

        if (paused) {
            g.setColor(Color.red);
            g.drawString("Paused", 100, 100);
            setGameState(State.Save);
        }

        if (gameState == State.Game) {
            hud.render(g);
        } else if (gameState.equals(State.Menu) || gameState.equals(State.End) || gameState.equals(State.Select) || gameState.equals(State.Save)) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void tick() {

        if (gameState == State.Game) {
            if (!paused) {
                handler.tick();
                hud.tick();
                spawner.tick();
                if (hud.getHEALTH() <= 0) {
                    handler.removeAll();
                    gameState = State.End;
                    for (int i = 0; i < 7; i++)
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Particle, handler));
                }
            }
        } else if (gameState.equals(State.Menu) || gameState.equals(State.End) || gameState.equals(State.Select)) {
            handler.tick();
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
