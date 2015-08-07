package com.sifno.whmapper.client.Graph;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sifno.whmapper.client.Graph.SolarSystemWidget;
import com.sifno.whmapper.client.SolarSystemClient;
import com.sifno.whmapper.client.WHMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel on 05.08.2015.
 */
public class GraphPanel extends AbsolutePanel {


    GraphCanvas graphCanvas;


    //Canvas canvas;

    public GraphPanel() {

        setPixelSize(800, 500);

        graphCanvas = new GraphCanvas(null);
        graphCanvas.getCanvas().setCoordinateSpaceWidth(800);
        graphCanvas.getCanvas().setCoordinateSpaceHeight(500);
        add(graphCanvas.getCanvas());

    }

    public void addConnection(SolarSystemWidget ssw1, SolarSystemWidget ssw2) {
        graphCanvas.add(new NodeConnection(graphCanvas, ssw1, ssw2));
    }


    public Point getWidgetPosition(int index) {

        Widget widget = list.get(index);

        return new Point(widget.getAbsoluteLeft()-getAbsoluteLeft(),widget.getAbsoluteTop()-getAbsoluteTop());
    }

    public void drawConnections() {

    }

    List<SolarSystemWidget> list;


    public void add(SolarSystemWidget w, int left, int top) {
        super.add(w, left, top);

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(w);

    }

    @Override
    protected void setWidgetPositionImpl(Widget w, int left, int top) {
        super.setWidgetPositionImpl(w, left, top);

       // WHMapper.debug.setText("setWidgetPositionImpl");
        if (!(w instanceof SolarSystemWidget))
            return;
        SolarSystemWidget ssw = (SolarSystemWidget) w;
        ssw.firePositionChange();
    }

    /*
    @Override
    public void add(Widget w) {

        //super.add(w);
    }*/
}
