package com.sifno.whmapper.server;

import java.util.Map;

/**
 * Created by Алёна on 23.07.2015.
 */
public class Translation {
    private String de;
    private String en;
    private String fr;
    private String ja;
    private String ru;
    private String zh;

    public Translation(Map<String,String> map){

        de = map.get("de");
        en = map.get("en");
        fr = map.get("fr");
        ja = map.get("ja");
        ru = map.get("ru");
        zh = map.get("zh");
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }
}
