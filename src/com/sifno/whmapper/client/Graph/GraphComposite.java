package com.sifno.whmapper.client.Graph;

import com.google.gwt.event.dom.client.ClickEvent;
import com.levigo.util.gwtawt.client.WebGraphics;
import com.sifno.whmapper.client.WHMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GraphComposite extends GraphComponent {

    List<GraphComponent> children;

    public GraphComposite(GraphComponent parent) {
        super(parent);
    }

    @Override
    void add(GraphComponent component) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(component);
    }

    @Override
    void remove(GraphComponent component) {
        if (children != null)
            children.remove(component);
    }

    @Override
    List<GraphComponent> getChildren() {
        return children;
    }

    @Override
    void fireMouseClick(ClickEvent event) {
        super.fireMouseClick(event);

        if (children != null)
            for (GraphComponent component: children) {
                if (component.contains(event.getX(), event.getY())) {
                    component.fireMouseClick(event);
                }
            }
    }

    @Override
    boolean contains(double x, double y) {
        boolean result = false;
        if (children != null)
            for (int i=0; i<children.size()&&!result;i++) {
                result|= children.get(i).contains(x, y);
            }
        return result;
    }

    @Override
    void paint(WebGraphics g) {
        if (children != null)
            for (GraphComponent component: children) {
                component.paint(g);
            }
    }
}
