package com.sifno.whmapper.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sifno.whmapper.server.SolarSystem;

/**
 * Created by Pavel on 01.08.2015.
 */
public class WHMapper implements EntryPoint {

    private Label label;
    private Button button, drawConnection;
    private PickupDragController dragController;
    private GraphPanel graphPanel;

    public void onModuleLoad() {

        //RootPanel.get().setPixelSize(600, 600);


       /* Image img = new Image("http://forum.eve-ru.com/public/style_images/eve_ru/logo.png");
        RootPanel.get().add(img, 40, 30);
        dragController.makeDraggable(img);*/

        graphPanel = new GraphPanel();
        graphPanel.addStyleName("gwt-GraphPanel");
        RootPanel.get().add(graphPanel);

        dragController = new PickupDragController(graphPanel,true);


        label = new Label("Hello");
        RootPanel.get().add(label);
        dragController.makeDraggable(label);

        button = new Button("get Jita");
        RootPanel.get().add(button);


        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
/*
                AsyncCallback<MyClass> callback = new AsyncCallback<MyClass>() {
                    public void onFailure(Throwable caught) {

                        label.setText(caught.getMessage());
                    }

                    public void onSuccess(MyClass result) {
                        label.setText(result.getName());
                    }
                };

                LayoutMap.App.getInstance().getObject("Pavel",callback);
*/
                AsyncCallback<SolarSystemClient> callback = new AsyncCallback<SolarSystemClient>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        label.setText(caught.getMessage());
                        System.out.println(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(SolarSystemClient result) {
                        SolarSystemWidget ssw = new SolarSystemWidget(result);
                        graphPanel.add(ssw, 100, 100);
                        dragController.makeDraggable(ssw);
                    }
                };

                LayoutMap.App.getInstance().getSolarSystemClient("Jita", callback);

            }
        });



        drawConnection = new Button("draw");
        RootPanel.get().add(drawConnection);

        drawConnection.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                graphPanel.drawConnections();
            }
        });

    }
}
