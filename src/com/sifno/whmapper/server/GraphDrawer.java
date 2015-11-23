package com.sifno.whmapper.server;

import com.sifno.stellarmap.graphdrawing.UndirectedSpareGraph;
import com.sifno.stellarmap.graphdrawing.FDDAlgorithm;
import com.sifno.stellarmap.graphdrawing.Pair;
import com.sifno.stellarmap.graphdrawing.PlanarGraphO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

/**
 * Created by Алёна on 13.11.2015.
 */
public class GraphDrawer extends JPanel {

    private PlanarGraphO<Integer,Integer> graph;


    public GraphDrawer() {

        setPreferredSize(new Dimension(800, 400));
        setSize(new Dimension(800, 400));
        graph = new PlanarGraphO(new UndirectedSpareGraph<>());

        graph.setHeight(400);
        graph.setWidth(800);

        graph.addVertex(1);
        graph.setLocation(1, new Point2D.Double(130, 200));

        graph.addVertex(2);
        graph.setLocation(2, new Point2D.Double(280, 200));

        graph.addEdge(1, 1, 2);

        graph.addVertex(3);
        graph.setLocation(3, new Point2D.Double(130,10));
        graph.addEdge(2,1,3);

        FDDAlgorithm alg = new FDDAlgorithm(graph);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alg.nextFrame();
     //           System.out.println("paint");
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int r = 50;
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.GREEN);
        for (Integer v: graph.getVertices()) {
            Point2D p = graph.getLocation(v);
            g2.fillOval((int)p.getX()-r/2,(int)p.getY()-r/2,r,r);
        }

        g2.setColor(Color.BLUE);
        for (Integer e: graph.getEdges()) {
            Pair<Integer> edge = graph.getEdge(e);

            Point2D p1 = graph.getLocation(edge.getFirst());
            Point2D p2 = graph.getLocation(edge.getSecond());

            g2.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
        }

    }
}
