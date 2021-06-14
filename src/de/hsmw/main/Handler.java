package de.hsmw.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<abstractGameObject> object = new LinkedList<>();

    public void tick(){
        for(abstractGameObject o : object){
            o.tick();
        }
    }
    public void render(Graphics g){
        for(abstractGameObject o : object){
            o.render(g);
        }
    }
    public void addObject(abstractGameObject ob){
        object.add(ob);
    }
    public void removeObject(abstractGameObject ob){
        object.remove(ob);
    }
}
