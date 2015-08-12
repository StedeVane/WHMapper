package com.sifno.whmapper.client.Graph;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;

import java.awt.*;

/**
 * Created by Pavel on 05.08.2015.
 */
public class VisualizationViewer extends AbsolutePanel {

    DropController dropController;
    GraphCanvas graphCanvas;

    public VisualizationViewer() {


        setPixelSize(800, 500);

        graphCanvas = new GraphCanvas(null);
        graphCanvas.getCanvas().setCoordinateSpaceWidth(800);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(500);
        add(graphCanvas.getCanvas());

        dropController = new GraphDropController(this);


    }

    public void repaint() {
        graphCanvas.repaint();
    }

    public void setBackground(Color color) {
        graphCanvas.setBackground(color);
    }

    public void addConnection(SolarSystemWidget ssw1, SolarSystemWidget ssw2) {
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
/*
    @Override
    protected void setWidgetPositionImpl(Widget w, int left, int top) {
        super.setWidgetPositionImpl(w, left, top);

       // WHMapper.debug.setText("setWidgetPositionImpl");
        if (!(w instanceof SolarSystemWidget))
            return;
        SolarSystemWidget ssw = (SolarSystemWidget) w;
        ssw.firePositionChange();
    }
*/
}
