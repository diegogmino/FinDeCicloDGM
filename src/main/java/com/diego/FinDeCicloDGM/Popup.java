package com.diego.FinDeCicloDGM;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class Popup  {
	
	public static Alert lanzarPopup(String titulo, String contenido, int tipo) {
		
		Alert popup = null;
	       if (tipo == 1) {
	           popup = new Alert(Alert.AlertType.INFORMATION);
	       } else if(tipo == 2) {
	           popup = new Alert(Alert.AlertType.ERROR);
	       } else if(tipo == 3) {
	    	   popup = new Alert(Alert.AlertType.CONFIRMATION);
	       }
	       
	       popup.setTitle(titulo);
	       popup.setHeaderText(null);
	       popup.setContentText(contenido);
	       popup.initStyle(StageStyle.DECORATED);
	       
	       return popup;
	}	 

}
