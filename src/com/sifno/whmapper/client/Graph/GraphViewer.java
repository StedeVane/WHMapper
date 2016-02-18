package com.sifno.whmapper.client.Graph;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.graph.PlanarGraph;
import com.sifno.stellarmap.graph.Layout;
import com.sifno.whmapper.client.WHMapper;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphViewer extends AbsolutePanel {

    DropController dropController;
    GraphCanvas graphCanvas;

    PlanarGraph<Integer,Integer> graph;
    Map<Integer,StarSystemWidget> widgetMap = new HashMap<>();



    public GraphViewer() {

        setPixelSize(800, 500);
        graphCanvas = new GraphCanvas(null);

        graphCanvas.getCanvas().setCoordinateSpaceWidth(800);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(500);
        add(graphCanvas.getCanvas());

        dropController = new GraphDropController(this);
    }

    public Layout<Integer, Integer> getGraph() {
        return graph;
    }



//  public void setSize()

    public void setGraph(PlanarGraph<Integer, Integer> graph) {

        this.graph = graph;

        int w = graph.getSize().width;
        int h = graph.getSize().height;

        super.setPixelSize(w, h);
        graphCanvas.getCanvas().setCoordinateSpaceWidth(w);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(h);

        // ???????
        clear();
        add(graphCanvas.getCanvas());

        for (Integer v: graph.getVertices()) {
            StarSystemWidget ssw = new StarSystemWidget(new StarSystem(v),this);
            widgetMap.put(v,ssw);
            add(ssw, (int) graph.getLocation(v).getX(), (int) graph.getLocation(v).getY());
        }

//        WHMapper.label.setText("1");
        for (Integer e: graph.getEdges()) {
            StarSystemWidget ssw1 = widgetMap.get(graph.getEdge(e).getFirst());
            StarSystemWidget ssw2 = widgetMap.get(graph.getEdge(e).getSecond());
            addConnection(e,ssw1,ssw2);
        }

        repaint();
    }

    @Override
    public void clear() {
        super.clear();
        graphCanvas = new GraphCanvas(null);

        graphCanvas.getCanvas().setCoordinateSpaceWidth(800);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(500);
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
