package com.diego.FinDeCicloDGM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ElegirLibroControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
    private TableView<Libro> tabla;

    @FXML
    private Button seleccionar;
    
    @FXML
    private Button cargarLibros;
	
    public void cargarLibros() {
    	
    	ObservableList<Libro> libros = FXCollections.observableArrayList();
	    libros.addAll(Informacion.libros);
	    tabla.setItems(libros);  
    	
    }
    
    public void activarSeleccionar() {
    	seleccionar.setDisable(false);
    }
    
    public void seleccionar() {
    	Informacion.libroSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	Informacion.dialogoSeleccionarLibro.close();
    }
    
    private void configurarTamanioColumnas() {
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Libro, ?>> columnas = tabla.getColumns();
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 17);
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 48);
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE *25);
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 15);
        
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		seleccionar.setDisable(true);
		configurarTamanioColumnas();
	}

}
