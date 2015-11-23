package com.sifno.whmapper.client.Graph;


import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.google.gwt.user.client.ui.*;
import com.sifno.stellarmap.dataobject.StarSystem;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 02.08.2015.
 */
public class StarSystemWidget extends FocusPanel {

    List<PositionListener> positionListenerList;

    private AbsolutePanel parent;

    private HorizontalPanel horizontalPanel;
    private Label systemName;
    private Label systemType;

    private StarSystem system;

    public StarSystemWidget(StarSystem system, AbsolutePanel parent) {
        addStyleName("gwt-SolarSystemWidget");

        this.parent = parent;
        this.system = system;

        horizontalPanel = new HorizontalPanel();

        add(horizontalPanel);

        systemName = new Label(String.valueOf(system.getID()));
        systemType = new Label(system.getSystemClassID());

        systemName.addStyleName("SystemName");

        horizontalPanel.add(systemType);
        horizontalPanel.add(systemName);

    }

    public StarSystem getSystem() {
        return system;
    }

    public int getX() {
        return getAbsoluteLeft() - parent.getAbsoluteLeft() - DOMUtil.getBorderLeft(parent.getElement());
    }
    public int getY() {
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
