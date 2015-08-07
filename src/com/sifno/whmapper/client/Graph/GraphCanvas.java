package com.sifno.whmapper.client.Graph;

import com.google.gwt.canvas.client.Canvas;
import com.levigo.util.gwtawt.client.WebGraphics;

/**
 * Created by Алёна on 07.08.2015.
 */
public class GraphCanvas extends GraphComposite {

    private final Canvas canvas;
    private final WebGraphics g;

    public GraphCanvas(GraphComponent parent) {
        super(parent);

        canvas = Canvas.createIfSupported();
        g = new WebGraphics(canvas.getContext2d());

    }

    @Override
    public void repaint() {
        g.clearRect(0,0,canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
        paint(g);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
