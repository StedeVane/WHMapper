package com.sifno.whmapper.client.Graph;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.levigo.util.gwtawt.client.WebGraphics;
import com.sifno.whmapper.client.WHMapper;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Алёна on 07.08.2015.
 */
public class GraphCanvas extends GraphComposite {

    private final Canvas canvas;
    private final WebGraphics g;

    private Color background;

    public GraphCanvas(GraphComponent parent) {
        super(parent);

        background = Color.WHITE;
        canvas = Canvas.createIfSupported();

        g = new WebGraphics(canvas.getContext2d());

        canvas.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireMouseClick(event);
            }
        });

      //  repaint();
    }


    public void setBackground(Color color) {
        background = color;
    }

    @Override
    public void repaint() {


        g.setFillColor(background);
        g.fill(new Rectangle2D.Double(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight()));
   //     g.clearRect(0,0,canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
        WHMapper.debug2.setText(g.toString());
        paint(g);
    }

    public Canvas getCanvas() {
        return canvas;
    }


}
