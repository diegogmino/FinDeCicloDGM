package com.diego.FinDeCicloDGM;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    Musica albumEncontrado;
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
    	caratulaImageView.setImage(null);
    	activarBuscar();
    	
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
        		Informacion.albumSeleccionado = new Musica();
        		// Volver aquí cuando el dialogoAnhadirAlbum esté listo
        		Informacion.dialogoAnhadirLibro.close();
        		
    		} else {
    			System.out.println("Ya tienes este álbum en tu colección");
    		}
    		
    			
    	} else {

    		// albumEncontrado es la variable donde se guarda el album resultante de la búsqueda por isbn
    		boolean encontrado = comprobarAlbumColeccion(albumesUsuario, Informacion.albumSeleccionado);
    		
    		if(!encontrado) {
    			
    			MusicaDao.anhadirAlbumUsuario(albumEncontrado, Informacion.usuario);
        		Informacion.dialogoAnhadirLibro.close();
        		
    		} else {
    			System.out.println("Ya tienes este álbum en tu colección");
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
    			File portada = new File(albumEncontrado.getPortada());

    			caratulaImageView.setImage(new Image(portada.toURI().toString()));
    			agregarAColeccion.setDisable(false);
    			
    			// Cargamos en los TExtField info1, info2 e info3 toda la información que no sea el EAN para mostrarsela al usuario
    			info1.setText("Título: " + albumEncontrado.getTitulo());
    			info2.setText("Artista: " + albumEncontrado.getArtista());
    			info3.setText("Género: " + albumEncontrado.getGenero());
    			
    		} else {
    			aportarAlbum.setDisable(false);
    		}

    	} else if(!titulo.getText().isEmpty()) {
    		
    		albumesEncontrados = MusicaDao.buscarAlbumTitulo(titulo.getText());
    		
    		if(!albumesEncontrados.isEmpty()) {
    			Informacion.albumes = albumesEncontrados;
    			lanzarDialogoSeleccion();
    			
    			// Cargamos en los TExtField info1, info2 e info3 toda la información que no sea el título para mostrarsela al usuario
    			info1.setText("EAN: " + albumEncontrado.getEan());
    			info2.setText("Artista: " + albumEncontrado.getArtista());
    			info3.setText("Género: " + albumEncontrado.getGenero());
    			
    		} else {
    			aportarAlbum.setDisable(false);
    			// mostrar imagen de libro no encontrado
    		}

    	} else if(!artista.getText().isEmpty()) {
    		
    		albumesEncontrados = MusicaDao.buscarAlbumGrupo(artista.getText());
    		
    		if(!albumesEncontrados.isEmpty()) {
    			Informacion.albumes = albumesEncontrados;
    			lanzarDialogoSeleccion();
    			
    			// Cargamos en los TExtField info1, info2 e info3 toda la información que no sea el artista para mostrarsela al usuario
    			info1.setText("EAN: " + albumEncontrado.getEan());
    			info2.setText("Título: " + albumEncontrado.getTitulo());
    			info3.setText("Género: " + albumEncontrado.getGenero());
    			
    		} else {
    			aportarAlbum.setDisable(false);
    			// mostrar imagen de libro no encontrado
    		}
    		
    	} else if(genero.getValue() != null) {
    		
    		albumesEncontrados = MusicaDao.buscarAlbumGenero(genero.getValue());
    		
    		if(!albumesEncontrados.isEmpty()) {
    			Informacion.albumes = albumesEncontrados;
    			lanzarDialogoSeleccion();
    			
    			// Cargamos en los TExtField info1, info2 e info3 toda la información que no sea el género para mostrarsela al usuario
    			info1.setText("EAN: " + albumEncontrado.getEan());
    			info2.setText("Título: " + albumEncontrado.getTitulo());
    			info3.setText("Artista: " + albumEncontrado.getArtista());
    			
    		} else {
    			aportarAlbum.setDisable(false);
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
        
        Informacion.dialogoSeleccionarAlbum = dialog;
        
        dialog.showAndWait();
        
        if(Informacion.libroSeleccionado.getIsbn() != null) {
        	
        	File portada = new File(Informacion.libroSeleccionado.getPortada());
			caratulaImageView.setImage(new Image(portada.toURI().toString()));
        	agregarAColeccion.setDisable(false);
        	aportarAlbum.setDisable(true);
        }

	}


	public void aportarAlbum() {
    	this.layout.mostrarComoPantallaActual("nuevoAlbum");
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Bloquear valores no numericos en el campo del ISBN-13
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
		
	}

}
