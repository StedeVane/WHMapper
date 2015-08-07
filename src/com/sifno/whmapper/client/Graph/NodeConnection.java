package com.sifno.whmapper.client.Graph;

import com.levigo.util.gwtawt.client.WebGraphics;
import com.sifno.whmapper.client.WHMapper;

import java.awt.*;
import java.awt.geom.*;

/**
 * Created by Алёна on 07.08.2015.
 */
public class NodeConnection extends GraphComponent {



    private Shape shape;

    private SolarSystemWidget ssw1, ssw2;

    public NodeConnection(GraphComponent parent, SolarSystemWidget ssw1, SolarSystemWidget ssw2) {
        super(parent);
        this.ssw1 = ssw1;
        this.ssw2 = ssw2;


        updateShape();



        ssw1.addPositionListener(new PositionHandler(this));
        ssw2.addPositionListener(new PositionHandler(this));


    }

    public void updateShape() {
        WHMapper.debug.setText("I'm here");

        WHMapper.debug.setText("ssw1 "+ ssw1);
        WHMapper.debug.setText("sww1 xy: " + ssw1.getX()+ " " + ssw1.getY());

        WHMapper.debug.setText("ssw2 "+ ssw2);
        WHMapper.debug.setText("sww2 xy: " + ssw2.getX()+ " " + ssw2.getY());

        WHMapper.debug.setText(ssw1.getX() + " " + ssw1.getY() + " " + ssw2.getX() + " " + ssw2.getY());
        shape = new Line2D.Double(ssw1.getX(),ssw1.getY(),ssw2.getX(),ssw2.getY());
        WHMapper.debug.setText(ssw1.getX() + " " + ssw1.getY() + " " + ssw2.getX() + " " + ssw2.getY());

     //   repaint();
    }



    public void paint(WebGraphics g) {
        g.draw(shape);
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
