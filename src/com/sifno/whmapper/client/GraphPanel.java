package com.sifno.whmapper.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 05.08.2015.
 */
public class GraphPanel extends AbsolutePanel {

    Canvas canvas;

    public GraphPanel() {

        setPixelSize(800,500);

        canvas = Canvas.createIfSupported();
        canvas.setCoordinateSpaceWidth(800);
        canvas.setCoordinateSpaceHeight(500);
        //canvas.setPixelSize(100,400);
        add(canvas);

    }

    public Canvas getCanvas() {
        return canvas;
    }



    public Point getWidgetPosition(int index) {

        Widget widget = list.get(index);

        return new Point(widget.getAbsoluteLeft()-getAbsoluteLeft(),widget.getAbsoluteTop()-getAbsoluteTop());
    }

    public void drawConnections() {



        Context2d context = canvas.getContext2d();
        context.clearRect(0,0,canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
        context.beginPath();

        for (int i=0;i<list.size()-1;i++)
            for (int j=i+1;j<list.size();j++) {
                Point pi =getWidgetPosition(i);
                Point pj =getWidgetPosition(j);


                context.moveTo(pi.getX(), pi.getY());
                context.lineTo(pj.getX(), pj.getY());
                context.stroke();

         
            }

    }

    List<SolarSystemWidget> list;


    public void add(SolarSystemWidget w, int left, int top) {
        super.add(w,left,top);

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(w);

    }

    /*
    @Override
    public void add(Widget w) {

        //super.add(w);
    }*/
}
