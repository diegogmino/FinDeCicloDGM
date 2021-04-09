package com.diego.FinDeCicloDGM;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ColeccionLibrosControlador extends ControladorConNavegabilidad implements Initializable {
	
	@FXML
    private Pane root;

    @FXML
    private VBox barraNavegacion;
	
	public void mostrarBarraNavegacion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(160);
        translate.play();
		
	}
	
	public void ocultarBarraNavegacion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
	}
	
	public void mostrarMusica() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("coleccionMusica");
        });
        
	}
	
	public void mostrarOtrasColecciones() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("masColecciones");
        });
		
	}
	
	public void cerrarSesion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("login");
        });
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
