package com.diego.FinDeCicloDGM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Musica;
import com.diego.FinDeCicloDGM.dao.MusicaDao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NuevoAlbumControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
    private ImageView imagenCaratula;

    @FXML
    private TextField ean;

    @FXML
    private TextField titulo;

    @FXML
    private TextField artista;

    @FXML
    private TextField precio;

    @FXML
    private TextField duracion;

    @FXML
    private ComboBox<String> genero;

    @FXML
    private TextField caratula;

    @FXML
    private Button btnComprobarCaratula;

    @FXML
    private Button guardarAlbum;

    @FXML
    private ComboBox<String> formato;

    @FXML
    private DatePicker fechaPublicacion;

    @FXML
    private TextField discografica;
    
    private boolean caratulaValida = false;
    
    LocalDate date = LocalDate.parse("1900-01-01");
    Path rutaGeneros = Path.of("ficherosInfo\\generosAlbumes.csv");
    Path rutaFormatos = Path.of("ficherosInfo\\formatosAlbumes.csv");
	
	public void guardarAlbum() {
		
		Musica albumBuscar = MusicaDao.buscarAlbumEAN(ean.getText());
		
		if(albumBuscar.getEan() == null) {
			
			Musica album;
			
			String directorioCaratula = descagarCaratula();
			
			album = new Musica(Long.parseLong(ean.getText()), titulo.getText(), artista.getText(), genero.getValue(), formato.getValue(), Integer.parseInt(duracion.getText()), 
					directorioCaratula, Date.valueOf(fechaPublicacion.getValue()), Double.parseDouble(precio.getText()), discografica.getText());
			
			if(MusicaDao.insertarAlbum(album)) {
				System.out.println("Album insertado");
				MusicaDao.anhadirAlbumUsuario(album, Informacion.usuario);
				System.out.println("Album añadido a usuario");
				
				Alert popup = Popup.lanzarPopup("Álbum añadido a la colección", "El álbum «" + album.getTitulo() + "» se ha añadido a tu colección "
        				+ "correctamente", 1);
        		popup.showAndWait();
				
				Informacion.dialogoAnhadirAlbum.close();
				
			} else {
				
				Alert popup = Popup.lanzarPopup("Error", "Error al guardar el álbum «" + album.getTitulo() + "»", 2);
        		popup.showAndWait();
				
			}
			
			
		} else {
			
			Alert popup = Popup.lanzarPopup("Error", "Ya existe un álbum con el EAN introducido", 2);
    		popup.showAndWait();
			
		}
		
	}
	
	private String descagarCaratula() {
		
		// Método para descargar la imagen introducida en el campo de portada y guardarla en el directorio pertinente para luego poder acceder a ellas, 
		// simulando que es el servidor privado de la aplicación.
		BufferedImage image;
		String ruta = null;
		
		try {
			
			image = ImageIO.read(new URL(caratula.getText()));
			// Con .replaceAll elimino todos los espacios en blanco, las comas, los puntos, las interrogaciones y admiraciones del título
			ruta = "caratulas\\" + ean.getText() + "_" + titulo.getText().replaceAll("\\s+", "").replaceAll("\\,", "").replaceAll("\\.", "").replaceAll("\\?", "")
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
		ean.clear();
		titulo.clear();
		artista.clear();
		genero.setValue(null);
		duracion.clear();
		formato.setValue(null);
		fechaPublicacion.setValue(null);
		precio.clear();
		discografica.clear();
		caratula.clear();
		try {
			imagenCaratula.setImage(new Image(getClass().getResource("../img/caratula_imagen.png").toURI().toString()));
		} catch (URISyntaxException e) {}
		
		guardarAlbum.setDisable(true);
		btnComprobarCaratula.setDisable(true);
		
	}
	
	public void comprobarCaratula() {
		
		// Método que comprueba si la url de una imagen es válida
		
	    try {  
	        BufferedImage image = ImageIO.read(new URL(caratula.getText()));  
	        caratulaValida = true;
	        imagenCaratula.setImage(new Image(caratula.getText()));
	        imagenCaratula.setDisable(true);
	        activarGuardarAlbum();
	    } catch (MalformedURLException e) {
	    	caratulaValida = false;
	    	activarGuardarAlbum();
	    	// Cargamos la imagen de caratula no válida en el ImageView
	    	try {
	    		imagenCaratula.setImage(new Image(getClass().getResource("../img/caratula_no_valida.png").toURI().toString()));
			} catch (URISyntaxException e1) {}
	    	
	    } catch (IOException e) {
	    	caratulaValida = false;
	    	activarGuardarAlbum();
	    	// Cargamos la imagen de caratula no válida en el ImageView
	    	try {
	    		imagenCaratula.setImage(new Image(getClass().getResource("../img/caratula_no_valida.png").toURI().toString()));
			} catch (URISyntaxException e1) {}
	    }
		
	}
	
	public void activarGuardarAlbum() {
		
		if((!ean.getText().isEmpty()) && (!titulo.getText().isEmpty()) && (!artista.getText().isEmpty()) && (genero.getValue() != null)
				&& (!duracion.getText().isEmpty()) && (formato.getValue() != null) && (fechaPublicacion.getValue() != null) && (!precio.getText().isEmpty()) 
				&& (!discografica.getText().isEmpty()) && (!caratula.getText().isEmpty()) && caratulaValida == true) {
			
			guardarAlbum.setDisable(false);
		} else {
			guardarAlbum.setDisable(true);
		}
		
		
		
	}
	
	public void activarComprobarCaratula() {
		
		if(!caratula.getText().isEmpty()) {
			btnComprobarCaratula.setDisable(false);
		} else {
			btnComprobarCaratula.setDisable(true);
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		genero.getItems().removeAll(genero.getItems());
		formato.getItems().removeAll(genero.getItems());
		List<String> generos = null;
		List<String> formatos = null;
	    
		try {
			generos = Files.lines(rutaGeneros).collect(Collectors.toList());
			formatos = Files.lines(rutaFormatos).collect(Collectors.toList());
		} catch (IOException e) {}
		
	    genero.setItems(FXCollections.observableArrayList(generos));
	    formato.setItems(FXCollections.observableArrayList(formatos));
		
		guardarAlbum.setDisable(true);
		btnComprobarCaratula.setDisable(true);
		
		// Bloquear valores no numericos en el campo del ISBN-13
	    ean.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			ean.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
	    
	    // Bloquear valores no numericos en el campo duración
	    duracion.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			duracion.setText(newValue.replaceAll("[^\\d]", ""));
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
