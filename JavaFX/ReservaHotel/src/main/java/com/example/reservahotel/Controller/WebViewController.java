package com.example.reservahotel.Controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import com.example.reservahotel.Util.WebUtil;

public class WebViewController {
    @FXML
    private WebView webView;

    private WebUtil model;
    private WebEngine webEngine;

    public WebViewController() {
        // El modelo debe ser inicializado por la clase principal o de otro modo
        model = new WebUtil("http://217.160.2.154/");
    }

    @FXML
    public void initialize() {
        // Inicializar el motor de WebView
        webEngine = webView.getEngine();

        // Cargar la URL inicial
        loadUrl(model.getUrl());
    }

    public void loadUrl(String url) {
        model.setUrl(url);
        webEngine.load(url);
    }

    public void updateUrl(String newUrl) {
        loadUrl(newUrl);
    }}
