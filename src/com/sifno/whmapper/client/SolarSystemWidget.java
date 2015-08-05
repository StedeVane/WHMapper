package com.sifno.whmapper.client;


import com.google.gwt.user.client.ui.*;
import com.sifno.whmapper.server.SolarSystem;

/**
 * Created by Pavel on 02.08.2015.
 */
public class SolarSystemWidget extends FocusPanel {

    HorizontalPanel horizontalPanel;
    Label systemName;
    Label systemType;

    SolarSystemClient system;

    SolarSystemWidget(SolarSystemClient system) {
        this.system = system;

        horizontalPanel = new HorizontalPanel();

        add(horizontalPanel);

        systemName = new Label(system.getName());
        systemType = new Label(system.getClassType());

        horizontalPanel.add(systemType);
        horizontalPanel.add(systemName);
    }
}
