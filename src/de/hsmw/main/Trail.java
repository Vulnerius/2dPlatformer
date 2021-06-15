package de.hsmw.main;

import java.awt.*;

public class Trail extends abstractGameObject{

    private float alpha = 0.9f;
    private int width, height;
    private float life;
    private Color color;
    private Handler handler;

//life = 0.01 - 0.1

    public Trail(int x, int y, ID id, Handler handler, Color color, int width, int height, float life) {
        super(x, y, id);
        this.handler = handler;
        this.id = id;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type,alpha);
    }

    @Override
    public void tick() {
        if(alpha>life){
            alpha-=(life - 0.0001f);
        }else
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect(x,y,width,height);

        g2d.setComposite(makeTransparent(1));

        g.setColor(Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,14,14);
    }
}
