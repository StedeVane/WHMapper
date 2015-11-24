package com.sifno.whmapper.client.Graph;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.graphdrawing.*;
import com.google.gwt.user.client.Timer;
import com.sifno.stellarmap.graphdrawing.layout.ForceDirectedAlgorithm;
import com.sifno.stellarmap.graphdrawing.layout.ForceDirectedLayout;
import com.sifno.stellarmap.graphdrawing.layout.Layout;
import com.sifno.stellarmap.graphdrawing.layout.TransferableLayout;
import com.sifno.whmapper.client.WHMapper;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavel on 05.08.2015.
 */
public class VisualizationViewer extends AbsolutePanel {

    DropController dropController;
    GraphCanvas graphCanvas;

    Layout<Integer,Integer> layout;
    Map<Integer,StarSystemWidget> widgetMap = new HashMap<>();



    public VisualizationViewer() {

  //      setPixelSize(800,500);
        setPixelSize(800, 500);
        graphCanvas = new GraphCanvas(null);

        graphCanvas.getCanvas().setCoordinateSpaceWidth(800);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(500);
        add(graphCanvas.getCanvas());

        dropController = new GraphDropController(this);
    }

    public Layout<Integer, Integer> getLayout() {
        return layout;
    }



//  public void setSize()

    public void setLayout(Layout<Integer, Integer> layout) {
        this.layout = layout;

        int w = layout.getSize().width;
        int h = layout.getSize().height;

        super.setPixelSize(w, h);
        graphCanvas.getCanvas().setCoordinateSpaceWidth(w);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(h);

        // ???????
        clear();
        add(graphCanvas.getCanvas());

        for (Integer v: layout.getGraph().getVertices()) {
            StarSystemWidget ssw = new StarSystemWidget(new StarSystem(v),this);
            widgetMap.put(v,ssw);
            add(ssw, (int) layout.getLocation(v).getX(), (int) layout.getLocation(v).getY());
        }

        WHMapper.label.setText("1");
        for (Integer e: layout.getGraph().getEdges()) {
            StarSystemWidget ssw1 = widgetMap.get(layout.getGraph().getEdge(e).getFirst());
            StarSystemWidget ssw2 = widgetMap.get(layout.getGraph().getEdge(e).getSecond());
            addConnection(e,ssw1,ssw2);
        }

        repaint();
    }

    public void add(StarSystemWidget w, int left, int top) {
        super.add(w, left, top);
        WHMapper.dragController.makeDraggable(w);
    }

    public void repaint() {
        graphCanvas.repaint();
    }

    public void setBackground(Color color) {
        graphCanvas.setBackground(color);
    }

    public void addConnection(int edge, StarSystemWidget ssw1, StarSystemWidget ssw2) {
        graphCanvas.add(new NodeConnection(graphCanvas, ssw1, ssw2));
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

}
