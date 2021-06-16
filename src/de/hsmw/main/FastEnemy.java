package de.hsmw.main;

import java.awt.*;

public class FastEnemy extends abstractGameObject{

        private Handler handler;

        public FastEnemy(float x, float y, ID id, Handler handler) {
            super(x, y, id);
            this.setVelX(7);
            this.setVelY(6);
            this.handler = handler;

        }

        @Override
        public void tick() {
            handler.addObject(new Trail(x,y,ID.Trail, handler, Color.cyan, 16,16,0.083f));
            x += velX;
            y += velY;
            if( y <= 0 || y>= Game.HEIGHT - 48)
                velY *=-1;
            if( x <= 0 || x>= Game.WIDTH - 64)
                velX *=-1;
        }

        @Override
        public void render(Graphics g) {
            g.setColor(Color.cyan);
            g.fillRect((int)x,(int)y,16,16);
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle((int)x,(int)y,16,16);
        }
}
