package com.diego.FinDeCicloDGM;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FichaTecnicaAlbumControlador extends ControladorConNavegabilidad implements Initializable {
	
	@FXML
    private Button cargar;

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
    private Label texto;

    @FXML
    private ImageView iconoRefrescar;

    @FXML
    private TextField discografica;


    public void cargarInformacion() {
    	
    	if(Informacion.usuario.getRango() == 2) {
    		// Si el usuario tiene rango 2, lo que significa que es administador, tiene permisos para editar la información,
    		// cosa que no puede hacer un usuario normal
    		editar.setVisible(true);
    	} 

    	cargar.setDisable(true);
		eliminar.setVisible(true);
		
		mostrarCampos();
		texto.setVisible(false);
		iconoRefrescar.setVisible(false);
		
		ean.setText(Informacion.albumMostrarFichaTecnica.getEan().toString());
		titulo.setText(Informacion.albumMostrarFichaTecnica.getTitulo());
		artista.setText(Informacion.albumMostrarFichaTecnica.getArtista());
		fechaPublicacion.setText(String.valueOf(Informacion.albumMostrarFichaTecnica.getFechaPublicacion()));
		precio.setText("Precio: " + String.valueOf(Informacion.albumMostrarFichaTecnica.getPrecio()) + " €");
		genero.setText(Informacion.albumMostrarFichaTecnica.getGenero());
		formato.setText(Informacion.albumMostrarFichaTecnica.getFormato());
		discografica.setText(Informacion.albumMostrarFichaTecnica.getDiscografica());
		duracion.setText(String.valueOf(Informacion.albumMostrarFichaTecnica.getDuracion()) + " minutos");
		nombreAlbum.setText("Ficha de: " + Informacion.albumMostrarFichaTecnica.getTitulo());

		File imgCaratula = new File(Informacion.albumMostrarFichaTecnica.getCaratula());
		caratula.setImage(new Image(imgCaratula.toURI().toString()));
    	
    }

    public void editarAlbum() {

    	editar.setDisable(true);
		guardar.setVisible(true);
		
		cambiarInformacionEditar();
		
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
		Optional<ButtonType> resultado = popup.showAndWait();
		
		if (resultado.get() == ButtonType.OK) {
			
			Musica albumActualizar = Informacion.albumMostrarFichaTecnica;
	    	
	    	albumActualizar.setTitulo(titulo.getText());
	    	albumActualizar.setEan(Long.parseLong(ean.getText()));
	    	albumActualizar.setArtista(artista.getText());
	    	albumActualizar.setFormato(formato.getText());
	    	albumActualizar.setPrecio(Double.parseDouble(precio.getText()));
	    	albumActualizar.setDuracion(Integer.parseInt(duracion.getText()));
	    	albumActualizar.setDiscografica(discografica.getText());
	    	albumActualizar.setFechaPublicacion(Date.valueOf(fechaPublicacion.getText()));
	    	
	    	MusicaDao.actualizarAlbum(albumActualizar);
	    	
	    	volver();
			
		}

    }

    private void cambiarInformacionEditar() {
		
    	precio.setText(String.valueOf(Informacion.albumMostrarFichaTecnica.getPrecio()));
    	duracion.setText(String.valueOf(Informacion.albumMostrarFichaTecnica.getDuracion()));
    	nombreAlbum.setText("Editar ficha de: " + Informacion.albumMostrarFichaTecnica.getTitulo());
		
	}

	public void eliminarAlbum() {

		if(Informacion.usuario.getRango() == 2) {
			// Si el rango del usuario es 2 se elimina el álbum de la base de datos
			Alert popup = Popup.lanzarPopup("Eliminar", "Vas a eliminar este álbum de la base de datos ¿Estás segur@?", 3);
			Optional<ButtonType> resultado = popup.showAndWait();
			
			if (resultado.get() == ButtonType.OK) {
				MusicaDao.eliminarAlbumAdmin(Informacion.albumMostrarFichaTecnica);
		    	volver();
			}
			
		} else {
			// Si el rango del usuario no es 2, lo que significa que es 1, se elimina el álbum de su colección privada
			Alert popup = Popup.lanzarPopup("Eliminar", "Vas a eliminar este álbum de tu colección ¿Estás segur@?", 3);
			Optional<ButtonType> resultado = popup.showAndWait();
			
			if (resultado.get() == ButtonType.OK) {
				MusicaDao.eliminarAlbumUsuario(Informacion.albumMostrarFichaTecnica, Informacion.usuario);
		    	volver();
			}

		}
    	
    }

    public void volver() {

    	this.layout.mostrarComoPantallaActual("coleccionMusica");
		limpiarCampos();
		ocultarCampos();
		texto.setVisible(true);
		iconoRefrescar.setVisible(true);
		cargar.setDisable(false);
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
    
    private void ocultarCampos() {
    	
		ean.setVisible(false);
		titulo.setVisible(false);
		artista.setVisible(false);
		formato.setVisible(false);
		precio.setVisible(false);
		genero.setVisible(false);
		duracion.setVisible(false);
		discografica.setVisible(false);
		fechaPublicacion.setVisible(false);
		nombreAlbum.setVisible(false);
		caratula.setVisible(false);
		
	}
	
	private void mostrarCampos() {
		
		ean.setVisible(true);
		titulo.setVisible(true);
		artista.setVisible(true);
		formato.setVisible(true);
		precio.setVisible(true);
		genero.setVisible(true);
		duracion.setVisible(true);
		discografica.setVisible(true);
		fechaPublicacion.setVisible(true);
		nombreAlbum.setVisible(true);
		caratula.setVisible(true);
		
		ean.setEditable(false);
		titulo.setEditable(false);
		artista.setEditable(false);
		formato.setEditable(false);
		precio.setEditable(false);
		genero.setEditable(false);
		duracion.setEditable(false);
		discografica.setEditable(false);
		fechaPublicacion.setEditable(false);
		nombreAlbum.setEditable(false);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ocultarCampos();
		guardar.setVisible(false);
		editar.setVisible(false);
		eliminar.setVisible(false);
	}

}
