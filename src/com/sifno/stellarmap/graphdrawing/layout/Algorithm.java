package com.sifno.stellarmap.graphdrawing.layout;

/**
 * Created by Алёна on 23.11.2015.
 */
public interface Algorithm<V,E> {


    public Layout<V,E> getEquilibriumLayout(Layout<V,E> layout);


}
