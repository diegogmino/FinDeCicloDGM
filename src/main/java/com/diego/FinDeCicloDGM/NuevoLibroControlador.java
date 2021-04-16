package com.diego.FinDeCicloDGM;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NuevoLibroControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
    private TextField isbn;

    @FXML
    private TextField titulo;

    @FXML
    private TextField autor;

    @FXML
    private TextField paginas;

    @FXML
    private TextField precio;

    @FXML
    private TextField editorial;

    @FXML
    private ComboBox<String> genero;

    @FXML
    private TextField numeroEdicion;

    @FXML
    private CheckBox leido;

    @FXML
    private DatePicker fechaLectura;

    @FXML
    private TextField portada;
    
    @FXML
    private ProgressIndicator procesando;
    
    @FXML
    private Button guardarLibro;
    
    @FXML
    private Button btnComprobarPortada;
    
    @FXML
    private ImageView imagenPortada;
    
    private boolean portadaValida = false;
    
    LocalDate date = LocalDate.parse("1900-01-01");
	
	public void guardarLibro() {
		
		Libro libro;
		
		if(fechaLectura == null) {
			libro = new Libro(Long.parseLong(isbn.getText()), titulo.getText(), autor.getText(), Integer.parseInt(paginas.getText()), Integer.parseInt(precio.getText()), 
					genero.getValue(), leido.isSelected(), date, editorial.getText(), Integer.parseInt(numeroEdicion.getText()), portada.getText());
		} else {
			libro = new Libro(Long.parseLong(isbn.getText()), titulo.getText(), autor.getText(), Integer.parseInt(paginas.getText()), Integer.parseInt(precio.getText()), 
					genero.getValue(), leido.isSelected(), fechaLectura.getValue(), editorial.getText(), Integer.parseInt(numeroEdicion.getText()), portada.getText());
		}
		
		
		if(LibroDao.insertarLibro(libro)) {
			System.out.println("Libro insertado");
		} else {
			System.out.println("Error al insertar el libro");
		}
		
	}
	
	public void limpiarCampos() {
	// Método que limpia los campos de la ventana
		isbn.clear();
		titulo.clear();
		autor.clear();
		paginas.clear();
		precio.clear();
		editorial.clear();   
		genero.setValue("Género");;
		numeroEdicion.clear();
		leido.setSelected(false);
		fechaLectura.setValue(null);
		fechaLectura.setDisable(true);
		portada.clear();
		imagenPortada.setImage(null);
		
		guardarLibro.setDisable(true);
		btnComprobarPortada.setDisable(true);
		
	}
	
	public void comprobarPortada() {
	// Método que comprueba si la url de una imagen es válida
	    try {  
	        BufferedImage image = ImageIO.read(new URL(portada.getText()));  
	        portadaValida = true;
	        imagenPortada.setImage(new Image(portada.getText()));
	        imagenPortada.setDisable(true);
	        activarGuardarLibro();
	    } catch (MalformedURLException e) {
	    	portadaValida = false;
	    	activarGuardarLibro();
	    	// Cargamos la imagen de portada no válida en el ImageView
	    	try {
				imagenPortada.setImage(new Image(getClass().getResource("../img/portada_no_valida.png").toURI().toString()));
			} catch (URISyntaxException e1) {}
	    	
	    } catch (IOException e) {
	    	portadaValida = false;
	    	activarGuardarLibro();
	    	// Cargamos la imagen de portada no válida en el ImageView
	    	try {
				imagenPortada.setImage(new Image(getClass().getResource("../img/portada_no_valida.png").toURI().toString()));
			} catch (URISyntaxException e1) {}
	    }
		
	}
	
	public void activarGuardarLibro() {
		
		if((!isbn.getText().isEmpty()) && (!titulo.getText().isEmpty()) && (!autor.getText().isEmpty()) && (!paginas.getText().isEmpty())
				&& (!precio.getText().isEmpty()) && (!editorial.getText().isEmpty()) && (genero.getValue() != null) && (!numeroEdicion.getText().isEmpty())
				&& (!portada.getText().isEmpty()) && portadaValida == true) {
			
			guardarLibro.setDisable(false);
		} else {
			guardarLibro.setDisable(true);
		}
		
	}
	
	public void activarComprobarPortada() {
		
		if(!portada.getText().isEmpty()) {
			btnComprobarPortada.setDisable(false);
		} else {
			btnComprobarPortada.setDisable(true);
		}
		
		
	}
	
	public void activarDesactivarFecha() {
	// Metodo que activa y desactiva el campo de la fecha
		if (leido.isSelected()) {
			fechaLectura.setDisable(false);
		} else {
			fechaLectura.setDisable(true);
			fechaLectura.setValue(null);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		genero.getItems().removeAll(genero.getItems());
		genero.getItems().addAll("Aventuras", "Ciencia ficción", "Drama", "Fantasía", "Gótico", "Humor", "Novela negra", "Realismo", "Romántico", "Terror");
		
		procesando.setVisible(false);
		
		guardarLibro.setDisable(true);
		btnComprobarPortada.setDisable(true);
		
		fechaLectura.setDisable(true);
		
		// Bloquear valores no numericos en el campo del ISBN-13
	    isbn.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			isbn.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
	    
	    // Bloquear valores no numericos en el campo del páginas
	    paginas.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			paginas.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
	    
	    // Bloquear valores no numericos en el campo del precio
	    precio.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			precio.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
	    
	    // Bloquear valores no numericos en el campo del número de la edición
	    numeroEdicion.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			numeroEdicion.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
		
	}

}
