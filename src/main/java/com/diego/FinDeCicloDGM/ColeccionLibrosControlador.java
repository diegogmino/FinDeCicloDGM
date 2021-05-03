package com.diego.FinDeCicloDGM;

import javafx.scene.control.ScrollPane;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.diego.FinDeCiclo.hilos.HiloAnhadirLibro;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

public class ColeccionLibrosControlador extends ControladorConNavegabilidad implements Initializable {

    @FXML
    private VBox barraNavegacion;
    
    @FXML
    private ScrollPane root;
    
    @FXML
    private Button refrescar;
    
    @FXML
    private Pane labelPane;
    
    @FXML
    private Label refrescarLabel;

    @FXML
    private ImageView refrescarIconoLabel;
    
    @FXML
    private Label noLibroLabel;

    @FXML
    private ImageView anhadirLibroIconoLabel;
    
    private TilePane tile;
    private Node contenido;
	
    public void añadirLibro() {
    	
    	HiloAnhadirLibro hilo = new HiloAnhadirLibro();
    	hilo.start();
    	refrescar.setDisable(false);
    	
    }
    
	public void mostrarBarraNavegacion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(160);
        translate.play();
		
	}
	
	public void ocultarBarraNavegacion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
	}
	
	public void mostrarMusica() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("coleccionMusica");
        });
        
	}
	
	public void mostrarOtrasColecciones() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("masColecciones");
        });
		
	}
	
	public void cerrarSesion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	
        	this.layout.mostrarComoPantallaActual("login");
        	
        	refrescar.setDisable(false);
        	
        	tile.getChildren().clear();
    		root.setContent(contenido);
    		labelPane.setVisible(true);
    		refrescarIconoLabel.setVisible(true);
    		refrescarLabel.setVisible(true);

        });
        
		
		
		
	}
	
	public void refrescar() {

		List<Libro> libros = LibroDao.buscarLibrosPorUsuario(Informacion.usuario);
		
		if(libros.isEmpty()) {
			
			refrescar.setDisable(true);
			
			refrescarIconoLabel.setVisible(false);
			refrescarLabel.setVisible(false);
			
			noLibroLabel.setVisible(true);
			anhadirLibroIconoLabel.setVisible(true);
			
		} else {
			
			tile.getChildren().clear();
			refrescar.setDisable(true);
			
			refrescarIconoLabel.setVisible(false);
			refrescarLabel.setVisible(false);
			noLibroLabel.setVisible(false);
			anhadirLibroIconoLabel.setVisible(false);
			
			//labelPane.setVisible(false);
			
			for(Libro libro : libros) {
				Button libroBoton;
	            libroBoton = createImageView(libro);
	            tile.getChildren().addAll(libroBoton);
			}
			
			root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	        root.setFitToWidth(true);
	        root.setContent(tile);
			
		}

	}
	
	private Button createImageView(Libro libro) {
		
		File portada = new File(libro.getPortada());
		ImageView imageView = new ImageView(new Image(portada.toURI().toString()));
		
		imageView.setFitWidth(210);
		imageView.setFitHeight(310);
		
		Button boton = new Button();
		boton.getStyleClass().add("boton-libro");
		boton.setGraphic(imageView);
		boton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				Informacion.libroMostrarFichaTecnica = libro;
				mostrarPantallaFichaTecnica();
				
				System.out.println("Titulo: " + libro.getTitulo());
				
			}
			
		});
		
		
		return boton;
		
	}
	
	public void mostrarPantallaFichaTecnica() {
		this.layout.mostrarComoPantallaActual("fichaTecnicaLibro");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		noLibroLabel.setVisible(false);
		anhadirLibroIconoLabel.setVisible(false);
		
		tile = new TilePane();
		tile.setPadding(new Insets(20, 25, 25, 20));
        tile.setHgap(25);
        tile.setVgap(25);
        labelPane.setVisible(true);
        contenido = root.getContent();
		
	}

}
