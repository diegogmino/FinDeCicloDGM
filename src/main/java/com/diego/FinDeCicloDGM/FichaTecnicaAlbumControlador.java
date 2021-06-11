package com.diego.FinDeCicloDGM;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Musica;
import com.diego.FinDeCicloDGM.dao.MusicaDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FichaTecnicaAlbumControlador extends ControladorConNavegabilidad implements Initializable {

    @FXML
    private Button borrar;

    @FXML
    private Button editar;

    @FXML
    private Button guardar;
    
    @FXML
    private Button eliminar;

    @FXML
    private TextField ean;

    @FXML
    private TextField titulo;

    @FXML
    private TextField artista;

    @FXML
    private TextField formato;

    @FXML
    private TextField precio;

    @FXML
    private TextField genero;

    @FXML
    private TextField duracion;

    @FXML
    private TextField fechaPublicacion;

    @FXML
    private TextField nombreAlbum;

    @FXML
    private ImageView caratula;

    @FXML
    private TextField discografica;
    
    private Musica album;


    public void cargarInformacion(Musica album) {
    	
    	this.album = album;
    	
    	if(Informacion.usuario.getRango() == 2) {
    		// Si el usuario tiene rango 2, lo que significa que es administador, tiene permisos para editar la información,
    		// cosa que no puede hacer un usuario normal
    		editar.setVisible(true);
    	} 

		eliminar.setVisible(true);
		
		ean.setText(album.getEan().toString());
		titulo.setText(album.getTitulo());
		artista.setText(album.getArtista());
		fechaPublicacion.setText(String.valueOf(album.getFechaPublicacion()));
		precio.setText("Precio: " + String.valueOf(album.getPrecio()) + " €");
		genero.setText(album.getGenero());
		formato.setText(album.getFormato());
		discografica.setText(album.getDiscografica());
		duracion.setText(String.valueOf(album.getDuracion()) + " minutos");
		nombreAlbum.setText("Ficha de: " + album.getTitulo());

		File imgCaratula = new File(album.getCaratula());
		caratula.setImage(new Image(imgCaratula.toURI().toString()));
    	
    }

    public void editarAlbum() {

    	editar.setDisable(true);
		guardar.setVisible(true);
		
		cambiarInformacionEditar();
		habilitarCampos();
		
		ean.setDisable(true);
		titulo.setEditable(true);
		artista.setEditable(true);
		formato.setEditable(true);
		precio.setEditable(true);
		genero.setEditable(true);
		duracion.setEditable(true);
		discografica.setEditable(true);
		fechaPublicacion.setEditable(true);
		nombreAlbum.setEditable(true);
    	
    }
    
    public void actualizarAlbum() {
    	
    	Alert popup = Popup.lanzarPopup("Editar", "Vas a editar la información de este álbum ¿Estás segur@?", 3);
    	cargarEstilosPopup(popup);
		Optional<ButtonType> resultado = popup.showAndWait();
		
		if (resultado.get() == ButtonType.OK) {
	    	
			album.setTitulo(titulo.getText());
			album.setEan(Long.parseLong(ean.getText()));
			album.setArtista(artista.getText());
			album.setFormato(formato.getText());
			album.setPrecio(Double.parseDouble(precio.getText()));
			album.setDuracion(Integer.parseInt(duracion.getText()));
			album.setDiscografica(discografica.getText());
			album.setFechaPublicacion(Date.valueOf(fechaPublicacion.getText()));
	    	
	    	MusicaDao.actualizarAlbum(album);
	    	
	    	volver();
			
		}

    }

    private void cambiarInformacionEditar() {
		
    	precio.setText(String.valueOf(album.getPrecio()));
    	duracion.setText(String.valueOf(album.getDuracion()));
    	nombreAlbum.setText("Editar ficha de: " + album.getTitulo());
		
	}

	public void eliminarAlbum() {

		if(Informacion.usuario.getRango() == 2) {
			// Si el rango del usuario es 2 se elimina el álbum de la base de datos
			Alert popup = Popup.lanzarPopup("Eliminar", "Vas a eliminar este álbum de la base de datos ¿Estás segur@?", 3);
			cargarEstilosPopup(popup);
			Optional<ButtonType> resultado = popup.showAndWait();
			
			if (resultado.get() == ButtonType.OK) {
				MusicaDao.eliminarAlbumAdmin(album);
		    	volver();
			}
			
		} else {
			// Si el rango del usuario no es 2, lo que significa que es 1, se elimina el álbum de su colección privada
			Alert popup = Popup.lanzarPopup("Eliminar", "Vas a eliminar este álbum de tu colección ¿Estás segur@?", 3);
			cargarEstilosPopup(popup);
			Optional<ButtonType> resultado = popup.showAndWait();
			
			if (resultado.get() == ButtonType.OK) {
				MusicaDao.eliminarAlbumUsuario(album, Informacion.usuario);
		    	volver();
			}

		}
    	
    }

    public void volver() {

    	try {
			this.layout.cargarColeccionMusica(ColeccionMusicaControlador.class.getResource("ColeccionMusica.fxml"));
		} catch (IOException e1) {}
    	
		limpiarCampos();
		deshabilitarCampos();
		editar.setDisable(false);
		guardar.setVisible(false);
		
		ean.setDisable(false);
		editar.setVisible(false);
		eliminar.setVisible(false);
    	
    }
    
    private void limpiarCampos() {
    	
		ean.clear();
		titulo.clear();
		artista.clear();
		formato.clear();
		precio.clear();
		genero.clear();
		duracion.clear();
		nombreAlbum.clear();
		fechaPublicacion.clear();
		caratula.setImage(null);
		
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
	
	public void deshabilitarCampos() {
		ean.setEditable(false);
		titulo.setEditable(false);
		artista.setEditable(false);
		formato.setEditable(false);
		precio.setEditable(false);
		genero.setEditable(false);
		duracion.setEditable(false);
		nombreAlbum.setEditable(false);
		fechaPublicacion.setEditable(false);
		discografica.setEditable(false);
	}
	
	public void habilitarCampos() {
		ean.setEditable(true);
		titulo.setEditable(true);
		artista.setEditable(true);
		formato.setEditable(true);
		precio.setEditable(true);
		genero.setEditable(true);
		duracion.setEditable(true);
		nombreAlbum.setEditable(true);
		fechaPublicacion.setEditable(true);
		discografica.setEditable(true);
	}
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		guardar.setVisible(false);
		editar.setVisible(false);
		eliminar.setVisible(false);
		deshabilitarCampos();
	}

}
