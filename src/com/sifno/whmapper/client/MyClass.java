package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.IsSerializable;

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
