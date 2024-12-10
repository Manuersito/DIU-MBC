package com.example.reservahotel.Util;

public class WebUtil {
    private String url;

    public WebUtil(String initialUrl) {
        this.url = initialUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
