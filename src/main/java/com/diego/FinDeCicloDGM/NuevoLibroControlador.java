package com.diego.FinDeCicloDGM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private ComboBox<String> tapa;

    @FXML
    private TextField portada;
    
    @FXML
    private Button guardarLibro;
    
    @FXML
    private Button btnComprobarPortada;
    
    @FXML
    private ImageView imagenPortada;
    
    private boolean portadaValida = false;
    private Path rutaGeneros = Path.of("ficherosInfo\\generosLibros.csv");
    private Path rutaTapa = Path.of("ficherosInfo\\tapaLibros.csv");
	
	public void guardarLibro() {
		
		Libro libroBuscar = LibroDao.buscarLibroISBN(isbn.getText());
		
		if(libroBuscar.getIsbn() == null) {
			
			Libro libro;
			
			String directorioPortada = descagarPortada();
			

			libro = new Libro(Long.parseLong(isbn.getText()), titulo.getText(), autor.getText(), Integer.parseInt(paginas.getText()), Double.parseDouble(precio.getText()), 
						genero.getValue(), tapa.getValue(), editorial.getText(), directorioPortada);
			
			
			if(LibroDao.insertarLibro(libro)) {
				LibroDao.anhadirLibroUsuario(libro, Informacion.usuario);
				
				if(Informacion.usuario.getRango() == 1) {
					
					Alert popup = Popup.lanzarPopup("Libro añadido a la colección", "El libro «" + libro.getTitulo() + "» se ha añadido a tu colección "
	        				+ "correctamente", 1);
					cargarEstilosPopup(popup);
	        		popup.showAndWait();
					
				} else {
					
					Alert popup = Popup.lanzarPopup("Libro añadido a la base de datos", "El libro «" + libro.getTitulo() + "» se ha añadido a la base de datos "
	        				+ "correctamente", 1);
					cargarEstilosPopup(popup);
	        		popup.showAndWait();
					
				}
		
				Informacion.dialogoAnhadirLibro.close();
				
			} else {
				
				Alert popup = Popup.lanzarPopup("Error", "Error al guardar el libro «" + libro.getTitulo() + "»", 2);
				cargarEstilosPopup(popup);
        		popup.showAndWait();
				
			}
			
			
		} else {
			
			Alert popup = Popup.lanzarPopup("Error", "Ya existe un libro con el ISBN-13 introducido", 2);
			cargarEstilosPopup(popup);
    		popup.showAndWait();
			
		}
		
		
	}
	
	private String descagarPortada() {
		// Método para descargar la imagen introducida en el campo de portada y guardarla en el directorio pertinente para luego poder acceder a ellas, 
		// simulando que es el servidor privado de la aplicación.
		BufferedImage image;
		String ruta = null;
		
		try {
			
			image = ImageIO.read(new URL(portada.getText()));
			// Con .replaceAll elimino todos los espacios en blanco, las comas, los puntos, las interrogaciones y admiraciones del título
			ruta = "portadas\\" + isbn.getText() + "_" + titulo.getText().replaceAll("\\s+", "").replaceAll("\\,", "").replaceAll("\\.", "").replaceAll("\\?", "")
					.replaceAll("\\¿", "").replaceAll("¡", "").replaceAll("!", "") +".png";
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
		tapa.setValue("Tipo de tapa");
		portada.clear();
		try {
			imagenPortada.setImage(new Image(getClass().getResource("../img/portada_imagen.png").toURI().toString()));
		} catch (URISyntaxException e) {}
		
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
				&& (!precio.getText().isEmpty()) && (!editorial.getText().isEmpty()) && (genero.getValue() != null) && (tapa.getValue() != null)
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
	
	private void cargarEstilosPopup(Alert popup) {
		
		DialogPane dialogPane = popup.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("../estilos/popup.css").toExternalForm());
		dialogPane.getStyleClass().add("popup");
		
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		
		try {
			stage.getIcons().add(new Image(getClass().getResource("../img/icono.png").toURI().toString()));
		} catch (URISyntaxException e) {}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		genero.getItems().removeAll(genero.getItems());
		tapa.getItems().removeAll(tapa.getItems());
		List<String> generos = null;
		List<String> tapas = null;
	    
		try {
			generos = Files.lines(rutaGeneros).collect(Collectors.toList());
			tapas = Files.lines(rutaTapa).collect(Collectors.toList());
		} catch (IOException e) {}
		
	    genero.setItems(FXCollections.observableArrayList(generos));
	    tapa.setItems(FXCollections.observableArrayList(tapas));
		
		guardarLibro.setDisable(true);
		btnComprobarPortada.setDisable(true);
		
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
	            if (!newValue.matches("\\d*(\\.\\d*)?")) {
	            	precio.setText(oldValue);
	            }
	        }
	    });
		
	}

}
