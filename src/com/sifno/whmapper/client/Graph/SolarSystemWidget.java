package com.sifno.whmapper.client.Graph;


import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.google.gwt.user.client.ui.*;
import com.sifno.stellarmap.SolarSystemClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 02.08.2015.
 */
public class SolarSystemWidget extends FocusPanel {

    List<PositionListener> positionListenerList;

    AbsolutePanel parent;

    HorizontalPanel horizontalPanel;
    Label systemName;
    Label systemType;

    SolarSystemClient system;

    public SolarSystemWidget(SolarSystemClient system, AbsolutePanel parent) {
        addStyleName("gwt-SolarSystemWidget");

        this.parent = parent;
        this.system = system;

        horizontalPanel = new HorizontalPanel();

        add(horizontalPanel);

        systemName = new Label(system.getName());
        systemType = new Label(system.getClassType());

        systemName.addStyleName("SystemName");

        horizontalPanel.add(systemType);
        horizontalPanel.add(systemName);

    }

    int getX() {
        return getAbsoluteLeft() - parent.getAbsoluteLeft() - DOMUtil.getBorderLeft(parent.getElement());
    }
    int getY() {
        return getAbsoluteTop() - parent.getAbsoluteTop() - DOMUtil.getBorderTop(parent.getElement());
    }

    void addPositionListener(PositionListener positionListener) {
        if (positionListenerList==null)
            positionListenerList = new ArrayList<PositionListener>();

        positionListenerList.add(positionListener);
    }

    public void firePositionChange() {

        if (positionListenerList==null) return;

        for (PositionListener listener : positionListenerList) {
            listener.positionChange();
        }
    }

}
