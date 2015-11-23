package com.sifno.whmapper.client;

import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandlerAdapter;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.whmapper.client.Graph.StarSystemWidget;
import com.sifno.whmapper.client.Graph.VisualizationViewer;


/**
 * Created by Pavel on 01.08.2015.
 */
public class WHMapper implements EntryPoint {

    private Button button;
    private Label label;

    public static PickupDragController dragController;

    @Override
    public void onModuleLoad() {

        label = new Label("Label");
        RootPanel.get().add(label);

        button = new Button("Button");

        VisualizationViewer vv = new VisualizationViewer();
        vv.addStyleName("gwt-GraphPanel");

        RootPanel.get().add(button);
        RootPanel.get().add(vv);

        dragController = new PickupDragController(vv,true);
        dragController.addDragHandler(new DragHandlerAdapter() {
            @Override
            public void onDragEnd(DragEndEvent event) {
                super.onDragEnd(event);

                if (!(event.getContext().draggable instanceof StarSystemWidget)) return;

                StarSystemWidget ssw = (StarSystemWidget) event.getContext().draggable;
                ssw.firePositionChange();
            }
        });
        dragController.registerDropController(vv.getDropController());

//        vv.add(debug2, 50, 50);
//        dragController.makeDraggable(debug2);

        StarSystemWidget ssw1 = new StarSystemWidget(new StarSystem(1), vv);
        StarSystemWidget ssw2 = new StarSystemWidget(new StarSystem(2), vv);
        StarSystemWidget ssw3 = new StarSystemWidget(new StarSystem(3), vv);

        vv.add(ssw1, 51, 100);
        vv.add(ssw2, 49, 150);
        vv.add(ssw3, 50, 250);



        dragController.makeDraggable(ssw1);
        dragController.makeDraggable(ssw2);
        dragController.makeDraggable(ssw3);

        vv.addConnection(1,ssw1, ssw2);
        vv.addConnection(2,ssw3, ssw2);
    }
}

//    private Label label;
//    private Button button, drawConnection;
//
//    public static PickupDragController dragController;
//    private VisualizationViewer vv;
//
//    public static Label x,y;
//
//    public static Label debug2;
//
//    public void onModuleLoad() {
//
//        StellarGraph<Integer,Integer> graph;
//
//        //RootPanel.get().setPixelSize(600, 600);
//
//
//       /* Image img = new Image("http://forum.eve-ru.com/public/style_images/eve_ru/logo.png");
//        RootPanel.get().add(img, 40, 30);
//        dragController.makeDraggable(img);*/
//
//        debug2 = new Label("HELLO");
//
//        x = new Label("x:");
//        RootPanel.get().add(x);
//
//        y = new Label("y:");
//        RootPanel.get().add(y);
//
//        vv = new VisualizationViewer();
//        vv.addStyleName("gwt-GraphPanel");
//        RootPanel.get().add(vv);
//
//        dragController = new PickupDragController(vv,true);
//
//        dragController.addDragHandler(new DragHandlerAdapter() {
//            @Override
//            public void onDragEnd(DragEndEvent event) {
//                super.onDragEnd(event);
//
//                if (!(event.getContext().draggable instanceof StarSystemWidget)) return;
//
//                StarSystemWidget ssw = (StarSystemWidget) event.getContext().draggable;
//                ssw.firePositionChange();
//            }
//        });
//
//
//        dragController.registerDropController(vv.getDropController());
//
//        vv.add(debug2, 50, 50);
//        dragController.makeDraggable(debug2);
//
//        StarSystemWidget ssw1 = new StarSystemWidget(new StarSystem(), vv);
//        StarSystemWidget ssw2 = new StarSystemWidget(new StarSystem(), vv);
//        StarSystemWidget ssw3 = new StarSystemWidget(new StarSystem(), vv);
//
//        vv.add(ssw1, 50, 100);
//        vv.add(ssw2, 50, 150);
//        vv.add(ssw3, 50, 150);
//
//        dragController.makeDraggable(ssw1);
//        dragController.makeDraggable(ssw2);
//        dragController.makeDraggable(ssw3);
//
//        vv.addConnection(ssw1, ssw2);
//        vv.addConnection(ssw3, ssw2);
//
//        label = new Label("BLAD");
//        RootPanel.get().add(label);
//        dragController.makeDraggable(label);
//
//        button = new Button("get BLAD");
//        RootPanel.get().add(button);
//
//
//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//
////
////                AsyncCallback<MyClass> callback = new AsyncCallback<MyClass>() {
////                    public void onFailure(Throwable caught) {
////
////                        label.setText(caught.getMessage());
////                    }
////
////                    public void onSuccess(MyClass result) {
////                        label.setText(result.getName());
////                    }
////                };
////
////                Server.App.getInstance().getObject("Pavel",callback);
//
//                AsyncCallback<SolarSystemClient> callback = new AsyncCallback<SolarSystemClient>() {
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        label.setText(caught.getMessage());
//                        System.out.println(caught.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(SolarSystemClient result) {
//                        StarSystemWidget ssw = new StarSystemWidget(result, vv);
//                        vv.add(ssw, 100, 100);
//                        dragController.makeDraggable(ssw);
//                    }
//                };
//
//                Server.App.getInstance().getSolarSystemClient("Jita", callback);
//
//            }
//        });
//
//
//
//        drawConnection = new Button("draw");
//        RootPanel.get().add(drawConnection);
//
//        drawConnection.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//
//
//                vv.drawConnections();
//            }
//        });
//
//    }
