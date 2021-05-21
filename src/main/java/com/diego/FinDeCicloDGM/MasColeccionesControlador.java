package com.diego.FinDeCicloDGM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Informacion;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MasColeccionesControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
    private VBox barraNavegacion;


    public void cerrarSesion() {

    	TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("login");
        	Informacion.stage.setTitle(Informacion.TITULO_APLICACION);
        });
    	
    }

    public void mostrarBarraNavegacion() {

    	TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(160);
        translate.play();
    	
    }

    public void mostrarLibros() {
    	
    	TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	
        	try {
				this.layout.cargarColeccionLibros(ColeccionLibrosControlador.class.getResource("ColeccionLibros.fxml"));
			} catch (IOException e1) {}
        	
        	Informacion.stage.setTitle(Informacion.TITULO_APLICACION + " - " + Informacion.usuario.getNombre() + " " + Informacion.usuario.getApellidos() + " - Libros");
        });

    }

    public void mostrarMusica() {
    	
    	TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	
        	try {
    			this.layout.cargarColeccionMusica(ColeccionMusicaControlador.class.getResource("ColeccionMusica.fxml"));
    		} catch (IOException e1) {}
        	
        	Informacion.stage.setTitle(Informacion.TITULO_APLICACION + " - " + Informacion.usuario.getNombre() + " " + Informacion.usuario.getApellidos() + " - Música");
        });

    }

    public void ocultarBarraNavegacion() {

    	TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
    	
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
