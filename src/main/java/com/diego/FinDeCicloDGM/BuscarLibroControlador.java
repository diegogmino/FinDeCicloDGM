package com.diego.FinDeCicloDGM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    	LibroDao.anhadirLibroUsuario(libroEncontrado, Informacion.usuario);
    }
    
    public void buscar() {
    	
    	if(!isbn.getText().isEmpty()) {
    		
    		libroEncontrado = LibroDao.buscarLibroISBN(isbn.getText());
    		System.out.println("Libro: " + libroEncontrado.getTitulo());
    		
    	} else if(!titulo.getText().isEmpty()) {
    		
    		librosEncontrados = LibroDao.buscarLibroTitulo(titulo.getText());
    		for(Libro l : librosEncontrados) {
    			System.out.println("Libro: " + l.getTitulo());
    		}
    		
    	} else if(!autor.getText().isEmpty()) {
    		
    		librosEncontrados = LibroDao.buscarLibroAutor(autor.getText());
    		for(Libro l : librosEncontrados) {
    			System.out.println("Libro: " + l.getTitulo());
    		}
    		
    	} else if(genero.getValue() != null) {
    		
    		librosEncontrados = LibroDao.buscarLibroGenero(genero.getValue());
    		for(Libro l : librosEncontrados) {
    			System.out.println("Libro: " + l.getTitulo());
    		}
    		
    	}
    	
    	/*
    	
    	if(libroEncontrado.getIsbn() == null) {
    		System.out.println("No existe el libro introducido");
    		aportarLibro.setDisable(false);
    	} else {
    		portadaImageView.setImage(new Image(libroEncontrado.getPortada()));
    		agregarAColeccion.setDisable(false);
    	}
    	
    	*/
    	
    }
    
    public void aportarLibro() {
    	this.layout.mostrarComoPantallaActual("nuevoLibro");
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
