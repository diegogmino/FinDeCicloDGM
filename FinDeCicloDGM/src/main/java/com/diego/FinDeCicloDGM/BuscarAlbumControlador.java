package com.diego.FinDeCicloDGM;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Musica;
import com.diego.FinDeCicloDGM.dao.MusicaDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuscarAlbumControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
    private TextField ean;

    @FXML
    private Button buscar;

    @FXML
    private ImageView caratulaImageView;

    @FXML
    private RadioButton eanRB;

    @FXML
    private ToggleGroup tgBuscar;

    @FXML
    private Button agregarAColeccion;

    @FXML
    private Button aportarAlbum;

    @FXML
    private RadioButton tituloRB;

    @FXML
    private TextField titulo;

    @FXML
    private RadioButton artistaRB;

    @FXML
    private RadioButton generoRB;

    @FXML
    private TextField artista;

    @FXML
    private ComboBox<String> genero;
    
    @FXML
    private TextField info1;

    @FXML
    private TextField info2;

    @FXML
    private TextField info3;
    
    Path rutaGeneros = Path.of("ficherosInfo\\generosAlbumes.csv");
    Musica albumEncontrado = new Musica();
    List<Musica> albumesEncontrados;
    
    public void activarDesactivarRB() {
    	
    	if(eanRB.isSelected()) {
    		ean.setDisable(false);
    	} else {
    		ean.setDisable(true);
    	}
    	
    	if(tituloRB.isSelected()) {
    		titulo.setDisable(false);
    	} else {
    		titulo.setDisable(true);
    	}
    	
    	if(artistaRB.isSelected()) {
    		artista.setDisable(false);
    	} else {
    		artista.setDisable(true);
    	}
    	
    	if(generoRB.isSelected()) {
    		genero.setDisable(false);
    	} else {
    		genero.setDisable(true);
    	}
    	
    	ean.clear();
    	artista.clear();
    	titulo.clear();
    	genero.setValue(null);
    	try {
			caratulaImageView.setImage(new Image(getClass().getResource("../img/caratula_imagen.png").toURI().toString()));
		} catch (URISyntaxException e) {}
    	activarBuscar();
    	agregarAColeccion.setDisable(true);
    	
    }
    
    public void activarBuscar() {
    	if((!ean.getText().isEmpty()) || (!titulo.getText().isEmpty()) || (!artista.getText().isEmpty()) || (genero.getValue() != null)) {
    		buscar.setDisable(false);
    	} else {
    		buscar.setDisable(true);
    	}
    }
    
    public void agregarAColeccion() {
    	
    	List<Musica> albumesUsuario = MusicaDao.buscarAlbumesPorUsuario(Informacion.usuario);
    	
    	// Informacion.albumSeleccionado hace referencia a un album seleccionado a través del TableView del dialogo de selección
    	if(Informacion.albumSeleccionado.getEan() != null) {
    		
    		boolean encontrado = comprobarAlbumColeccion(albumesUsuario, Informacion.albumSeleccionado);
    		
    		if(!encontrado) {
    			
    			MusicaDao.anhadirAlbumUsuario(Informacion.albumSeleccionado, Informacion.usuario);
        		
        		// Lanzamos el popup de confirmación
        		
    			if(Informacion.usuario.getRango() == 1) {
					
					Alert popup = Popup.lanzarPopup("Álbum añadido a la colección", "El álbum «" + Informacion.albumSeleccionado.getTitulo() + "» se ha añadido a tu colección "
	        				+ "correctamente", 1);
					cargarEstilosPopup(popup);
	        		popup.showAndWait();
					
				} else {
					
					Alert popup = Popup.lanzarPopup("Álbum añadido a la base de datos", "El álbum «" + Informacion.albumSeleccionado.getTitulo() + "» se ha añadido base de datos "
	        				+ "correctamente", 1);
					cargarEstilosPopup(popup);
	        		popup.showAndWait();
					
				}
        		
        		Informacion.albumSeleccionado = new Musica();
        		Informacion.dialogoAnhadirAlbum.close();
        		
    		} else {
    			Alert popup = Popup.lanzarPopup("Álbum encontrado", "Ya tienes este álbum en tu colección", 2);
    			cargarEstilosPopup(popup);
    			popup.showAndWait();
    		}
    		
    			
    	} else {

    		// albumEncontrado es la variable donde se guarda el album resultante de la búsqueda por ean
    		boolean encontrado = comprobarAlbumColeccion(albumesUsuario, albumEncontrado);
    		
    		if(!encontrado) {
    			
    			MusicaDao.anhadirAlbumUsuario(albumEncontrado, Informacion.usuario);
    			// Lanzamos el popup de confirmación
    			if(Informacion.usuario.getRango() == 1) {
					
					Alert popup = Popup.lanzarPopup("Álbum añadido a la colección", "El álbum «" + albumEncontrado.getTitulo() + "» se ha añadido a tu colección "
	        				+ "correctamente", 1);
					cargarEstilosPopup(popup);
	        		popup.showAndWait();
					
				} else {
					
					Alert popup = Popup.lanzarPopup("Álbum añadido a la base de datos", "El álbum «" + albumEncontrado.getTitulo() + "» se ha añadido base de datos "
	        				+ "correctamente", 1);
					cargarEstilosPopup(popup);
	        		popup.showAndWait();
					
				}
        		
        		Informacion.dialogoAnhadirAlbum.close();
        		
    		} else {
    			Alert popup = Popup.lanzarPopup("Álbum encontrado", "Ya tienes este álbum en tu colección", 2);
    			cargarEstilosPopup(popup);
    			popup.showAndWait();
    		}

    	}
    	
    }
    
    
    private boolean comprobarAlbumColeccion(List<Musica> albumesUsuario, Musica albumAGuardar) {
		
		int encontrado = 0;
		
		for(Musica musica : albumesUsuario) {
    		if(musica.getEan().equals(albumAGuardar.getEan())) {
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
    	
    	if(!ean.getText().isEmpty()) {
    		
    		albumEncontrado = MusicaDao.buscarAlbumEAN(ean.getText());
    		
    		if(albumEncontrado.getEan() != null) {
    			File caratula = new File(albumEncontrado.getCaratula());

    			caratulaImageView.setImage(new Image(caratula.toURI().toString()));
    			agregarAColeccion.setDisable(false);
    			
    			// Cargamos en los TextField info1, info2 e info3 toda la información que no sea el EAN para mostrarsela al usuario
    			info1.setText("Título: " + albumEncontrado.getTitulo());
    			info2.setText("Artista: " + albumEncontrado.getArtista());
    			info3.setText("Género: " + albumEncontrado.getGenero());
    			aportarAlbum.setDisable(true);
    			
    		} else {
    			agregarAColeccion.setDisable(true);
    			aportarAlbum.setDisable(false);
    			info1.setText(null);
    			info2.setText(null);
    			info3.setText(null);
    		}

    	} else if(!titulo.getText().isEmpty()) {
    		
    		albumesEncontrados = MusicaDao.buscarAlbumTitulo(titulo.getText());
    		
    		if(!albumesEncontrados.isEmpty()) {
    			
    			lanzarDialogoSeleccion(albumesEncontrados);
    			
    			if(Informacion.albumSeleccionado.getEan() != null) {
    				
    				// Cargamos en los TextField info1, info2 e info3 toda la información que no sea el título para mostrarsela al usuario
        			info1.setText("EAN: " + Informacion.albumSeleccionado.getEan());
        			info2.setText("Artista: " + Informacion.albumSeleccionado.getArtista());
        			info3.setText("Género: " + Informacion.albumSeleccionado.getGenero());
        			aportarAlbum.setDisable(true);
    				
    			}
    			
    		} else {
    			agregarAColeccion.setDisable(true);
    			aportarAlbum.setDisable(false);
    			info1.setText(null);
    			info2.setText(null);
    			info3.setText(null);
    		}

    	} else if(!artista.getText().isEmpty()) {
    		
    		albumesEncontrados = MusicaDao.buscarAlbumGrupo(artista.getText());
    		
    		if(!albumesEncontrados.isEmpty()) {

    			lanzarDialogoSeleccion(albumesEncontrados);
    			
    			if(Informacion.albumSeleccionado.getEan() != null) {
    				
    				// Cargamos en los TextField info1, info2 e info3 toda la información que no sea el artista para mostrarsela al usuario
            		info1.setText("EAN: " + Informacion.albumSeleccionado.getEan());
            		info2.setText("Título: " + Informacion.albumSeleccionado.getTitulo());
            		info3.setText("Género: " + Informacion.albumSeleccionado.getGenero());
        			aportarAlbum.setDisable(true);
    				
    			}
    			
    		} else {
    			agregarAColeccion.setDisable(true);
    			aportarAlbum.setDisable(false);
    			info1.setText(null);
    			info2.setText(null);
    			info3.setText(null);
    		}
    		
    	} else if(genero.getValue() != null) {
    		
    		albumesEncontrados = MusicaDao.buscarAlbumGenero(genero.getValue());
    		
    		if(!albumesEncontrados.isEmpty()) {
    			
    			lanzarDialogoSeleccion(albumesEncontrados);
    			
    			if(Informacion.albumSeleccionado.getEan() != null) {
    				
    				// Cargamos en los TextField info1, info2 e info3 toda la información que no sea el género para mostrarsela al usuario
        			info1.setText("EAN: " + Informacion.albumSeleccionado.getEan());
        			info2.setText("Título: " + Informacion.albumSeleccionado.getTitulo());
        			info3.setText("Artista: " + Informacion.albumSeleccionado.getArtista());
        			aportarAlbum.setDisable(true);
    				
    			}
    			
    		} else {
    			agregarAColeccion.setDisable(true);
    			aportarAlbum.setDisable(false);
    			info1.setText(null);
    			info2.setText(null);
    			info3.setText(null);
    		}
    	}
    	
    }
    
    private void lanzarDialogoSeleccion(List<Musica> albumes) {
		
    	LayoutPane layoutPane = new LayoutPane();
    	
    	try {
			layoutPane.cargarAlbumesBuscadosTabla(ElegirAlbumControlador.class.getResource("ElegirAlbum.fxml"), albumes);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Informacion.dialogoAnhadirLibro);
        Scene dialogScene = new Scene(layoutPane, 700 , 400);
        dialog.setTitle("Elegir álbum");
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        
        Informacion.dialogoSeleccionarAlbum = dialog;
        
        dialog.showAndWait();
        
        if(Informacion.albumSeleccionado.getEan() != null) {
        	
        	File caratula = new File(Informacion.albumSeleccionado.getCaratula());
			caratulaImageView.setImage(new Image(caratula.toURI().toString()));
        	agregarAColeccion.setDisable(false);
        	aportarAlbum.setDisable(true);
        }

	}


	public void aportarAlbum() {
    	this.layout.mostrarComoPantallaActual("nuevoAlbum");
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
		
		// Bloquear valores no numericos en el campo de EAN
	    ean.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			ean.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	     });
	    
	    
	    List<String> generos = null;
	    
		try {
			generos = Files.lines(rutaGeneros).collect(Collectors.toList());
		} catch (IOException e) {}
		
	    genero.setItems(FXCollections.observableArrayList(generos));
		
		albumEncontrado = null;
		albumesEncontrados = new ArrayList<Musica>();
		
		ean.setDisable(true);
		titulo.setDisable(true);
		artista.setDisable(true);
		genero.setDisable(true);
		
		aportarAlbum.setDisable(true);
		buscar.setDisable(true);
		agregarAColeccion.setDisable(true);
		
		info1.setEditable(false);
		info2.setEditable(false);
		info3.setEditable(false);
		
	}

}
