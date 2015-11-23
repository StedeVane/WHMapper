package com.sifno.whmapper.client.Graph;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.sifno.stellarmap.graphdrawing.*;
import com.google.gwt.user.client.Timer;


import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Pavel on 05.08.2015.
 */
public class VisualizationViewer extends AbsolutePanel {

    DropController dropController;
    GraphCanvas graphCanvas;

    PlanarGraphO<Integer,Integer> planarGraphO = new PlanarGraphO<>(new UndirectedSpareGraph<Integer,Integer>());


    public VisualizationViewer() {


        setPixelSize(800, 500);

        graphCanvas = new GraphCanvas(null);
        graphCanvas.getCanvas().setCoordinateSpaceWidth(800);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(500);
        add(graphCanvas.getCanvas());

        dropController = new GraphDropController(this);

        planarGraphO.setHeight(500);
        planarGraphO.setWidth(800);

        final FDDAlgorithm<Integer,Integer> algorithm = new FDDAlgorithm<>(planarGraphO);

        Timer timer = new Timer() {
            @Override
            public void run() {
                algorithm.nextFrame();
            }
        };
        timer.scheduleRepeating(16);

    }


    public void add(StarSystemWidget w, int left, int top) {
        super.add(w, left, top);
        final StarSystemWidget ww = w;

        planarGraphO.addGraphEventListener(new GraphEventListener<Integer, Integer>() {
            @Override
            public void handleGraphEvent(GraphEvent<Integer, Integer> event) {
                if (event.getType() == GraphEvent.Type.CHANGE_VERTICES_LOCATION) {
                    GraphEvent.VerticesLocation<Integer,Integer> eventLocation = (GraphEvent.VerticesLocation) event;
                    Point2D location = eventLocation.getLocations().get(ww.getSystem().getID());
                    setWidgetPosition(ww, (int) location.getX(), (int) location.getY());
                }
            }
        });

        ww.addPositionListener(new PositionListener() {
            @Override
            public void positionChange() {

                planarGraphO.setLocation(ww.getSystem().getID(),new Point2D.Double(ww.getX(),ww.getY()));
            }
        });

        planarGraphO.addVertex(w.getSystem().getID());
        planarGraphO.setLocation(w.getSystem().getID(),new Point2D.Double(left,top));
    }

    public void repaint() {
        graphCanvas.repaint();
    }

    public void setBackground(Color color) {
        graphCanvas.setBackground(color);
    }

    public void addConnection(int edge, StarSystemWidget ssw1, StarSystemWidget ssw2) {
        graphCanvas.add(new NodeConnection(graphCanvas, ssw1, ssw2));

        planarGraphO.addEdge(edge,ssw1.getSystem().getID(),ssw2.getSystem().getID());
    }

    public DropController getDropController() {
        return dropController;
    }

    public void setDropController(DropController dropController) {
        this.dropController = dropController;
    }

    public void drawConnections() {
        graphCanvas.repaint();
    }
/*
    @Override
    protected void setWidgetPositionImpl(Widget w, int left, int top) {
        super.setWidgetPositionImpl(w, left, top);

       // WHMapper.debug.setText("setWidgetPositionImpl");
        if (!(w instanceof StarSystemWidget))
            return;
        StarSystemWidget ssw = (StarSystemWidget) w;
        ssw.firePositionChange();
    }
*/
}
