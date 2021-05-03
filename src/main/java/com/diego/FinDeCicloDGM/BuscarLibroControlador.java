package com.diego.FinDeCicloDGM;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuscarLibroControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
	private TextField isbn;

	@FXML
	private Button buscar;

	@FXML
	private ImageView portadaImageView;

	@FXML
	private RadioButton isbnRB;

	@FXML
	private Button agregarAColeccion;

	@FXML
	private Button aportarLibro;

	@FXML
	private RadioButton tituloRB;

	@FXML
	private TextField titulo;

	@FXML
	private RadioButton autorRB;

	@FXML
	private RadioButton generoRB;
	
	@FXML
    private ComboBox<String> genero;

	@FXML
	private TextField autor;
    
    Libro libroEncontrado;
    List<Libro> librosEncontrados;
    
    public void activarDesactivarRB() {
    	
    	if(isbnRB.isSelected()) {
    		isbn.setDisable(false);
    	} else {
    		isbn.setDisable(true);
    	}
    	
    	if(tituloRB.isSelected()) {
    		titulo.setDisable(false);
    	} else {
    		titulo.setDisable(true);
    	}
    	
    	if(autorRB.isSelected()) {
    		autor.setDisable(false);
    	} else {
    		autor.setDisable(true);
    	}
    	
    	if(generoRB.isSelected()) {
    		genero.setDisable(false);
    	} else {
    		genero.setDisable(true);
    	}
    	
    	isbn.clear();
    	autor.clear();
    	titulo.clear();
    	genero.setValue(null);
    	portadaImageView.setImage(null);
    	activarBuscar();
    	
    }
    
    
    public void activarBuscar() {
    	if((!isbn.getText().isEmpty()) || (!titulo.getText().isEmpty()) || (!autor.getText().isEmpty()) || (genero.getValue() != null)) {
    		buscar.setDisable(false);
    	} else {
    		buscar.setDisable(true);
    	}
    }
    
    public void agregarAColeccion() {
    	
    	List<Libro> librosUsuario = LibroDao.buscarLibrosPorUsuario(Informacion.usuario);
    	
    	
    	// Informacion.libroSeleccionado hace referencia a un libro seleccionado a través del TableView del dialogo de selección
    	if(Informacion.libroSeleccionado.getIsbn() != null) {
    		
    		boolean encontrado = comprobarLibroColeccion(librosUsuario, Informacion.libroSeleccionado);
    		
    		if(!encontrado) {
    			
    			LibroDao.anhadirLibroUsuario(Informacion.libroSeleccionado, Informacion.usuario);
        		Informacion.libroSeleccionado = new Libro();
        		Informacion.dialogoAnhadirLibro.close();
        		
    		} else {
    			System.out.println("Ya tienes este libro en tu colección");
    		}
    		
    		
    		
    	} else {

    		// libroEncontrado es la variable donde se guarda el libro resultante de la búsqueda por isbn
    		boolean encontrado = comprobarLibroColeccion(librosUsuario, libroEncontrado);
    		
    		if(!encontrado) {
    			
    			LibroDao.anhadirLibroUsuario(libroEncontrado, Informacion.usuario);
        		Informacion.dialogoAnhadirLibro.close();
        		
    		} else {
    			System.out.println("Ya tienes este libro en tu colección");
    		}

    	}
    	
    }

	private boolean comprobarLibroColeccion(List<Libro> librosUsuario, Libro libroAGuardar) {
		
		int encontrado = 0;
		
		for(Libro libro : librosUsuario) {
    		if(libro.getIsbn().equals(libroAGuardar.getIsbn())) {
    			encontrado++;
    		} 
    	}
		
		if(encontrado == 0) {
			return false;
		} else {
			return true;
		}
	}


	public void buscar() {
    	
    	if(!isbn.getText().isEmpty()) {
    		
    		libroEncontrado = LibroDao.buscarLibroISBN(isbn.getText());
    		
    		if(libroEncontrado.getIsbn() != null) {
    			File portada = new File(libroEncontrado.getPortada());

    			portadaImageView.setImage(new Image(portada.toURI().toString()));
    			agregarAColeccion.setDisable(false);
    		} else {
    			aportarLibro.setDisable(false);
    		}

    	} else if(!titulo.getText().isEmpty()) {
    		
    		librosEncontrados = LibroDao.buscarLibroTitulo(titulo.getText());
    		
    		if(!librosEncontrados.isEmpty()) {
    			Informacion.libros = librosEncontrados;
    			lanzarDialogoSeleccion();
    		} else {
    			aportarLibro.setDisable(false);
    			// mostrar imagen de libro no encontrado
    		}

    	} else if(!autor.getText().isEmpty()) {
    		
    		librosEncontrados = LibroDao.buscarLibroAutor(autor.getText());
    		
    		if(!librosEncontrados.isEmpty()) {
    			Informacion.libros = librosEncontrados;
    			lanzarDialogoSeleccion();
    		} else {
    			aportarLibro.setDisable(false);
    			// mostrar imagen de libro no encontrado
    		}
    		
    	} else if(genero.getValue() != null) {
    		
    		librosEncontrados = LibroDao.buscarLibroGenero(genero.getValue());
    		
    		if(!librosEncontrados.isEmpty()) {
    			Informacion.libros = librosEncontrados;
    			lanzarDialogoSeleccion();
    		} else {
    			aportarLibro.setDisable(false);
    			// mostrar imagen de libro no encontrado
    		}
    	}
    	
    }
    
    private void lanzarDialogoSeleccion() {
		
    	LayoutPane layoutPane = new LayoutPane();
    	
    	try {
			layoutPane.cargarPantalla("elegirLibro", ElegirLibroControlador.class.getResource("ElegirLibro.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	layoutPane.mostrarComoPantallaActual("elegirLibro");
    	final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Informacion.dialogoAnhadirLibro);
        Scene dialogScene = new Scene(layoutPane, 700 , 400);
        dialog.setTitle("Elegir libro");
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        
        Informacion.dialogoSeleccionarLibro = dialog;
        
        dialog.showAndWait();
        
        if(Informacion.libroSeleccionado.getIsbn() != null) {
        	
        	File portada = new File(Informacion.libroSeleccionado.getPortada());
			portadaImageView.setImage(new Image(portada.toURI().toString()));
        	agregarAColeccion.setDisable(false);
        }

	}


	public void aportarLibro() {
    	this.layout.mostrarComoPantallaActual("nuevoLibro");
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Bloquear valores no numericos en el campo del ISBN-13
	    isbn.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			isbn.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
	    
		
		genero.getItems().removeAll(genero.getItems());
		genero.getItems().addAll("Aventuras", "Ciencia ficción", "Drama", "Fantasía", "Gótico", "Humor", "Novela negra", "Realismo", "Romántico", "Terror");
		
		libroEncontrado = null;
		librosEncontrados = new ArrayList<Libro>();
		
		isbn.setDisable(true);
		titulo.setDisable(true);
		autor.setDisable(true);
		genero.setDisable(true);
		
		aportarLibro.setDisable(true);
		buscar.setDisable(true);
		agregarAColeccion.setDisable(true);
		
	}

}
