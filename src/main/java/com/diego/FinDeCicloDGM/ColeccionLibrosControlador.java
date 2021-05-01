package com.diego.FinDeCicloDGM;

import javafx.scene.control.ScrollPane;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.input.MouseButton;
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
			labelPane.setVisible(false);
			
			for(Libro libro : libros) {
				ImageView imageView;
	            imageView = createImageView(libro);
	            tile.getChildren().addAll(imageView);
			}
			
			root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	        root.setFitToWidth(true);
	        root.setContent(tile);
			
		}

	}
	
	private ImageView createImageView(Libro libro) {
		ImageView imageView = new ImageView(libro.getPortada());
		imageView.setFitWidth(220);
		imageView.setFitHeight(320);
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					if(mouseEvent.getClickCount() == 2){
						System.out.println("Titulo: " + libro.getTitulo());
					}
				}
			}
		});
		
		return imageView;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		noLibroLabel.setVisible(false);
		anhadirLibroIconoLabel.setVisible(false);
		
		tile = new TilePane();
		tile.setPadding(new Insets(25, 35, 25, 35));
        tile.setHgap(25);
        tile.setVgap(25);
        labelPane.setVisible(true);
		
	}

}
