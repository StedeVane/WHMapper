package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by Pavel on 01.08.2015.
 */
public class MyClass implements IsSerializable {

    private String name = "default";
    public MyClass() {
    }
    public MyClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
