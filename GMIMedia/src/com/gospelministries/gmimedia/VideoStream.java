package com.gospelministries.gmimedia;

public class VideoStream {


    public int icon;
    public String title;
    public String urlHi  = null; // High resolution
    public String urlMed = null; // Medium resolution
    public String urlLo  = null; // Low resolution
    public VideoStream(){
        super();
    }

    public VideoStream(int icon, String title, String lo, String med, String hi )  {
        super();
        this.icon   = icon;
        this.title  = title;
        this.urlHi  = hi;
        this.urlMed = med;
        this.urlLo  = lo;
    }

    public VideoStream(int icon, String title)  {
        super();
        this.icon   = icon;
        this.title  = title;
    }

}
