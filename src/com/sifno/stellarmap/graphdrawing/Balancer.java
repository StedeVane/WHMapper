package com.sifno.stellarmap.graphdrawing;

import com.sifno.stellarmap.graphdrawing.PlanarGraph;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Алёна on 11.02.2016.
 */
public class Balancer<V,E> {
    private PlanarGraph<V,E> graph;

    private double resistanceStrength = 1.0;
    private double gravity = 1E4;
    private double tension = 5E-3;
    private double dt = 0.016666667;

    public Balancer(PlanarGraph graph) {
        this.graph = graph;
    }

    private Point2D getGravity(V vertex) {
        Point2D result = new Point2D.Double(0,0);
        Point2D location = graph.getLocation(vertex);

        for (V v: graph.getVertices()) {
            if (!v.equals(vertex)) {
                Point2D r = sub(graph.getLocation(v), location);
                result = add(result, mul(r, gravity / r.distanceSq(0, 0)));
            }
        }
        return result;
    }

    private Point2D getBorderForce(V vertex) {
        Point2D location = graph.getLocation(vertex);

        double fx = 0,fy = 0;
        double x = location.getX();
        double y = location.getY();

        if (graph.getSize().getWidth() != 0)
            fx = gravity/(x) - gravity/((graph.getSize().getWidth()-x));
        if (graph.getSize().getHeight() !=0)
            fy = gravity/(y) - gravity/((graph.getSize().getHeight()-y));
        return new Point2D.Double(fx,fy);
    }

    private Point2D getTension(V vertex) {
        Point2D result = new Point2D.Double(0,0);
        Point2D location = graph.getLocation(vertex);

        for (V v: graph.getNeighbors(vertex)) {
            if (!v.equals(vertex)) {
                Point2D r = sub(location, graph.getLocation(v));
                result = add(result,mul(r,tension*r.distance(0,0))) ;
            }
        }
        return result;
    }

    private Point2D getCentralTension(V vertex) {
        Point2D location = graph.getLocation(vertex);
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

    public boolean nextFrame() {
        boolean isEquilibrium = true;
        Map<V, Point2D> result = new HashMap<>();


        for (V v: graph.getVertices()) {
            //        System.out.println(v+":" + "gravity=" + getGravity(v) + "  tension="+getTension(v) + "  force=" + getForce(v));

            if (graph.isLocked(v)) continue;

            Point2D point = add(graph.getLocation(v), mul(getForce(v), dt));
            if (!isEquilibrium || point.getX() != 0 || point.getY() != 0) isEquilibrium = false;
            result.put(v,point );
        }

        graph.setLocations(result);
//        locations = result;
        return isEquilibrium;
    }

    public void leadToEquilibrium() {
        // this.layout = layout;

        while (!nextFrame()) { }

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
