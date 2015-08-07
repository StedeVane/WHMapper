package com.sifno.whmapper.client.Graph;

import com.levigo.util.gwtawt.client.WebGraphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Алёна on 07.08.2015.
 */
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
    void paint(WebGraphics g) {
        if (children != null)
            for (GraphComponent component: children)
                component.paint(g);

    }
}
