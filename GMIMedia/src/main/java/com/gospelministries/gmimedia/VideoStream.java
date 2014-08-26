package com.gospelministries.gmimedia;

public class VideoStream {

    public int icon;
    public String title;

    /**
     * High resolution
     */
    public String urlHi = null;

    /**
     * Medium resolution
     */
    public String urlMed = null;

    /**
     * Low resolution
     */
    public String urlLo = null;

    public VideoStream() {
        this(0, null);
    }

    public VideoStream(int icon, String title) {
        this(icon, title, null, null, null);
    }

    public VideoStream(int icon, String title, String lo, String med, String hi) {
        super();
        this.icon = icon;
        this.title = title;
        this.urlHi = hi;
        this.urlMed = med;
        this.urlLo = lo;
    }
}
