package com.sifno.stellarmap.graphdrawing.layout;



import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Алёна on 13.11.2015.
 */
public class ForceDirectedAlgorithm<V,E> {

    private Map<V,Point2D> locations;
    private ForceDirectedLayout<V,E> layout;

    private double resistanceStrength = 1.0;
    private double gravity = 1E4;
    private double tension = 5E-3;
    private double dt = 0.016666667;

    public void leadToEquilibrium(ForceDirectedLayout<V, E> layout) {
       // this.layout = layout;
        this.locations = layout.getLocations();


        while (!balance()) { }

        layout.getLocations().putAll(locations);
    }

    private Point2D getGravity(V vertex) {
        Point2D result = new Point2D.Double(0,0);
        Point2D location = locations.get(vertex);

        for (V v: layout.getGraph().getVertices()) {
            if (!v.equals(vertex)) {
                Point2D r = sub(locations.get(v), location);
                result = add(result, mul(r, gravity / r.distanceSq(0, 0)));
            }
        }
        return result;
    }

    private Point2D getBorderForce(V vertex) {
        Point2D location = locations.get(vertex);

        double fx = 0,fy = 0;
        double x = location.getX();
        double y = location.getY();

        if (layout.getSize().getWidth() != 0)
            fx = gravity/(x) - gravity/((layout.getSize().getWidth()-x));
        if (layout.getSize().getHeight() !=0)
            fy = gravity/(y) - gravity/((layout.getSize().getHeight()-y));
        return new Point2D.Double(fx,fy);
    }

    private Point2D getTension(V vertex) {
        Point2D result = new Point2D.Double(0,0);
        Point2D location = locations.get(vertex);

        for (V v: layout.getGraph().getNeighbors(vertex)) {
            if (!v.equals(vertex)) {
                Point2D r = sub(location, locations.get(v));
                result = add(result,mul(r,tension*r.distance(0,0))) ;
            }
        }
        return result;
    }

    private Point2D getCentralTension(V vertex) {
        Point2D location = locations.get(vertex);
        return new Point2D.Double(-location.getX()*1*tension,-location.getY()*1*tension);
       // return new Point2D.Double(0,0);
    }

    private Point2D resistance(Point2D point) {
        double length = point.distance(0, 0);
        double mul = Math.max(0, (length - resistanceStrength) / length);

        if (mul > 0) return point;
        else return new Point2D.Double(0,0);
    //    else return new Point2D.Double(point.get-X()*mul, point.getY()*mul);
    }

    private Point2D getForce(V vertex) {

        System.out.println(vertex + " "+ getCentralTension(vertex));

        return resistance(
                add(getCentralTension(vertex),
                    add(getBorderForce(vertex),
                        add(getGravity(vertex),
                                getTension(vertex)
                        )
                    )
                )
        );
    }

    public boolean balance() {
        boolean isEquilibrium = true;
        Map<V, Point2D> result = new HashMap<>();


        for (V v: layout.getGraph().getVertices()) {
    //        System.out.println(v+":" + "gravity=" + getGravity(v) + "  tension="+getTension(v) + "  force=" + getForce(v));

            Point2D point = add(locations.get(v), mul(getForce(v), dt));
            if (!isEquilibrium || point.getX() != 0 || point.getY() != 0) isEquilibrium = false;
            result.put(v,point );
        }

        locations = result;
        return isEquilibrium;
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



}
