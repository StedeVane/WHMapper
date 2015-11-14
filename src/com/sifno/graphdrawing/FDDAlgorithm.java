package com.sifno.graphdrawing;



import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Алёна on 13.11.2015.
 */
public class FDDAlgorithm {

    private PlanarGraph delegate;
    private double resistanceStrength = 15.0;
    private double gravity = 1E3;
    private double tension = 1E-3;
    private double dt = 0.016666667;

    public FDDAlgorithm(PlanarGraph delegate) {
        this.delegate = delegate;
    }

    public PlanarGraph getDelegate() {
        return delegate;
    }

    public void setDelegate(PlanarGraph delegate) {
        this.delegate = delegate;
    }

    private Point2D add(Point2D p1, Point2D p2) {
        return new Point2D.Double(p1.getX()+p2.getX(), p1.getY()+p2.getY());
    }

    private Point2D mul(Point2D p, double k) {
        return new Point2D.Double(p.getX()*k,p.getY()*k);
    }

    private Point2D normalize(Point2D p) {
        double length = p.distance(0, 0);
        return mul(p, 1.0 / length);
    }

    private Point2D sub(Point2D p1, Point2D p2) {
        return new Point2D.Double(p2.getX()-p1.getX(),p2.getY()-p1.getY());
    }

    private Point2D getGravity(Object vertex) {
        Point2D result = new Point2D.Double(0,0);
        Point2D location = delegate.getLocation(vertex);

        for (Object v: delegate.getVertices()) {
            if (!v.equals(vertex)) {
                Point2D r = sub(delegate.getLocation(v), location);
                result = add(result, mul(r, gravity / r.distanceSq(0, 0)));
            }
        }
        return result;
    }

    private Point2D getTension(Object vertex) {
        Point2D result = new Point2D.Double(0,0);
        Point2D location = delegate.getLocation(vertex);

        for (Object v: delegate.getNeighbors(vertex)) {
            if (!v.equals(vertex)) {
                Point2D r = sub(location, delegate.getLocation(v));
                result = add(result,mul(r,tension*r.distance(0,0))) ;
            }
        }
        return result;
    }

    private Point2D resistance(Point2D point) {
        double length = point.distance(0, 0);
        double mul = Math.max(0,(length-resistanceStrength)/length);

        if (mul > 0) return point;
        else return new Point2D.Double(0,0);
    //    else return new Point2D.Double(point.getX()*mul, point.getY()*mul);
    }

    private Point2D getForce(Object vertex) {


        return resistance(add(getGravity(vertex), getTension(vertex)));
    }

    public  void nextFrame() {
        Map<Object, Point2D> result = new HashMap<>();

        for (Object v: delegate.getVertices()) {
    //        System.out.println(v+":" + "gravity=" + getGravity(v) + "  tension="+getTension(v) + "  force=" + getForce(v));


            result.put(v, add(delegate.getLocation(v), mul(getForce(v), dt)));
        }

        for (Object v: delegate.getVertices()) {
            delegate.setLocation(v,result.get(v));
        }
    }

}
