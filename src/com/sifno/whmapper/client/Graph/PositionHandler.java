package com.sifno.whmapper.client.Graph;

import com.sifno.whmapper.client.WHMapper;

/**
 * Created by Алёна on 07.08.2015.
 */
public class PositionHandler implements PositionListener{

    NodeConnection connection;

    public PositionHandler(NodeConnection connection) {
        this.connection = connection;
    }

    @Override
    public void positionChange() {
        WHMapper.label.setText("5");
        connection.updateShape();
        connection.repaint();
    }
}
