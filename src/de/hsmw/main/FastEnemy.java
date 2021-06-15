package de.hsmw.main;

import java.awt.*;

public class FastEnemy extends abstractGameObject{

        private Handler handler;
        private ID id;

        public FastEnemy(int x, int y, ID id, Handler handler) {
            super(x, y, id);
            this.handler = handler;
            this.id = id;
            velX = 7;
            velY = 7;
        }

        @Override
        public void tick() {
            handler.addObject(new Trail(x,y,ID.Trail, handler, Color.cyan, 16,16,0.083f));
            x += velX;
            y += velY;
            if( y <= 0 || y>= Game.HEIGHT - 32)
                velY *=-1;
            if( x <= 0 || x>= Game.WIDTH - 32)
                velX *=-1;
        }

        @Override
        public void render(Graphics g) {
            g.setColor(Color.cyan);
            g.fillRect(x,y,16,16);
        }

        @Override
        public Rectangle getBounds() {
            return new Rectangle(x,y,16,16);
        }

}
