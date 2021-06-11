package com.diego.FinDeCicloDGM;

import com.diego.FinDeCiclo.pojos.Informacion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Lanzador extends Application {
	
    public void start (Stage primaryStage) throws Exception{
        
        LayoutPane layoutPane = new LayoutPane();
        layoutPane.cargarPantalla("login", LoginControlador.class.getResource("Login.fxml"));
        layoutPane.cargarPantalla("selectorColeccion", SelectorColeccionControlador.class.getResource("SelectorColeccion.fxml"));
        layoutPane.cargarPantalla("masColecciones", MasColeccionesControlador.class.getResource("MasColecciones.fxml"));
        
        layoutPane.mostrarComoPantallaActual("login");
        
        Scene escena = new Scene(layoutPane, 1200 , 650);
        primaryStage.setTitle(Informacion.TITULO_APLICACION);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResource("../img/icono.png").toURI().toString()));
        primaryStage.setScene(escena);
        primaryStage.show();
        Informacion.stage = primaryStage;
        
    }
    public static void main(String[]  args) {
        launch(args);
    }
}
