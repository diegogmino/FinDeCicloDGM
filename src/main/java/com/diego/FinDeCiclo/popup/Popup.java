package com.diego.FinDeCiclo.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup {
	
	 public static Alert lanzarPopup(String titulo, String contenido, int tipo) {
	       // Método para crear un popup con los string recibidos y devolverlo 
	       Alert popup = null;
	       if (tipo == 1) {
	           popup = new Alert(Alert.AlertType.CONFIRMATION);
	       } else if(tipo == 2) {
	           popup = new Alert(Alert.AlertType.ERROR);
	       }
	       
	       popup.setTitle(titulo);
	       popup.setHeaderText(null);
	       popup.setContentText(contenido);
	       popup.initStyle(StageStyle.DECORATED); 
	       Stage stage = (Stage) popup.getDialogPane().getScene().getWindow();
	       //stage.getIcons().add(new Image("/img/libro.png"));
	       
	       DialogPane dialogPane = popup.getDialogPane();
	       dialogPane.getStylesheets().add("/FinDeCicloDGM/src/main/resources/com/diego/estilos/popup.css");
	       dialogPane.getStyleClass().add("popup");
	       
	       return popup;
	            
	   }

}
