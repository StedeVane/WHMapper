package com.sifno.whmapper.client.Graph;

import com.google.gwt.event.dom.client.ClickEvent;
import com.levigo.util.gwtawt.client.WebGraphics;
import com.sifno.whmapper.client.WHMapper;

import java.awt.*;
import java.awt.geom.*;

/**
 * Created by Алёна on 07.08.2015.
 */
public class NodeConnection extends GraphComponent {


    private boolean click = false;
    private Shape shape;

    private SolarSystemWidget ssw1, ssw2;

    public NodeConnection(GraphComponent parent, SolarSystemWidget ssw1, SolarSystemWidget ssw2) {
        super(parent);
        this.ssw1 = ssw1;
        this.ssw2 = ssw2;


        updateShape();

        addMouseClickListener(new MouseClickListener() {
            @Override
            public void onMouseClick(ClickEvent event) {
                click = true;
                repaint();
            }
        });


        ssw1.addPositionListener(new PositionHandler(this));
        ssw2.addPositionListener(new PositionHandler(this));


    }


    public void updateShape() {

        int x = Math.min(ssw1.getX(), ssw2.getX());
        int y = Math.min(ssw1.getY(), ssw2.getY());

        int w = Math.abs(ssw1.getX() - ssw2.getX());
        int h = Math.abs(ssw1.getY() - ssw2.getY());

        shape = new Rectangle2D.Double(x,y,w,h);
//        shape = new Line2D.Double(ssw1.getX(),ssw1.getY(),ssw2.getX(),ssw2.getY());

       // shape = new P
    }


    @Override
    public void paint(WebGraphics g) {
        WHMapper.debug2.setText("paint ");
        WHMapper.debug2.setText(g.toString());

        Color defaultColor;
        if (click) g.setColor(Color.GREEN);

        g.draw(shape);


    }

    @Override
    boolean contains(double x, double y) {
        return shape.contains(x,y);
    }

    public String getSomething() {
        return "somethink "+ ssw1 + " " + ssw2;
    }

   /* public Boolean add(GraphComponent component) {
        throw new Exception("This element can not have children");
        return null;
    }

    public Boolean remove(GraphComponent component) {
        return null;
    }

    public Collection<GraphComponent> getChildren() {
        return null;
    }*/

    /*
    public Rectangle getBounds() {
        return shape.getBounds();
    }

    public Rectangle2D getBounds2D() {
        return shape.getBounds2D();
    }

    public boolean contains(double x, double y) {
        return shape.contains(x, y);
    }

    public boolean contains(Point2D p) {
        return shape.contains(p);
    }

    public boolean intersects(double x, double y, double w, double h) {
        return shape.intersects(x, y, w, h);
    }

    public boolean intersects(Rectangle2D r) {
        return shape.intersects(r);
    }

    public boolean contains(double x, double y, double w, double h) {
        return contains(x, y, w, h);
    }

    public boolean contains(Rectangle2D r) {
        return contains(r);
    }

    public PathIterator getPathIterator(AffineTransform at) {
        return getPathIterator(at);
    }

    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return getPathIterator(at,flatness);
    }
*/
}
