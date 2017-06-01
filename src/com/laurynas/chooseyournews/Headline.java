package com.laurynas.chooseyournews;

/**
 * Created by Laurynas on 2017-05-31.
 */
public class Headline {
    String headline;
    String link;
    String tema;

    public Headline(String headline, String link, String tema) {
        this.headline = headline;
        this.link = link;
        this.tema = tema;
    }

    public Headline(String headline, String link, String tema, boolean isThing) {
        this.headline = headline;
        this.link = link;
        this.tema = tema;
        this.isThing = isThing;
    }

    public String getTema() {

        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    boolean isThing;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isThing() {
        return isThing;
    }

    public void setThing(boolean thing) {
        isThing = thing;
    }
}
