package com.diego.FinDeCiclo.hilos;

import java.io.IOException;
import java.net.URISyntaxException;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCicloDGM.BuscarLibroControlador;
import com.diego.FinDeCicloDGM.LayoutPane;
import com.diego.FinDeCicloDGM.NuevoLibroControlador;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HiloAnhadirLibro extends Thread {
	
	public HiloAnhadirLibro() {}
	
	@Override
	public void run() {
		
		LayoutPane layoutPane = new LayoutPane();
		
		try {
			layoutPane.cargarPantalla("nuevoLibro", NuevoLibroControlador.class.getResource("NuevoLibro.fxml"));
			layoutPane.cargarPantalla("buscarLibro", BuscarLibroControlador.class.getResource("BuscarLibro.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Platform.runLater(()->{

			layoutPane.mostrarComoPantallaActual("buscarLibro");
			
			final Stage dialog = new Stage();
			dialog.initModality(Modality.NONE);
	        dialog.initOwner(Informacion.stage);
	        
	        try {
				dialog.getIcons().add(new Image(getClass().getResource("../../img/icono.png").toURI().toString()));
			} catch (URISyntaxException e) {}
	        
	        Scene dialogScene = new Scene(layoutPane, 800 , 650);
	        
	        dialog.setTitle("Nuevo libro");
	        dialog.setResizable(false);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        Informacion.dialogoAnhadirLibro = dialog;
    		
		});
		
		
	}
	

}
