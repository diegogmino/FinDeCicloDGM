package com.diego.FinDeCicloDGM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
		
		procesando.setVisible(true);
		
		Libro libro;
		
		String directorioPortada = descagarPortada();
		
		if(fechaLectura == null) {
			libro = new Libro(Long.parseLong(isbn.getText()), titulo.getText(), autor.getText(), Integer.parseInt(paginas.getText()), Integer.parseInt(precio.getText()), 
					genero.getValue(), leido.isSelected(), date, editorial.getText(), Integer.parseInt(numeroEdicion.getText()), directorioPortada);
		} else {
			libro = new Libro(Long.parseLong(isbn.getText()), titulo.getText(), autor.getText(), Integer.parseInt(paginas.getText()), Integer.parseInt(precio.getText()), 
					genero.getValue(), leido.isSelected(), fechaLectura.getValue(), editorial.getText(), Integer.parseInt(numeroEdicion.getText()), directorioPortada);
		}
		
		
		if(LibroDao.insertarLibro(libro)) {
			System.out.println("Libro insertado");
			LibroDao.anhadirLibroUsuario(libro, Informacion.usuario);
			System.out.println("Libro añadido a usuario");
			Informacion.dialogoAnhadirLibro.close();
			
		} else {
			System.out.println("Error al insertar el libro");
		}
		
		procesando.setVisible(false);
		
	}
	
	private String descagarPortada() {
		// Método para descargar la imagen introducida en el campo de portada y guardarla en el directorio pertinente para luego poder acceder a ellas, 
		// simulando que es el servidor privado de la aplicación.
		BufferedImage image;
		String ruta = null;
		
		try {
			
			image = ImageIO.read(new URL(portada.getText()));
			// Con .replaceAll("\\s+", "") elimino todos los espacios en blanco del título
			ruta = "portadas\\" + isbn.getText() + "_" + titulo.getText().replaceAll("\\s+", "") +".png";
			ImageIO.write(image , "png", new File(ruta));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ruta;
		
		
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
		
		procesando.setVisible(true);
		
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
	    
	    procesando.setVisible(false);
		
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
		
		// Bloquea las fechas futuras
		fechaLectura.setDayCellFactory(param -> new DateCell() {
	        @Override
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
	        }
	    });
		
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
