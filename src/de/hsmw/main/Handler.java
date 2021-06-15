package de.hsmw.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<abstractGameObject> object = new LinkedList<>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            abstractGameObject tempObj = object.get(i);
            tempObj.tick();
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            abstractGameObject tempObj = object.get(i);
            tempObj.render(g);
        }
    }
    public void addObject(abstractGameObject ob){
        object.add(ob);
    }
    public void removeObject(abstractGameObject ob){
        object.remove(ob);
    }

    public void removeEnemy() {
        for(abstractGameObject abO : object){
            if(abO.getId() == ID.BasicEnemy || abO.getId() == ID.FastEnemy){
                removeObject(abO);
                break;
            }
        }
    }
}
