package com.diego.FinDeCiclo.hilos;

import java.io.IOException;

import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCicloDGM.BuscarAlbumControlador;
import com.diego.FinDeCicloDGM.BuscarLibroControlador;
import com.diego.FinDeCicloDGM.LayoutPane;
import com.diego.FinDeCicloDGM.NuevoAlbumControlador;
import com.diego.FinDeCicloDGM.NuevoLibroControlador;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HiloAnhadirAlbum extends Thread {
	
	public HiloAnhadirAlbum() {}
	
	@Override
	public void run() {
		
		LayoutPane layoutPane = new LayoutPane();
		
		try {
			layoutPane.cargarPantalla("nuevoAlbum", NuevoAlbumControlador.class.getResource("NuevoAlbum.fxml"));
			layoutPane.cargarPantalla("buscarAlbum", BuscarAlbumControlador.class.getResource("BuscarAlbum.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Platform.runLater(()->{

			layoutPane.mostrarComoPantallaActual("buscarAlbum");
			
			final Stage dialog = new Stage();
			dialog.initModality(Modality.NONE);
	        dialog.initOwner(Informacion.stage);
	        Scene dialogScene = new Scene(layoutPane, 800 , 650);
	        
	        dialog.setTitle("Nuevo álbum");
	        dialog.setResizable(false);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        Informacion.dialogoAnhadirAlbum = dialog;
    		
		});
		
	}

}
