package com.sifno.whmapper.client.Graph;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.sifno.whmapper.client.WHMapper;


/**
 * Created by Pavel on 08.08.2015.
 */
public class GraphDropController extends AbsolutePositionDropController {

    VisualizationViewer vv;

    public GraphDropController(VisualizationViewer dropTarget) {
        super(dropTarget);
        this.vv = dropTarget;
    }

    @Override
    public void onEnter(DragContext context) {
        super.onEnter(context);

     //   vv.setBackground(Color.CYAN);
        vv.repaint();
    }

    @Override
    public void onLeave(DragContext context) {
        super.onLeave(context);
     //   vv.setBackground(Color.WHITE);
        vv.repaint();
    }

    @Override
    public void onMove(DragContext context) {
        super.onMove(context);

        WHMapper.x.setText("x: " + (context.desiredDraggableX - vv.getAbsoluteLeft() - DOMUtil.getBorderLeft(vv.getElement()) ));
        WHMapper.y.setText("y: " + (context.desiredDraggableY - vv.getAbsoluteTop() - DOMUtil.getBorderTop(vv.getElement()) ));

        if (!(context.draggable instanceof SolarSystemWidget)) return;

        SolarSystemWidget ssw = (SolarSystemWidget) context.draggable;

        ssw.firePositionChange();

//        WHMapper.x.setText("x: " + context.draggable.getAbsoluteLeft());
//        WHMapper.y.setText("y: " + context.draggable.getAbsoluteTop());
    }


}
