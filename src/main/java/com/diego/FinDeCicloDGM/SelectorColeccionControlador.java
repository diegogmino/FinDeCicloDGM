package com.diego.FinDeCicloDGM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Informacion;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SelectorColeccionControlador extends ControladorConNavegabilidad implements Initializable {
	
	@FXML
    private Pane root;
	
	@FXML
    private Pane librosPane;

    @FXML
    private Pane musicaPane;
    
    @FXML
    private Pane masColeccionesPane;

    @FXML
    private Button botonLibros;

    @FXML
    private Button botonMusica;
    
    @FXML
    private Button botonMas;
    
    private Parent fxmlLibro;
    private Parent fxmlMusica;
    private Parent fxmlMasColecciones;
    
	public void cargarLibros() {
		
		botonLibros.setDisable(true);
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), librosPane);
        translate.setToX(-450);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("coleccionLibros");
        }); 
		
	}
	
	public void cargarMusica() {
		
		botonMusica.setDisable(true);
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), musicaPane);
        translate.setToY(-500);
        translate.play();
		
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("coleccionMusica");
        });
        
	}
	
	public void cargarMasColecciones() {
		
		botonMas.setDisable(true);
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), masColeccionesPane);
        translate.setToX(450);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("masColecciones");
        });
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			fxmlLibro = FXMLLoader.load(getClass().getResource("LibrosPane.fxml"));
			librosPane.getChildren().removeAll();
			librosPane.getChildren().setAll(fxmlLibro);
			
			fxmlMusica = FXMLLoader.load(getClass().getResource("MusicaPane.fxml"));
			musicaPane.getChildren().removeAll();
			musicaPane.getChildren().setAll(fxmlMusica);
			
			fxmlMasColecciones = FXMLLoader.load(getClass().getResource("MasColeccionesPane.fxml"));
			masColeccionesPane.getChildren().removeAll();
			masColeccionesPane.getChildren().setAll(fxmlMasColecciones);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
