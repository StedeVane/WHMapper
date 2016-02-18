package com.sifno.stellarmap.graph;

import java.awt.*;

import java.util.HashMap;
import java.util.Map;


public class Balancer<V,E> {
    private PlanarGraph<V,E> graph;

    private double resistanceStrength = 5.0;
    private double gravity = 1E4;
    private double tension = 5E-3;
    private double dt = 0.016666667;

    public Balancer(PlanarGraph graph) {
        this.graph = graph;
    }

    private Point.Double getGravity(V vertex) {
        Point.Double result = new Point.Double(0,0);
        Point.Double location = graph.getLocation(vertex);

        for (V v: graph.getVertices()) {
            if (!v.equals(vertex)) {
                Point.Double r = sub(graph.getLocation(v), location);
                result = add(result, mul(r, gravity / r.distanceSq(0, 0)));
            }
        }
        return result;
    }

    private Point.Double getBorderForce(V vertex) {
        Point.Double location = graph.getLocation(vertex);

        double fx = 0,fy = 0;
        double x = location.getX();
        double y = location.getY();

        if (graph.getSize().getWidth() != 0)
            fx = gravity/(x) - gravity/((graph.getSize().getWidth()-x));
        if (graph.getSize().getHeight() !=0)
            fy = gravity/(y) - gravity/((graph.getSize().getHeight()-y));
        return new Point.Double(fx,fy);
    }

    private Point.Double getTension(V vertex) {
        Point.Double result = new Point.Double(0,0);
        Point.Double location = graph.getLocation(vertex);

        for (V v: graph.getNeighbors(vertex)) {
            if (!v.equals(vertex)) {
                Point.Double r = sub(location, graph.getLocation(v));
                result = add(result,mul(r,tension*r.distance(0,0))) ;
            }
        }
        return result;
    }

    private Point.Double getCentralTension(V vertex) {
        Point.Double location = graph.getLocation(vertex);
        return new Point.Double(-location.getX()*1*tension,-location.getY()*1*tension);
        // return new Point2D.Double(0,0);
    }

    private Point.Double resistance(Point.Double point) {
        double length = point.distance(0, 0);
        double mul = Math.max(0, (length - resistanceStrength) / length);

        if (mul > 0) return point;
        else return new Point.Double(0,0);
        //    else return new Point2D.Double(point.get-X()*mul, point.getY()*mul);
    }

    private Point.Double getForce(V vertex) {

//        System.out.println(vertex + " "+ getCentralTension(vertex));

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
        Map<V, Point.Double> result = new HashMap<>();


        for (V v: graph.getVertices()) {
            //        System.out.println(v+":" + "gravity=" + getGravity(v) + "  tension="+getTension(v) + "  force=" + getForce(v));

            if (graph.isLocked(v)) continue;
            Point.Double point = mul(getForce(v), dt);
            if (!isEquilibrium || point.getX() != 0 || point.getY() != 0) isEquilibrium = false;
            point = add(graph.getLocation(v),point);

            result.put(v,point );
        }

        graph.setLocations(result);
//        locations = result;
        return isEquilibrium;
    }

    public void leadToEquilibrium() {
        // this.layout = layout;
        int count = 0;
        while (!nextFrame()&&count++<10000) { }

    }




    private Point.Double add(Point.Double p1, Point.Double p2) {
        return new Point.Double(p1.getX()+p2.getX(), p1.getY()+p2.getY());
    }

    private Point.Double mul(Point.Double p, double k) {
        return new Point.Double(p.getX()*k,p.getY()*k);
    }

    private Point.Double normalize(Point.Double p) {
        double length = p.distance(0, 0);
        return mul(p, 1.0 / length);
    }

    private Point.Double sub(Point.Double p1, Point.Double p2) {
        return new Point.Double(p2.getX()-p1.getX(),p2.getY()-p1.getY());
    }


}
