package com.sifno.whmapper.client.Graph;

import com.levigo.util.gwtawt.client.WebGraphics;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Алёна on 07.08.2015.
 */
public abstract class GraphComponent {

    private GraphComponent parent;


    public GraphComponent(GraphComponent parent) {
        this.parent = parent;
    }

    public GraphComponent getParent() {
        return parent;
    }

    public void repaint() {
        parent.repaint();
    }

    abstract void paint(WebGraphics g);

    void add(GraphComponent component){
        new Exception("this element can not have children.");
    }


    void remove(GraphComponent component) {
        new Exception("You can not remove the element is not a child");
    }

    List<GraphComponent> getChildren() {
        return null;
    }

//    Boolean add(GraphComponent component);
//    Boolean remove(GraphComponent component);
//    Collection<GraphComponent> getChildren();
}
