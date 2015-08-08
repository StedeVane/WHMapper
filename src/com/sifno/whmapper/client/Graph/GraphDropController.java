package com.sifno.whmapper.client.Graph;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.allen_sauer.gwt.dnd.client.drop.AbstractDropController;
import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.sifno.whmapper.client.WHMapper;

import java.awt.*;


/**
 * Created by Pavel on 08.08.2015.
 */
public class GraphDropController extends AbsolutePositionDropController {

    GraphPanel graphPanel;

    public GraphDropController(GraphPanel dropTarget) {
        super(dropTarget);
        this.graphPanel = dropTarget;
    }

    @Override
    public void onEnter(DragContext context) {
        super.onEnter(context);

     //   graphPanel.setBackground(Color.CYAN);
        graphPanel.repaint();
    }

    @Override
    public void onLeave(DragContext context) {
        super.onLeave(context);
     //   graphPanel.setBackground(Color.WHITE);
        graphPanel.repaint();
    }

    @Override
    public void onMove(DragContext context) {
        super.onMove(context);

        WHMapper.x.setText("x: " + (context.desiredDraggableX - graphPanel.getAbsoluteLeft() - DOMUtil.getBorderLeft(graphPanel.getElement()) ));
        WHMapper.y.setText("y: " + (context.desiredDraggableY - graphPanel.getAbsoluteTop() - DOMUtil.getBorderTop(graphPanel.getElement()) ));

        if (!(context.draggable instanceof SolarSystemWidget)) return;

        SolarSystemWidget ssw = (SolarSystemWidget) context.draggable;

        ssw.firePositionChange();

//        WHMapper.x.setText("x: " + context.draggable.getAbsoluteLeft());
//        WHMapper.y.setText("y: " + context.draggable.getAbsoluteTop());
    }


}
