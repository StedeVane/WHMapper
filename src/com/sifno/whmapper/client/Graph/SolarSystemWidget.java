package com.sifno.whmapper.client.Graph;


import com.google.gwt.user.client.ui.*;
import com.sifno.whmapper.client.SolarSystemClient;
import com.sifno.whmapper.client.WHMapper;
import com.sifno.whmapper.server.SolarSystem;

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
        this.parent = parent;
        this.system = system;

        horizontalPanel = new HorizontalPanel();

        add(horizontalPanel);

        systemName = new Label(system.getName());
        systemType = new Label(system.getClassType());

        horizontalPanel.add(systemType);
        horizontalPanel.add(systemName);

    }

    int getX() {


        return parent.getWidgetLeft(this);
    }
    int getY() {


        return parent.getWidgetTop(this);
    }

    void addPositionListener(PositionListener positionListener) {
        if (positionListenerList==null)
            positionListenerList = new ArrayList<PositionListener>();

        positionListenerList.add(positionListener);
    }

    void firePositionChange() {
        WHMapper.debug.setText("fire");
        if (positionListenerList==null) return;
        WHMapper.debug.setText(String.valueOf(positionListenerList.size()));
        for (PositionListener listener : positionListenerList) {
            listener.positionChange();
        }
    }

}
