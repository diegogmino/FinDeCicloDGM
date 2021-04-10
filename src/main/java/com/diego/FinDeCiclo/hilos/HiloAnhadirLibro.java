package com.diego.FinDeCiclo.hilos;

import java.io.IOException;

import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCicloDGM.LayoutPane;
import com.diego.FinDeCicloDGM.LoginControlador;
import com.diego.FinDeCicloDGM.NuevoLibroControlador;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HiloAnhadirLibro extends Thread {
	
	public HiloAnhadirLibro() {}
	
	@Override
	public void run() {
		
		LayoutPane layoutPane = new LayoutPane();
		
		try {
			layoutPane.cargarPantalla("nuevoLibro", NuevoLibroControlador.class.getResource("NuevoLibro.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Platform.runLater(()->{

			layoutPane.mostrarComoPantallaActual("nuevoLibro");
			
			final Stage dialog = new Stage();
			dialog.initModality(Modality.NONE);
	        dialog.initOwner(Informacion.stage);
	        Scene dialogScene = new Scene(layoutPane, 500 , 650);
	        
	        dialog.setTitle("Nuevo libro");
	        dialog.setResizable(false);
	        dialog.setScene(dialogScene);
	        dialog.show();
    		
		});
		
		
	}
	

}
