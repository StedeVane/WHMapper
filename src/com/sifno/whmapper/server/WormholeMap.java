package com.sifno.whmapper.server;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Timer;

/**
 * Created by Крочак on 29.06.15.
 */
public class WormholeMap extends JPanel {

    public static SolarSystem currentSystem  = NewEden.getSolarSystem("Jita");

    WormholeModel model;

    private static final long serialVersionUID = -5345319851341875800L;
    private Graph<SolarSystem, Jump> g = null;
    private VisualizationViewer<Number, Number> vv = null;
    private AbstractLayout<Number, Number> layout = null;
    java.util.Timer timer;
    boolean done;
    protected JButton switchLayout;
    public static final int EDGE_LENGTH = 100;
    Integer v_prev = null;




    public WormholeMap() {
        model = new WormholeModel();
        g = model.getGraph();

        KKLayout<SolarSystem,Jump> kkLayout = new KKLayout<SolarSystem, Jump>(g);
    //    kkLayout.


        this.layout = new KKLayout(this.g); // new FRLayout2(this.g);

      //  Point2D transform = layout.transform(5);


        this.vv = new VisualizationViewer(this.layout, new Dimension(600, 600));
        JRootPane rp = this.getRootPane();
    //    rp.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.setFont(new Font("Serif", 0, 32));
        this.vv.getModel().getRelaxer().setSleepTime(500L);
        this.vv.setGraphMouse(new DefaultModalGraphMouse());
        this.vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        this.vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        this.vv.setForeground(Color.BLACK);
        vv.getRenderer().getVertexRenderer();


        this.add(this.vv);
        this.switchLayout = new JButton("Switch to SpringLayout");
        this.switchLayout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Dimension d = new Dimension(600, 600);
                if(switchLayout.getText().indexOf("Spring") > 0) {
                    switchLayout.setText("Switch to FRLayout");
                    layout = new edu.uci.ics.jung.algorithms.layout.SpringLayout(g, new ConstantTransformer(Integer.valueOf(100)));
                    layout.setSize(d);
                    vv.getModel().setGraphLayout(layout, d);
                } else {
                    switchLayout.setText("Switch to SpringLayout");
                    layout = new KKLayout(g);
                    vv.getModel().setGraphLayout(layout, d);
                }

            }
        });
        this.add(this.switchLayout, "South");
        this.timer = new Timer();
    }

}
