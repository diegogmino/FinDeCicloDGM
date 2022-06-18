package com.diego.FinDeCicloDGM;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import com.diego.FinDeCiclo.hilos.HiloAnhadirAlbum;
import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Musica;
import com.diego.FinDeCicloDGM.dao.MusicaDao;
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

public class ColeccionMusicaControlador extends ControladorConNavegabilidad implements Initializable {

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
    private Label noDiscoLabel;

    @FXML
    private ImageView anhadirDiscoIconoLabel;
    
    @FXML
    private TextField valorTotal;
    
    @FXML
    private TextField totalDiscos;
    
    private TilePane tile;
    private Node contenido;
	
    public void añadirAlbum() {
    	
    	HiloAnhadirAlbum hilo = new HiloAnhadirAlbum();
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
	
	public void mostrarLibros() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	
        	try {
				this.layout.cargarColeccionLibros(ColeccionLibrosControlador.class.getResource("ColeccionLibros.fxml"));
			} catch (IOException e1) {}
        	
        	// Limpiamos la pantalla de álbumes
        	
        	refrescar.setDisable(false);
        	
        	tile.getChildren().clear();
    		root.setContent(contenido);
    		labelPane.setVisible(true);
    		refrescarIconoLabel.setVisible(true);
    		refrescarLabel.setVisible(true);
    		noDiscoLabel.setVisible(false);
    		anhadirDiscoIconoLabel.setVisible(false);
    		
    		valorTotal.setText("Valor total: 0 €");
			totalDiscos.setText("Nº de discos: 0");
        	
        	Informacion.stage.setTitle(Informacion.TITULO_APLICACION + " - " + Informacion.usuario.getNombre() + " " + Informacion.usuario.getApellidos() + " - Libros");
        });
        
	}
	
	public void mostrarOtrasColecciones() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	this.layout.mostrarComoPantallaActual("masColecciones");
        	
        	// Limpiamos la pantalla de álbumes
        	
        	refrescar.setDisable(false);
        	
        	tile.getChildren().clear();
    		root.setContent(contenido);
    		labelPane.setVisible(true);
    		refrescarIconoLabel.setVisible(true);
    		refrescarLabel.setVisible(true);
    		noDiscoLabel.setVisible(false);
    		anhadirDiscoIconoLabel.setVisible(false);
    		
    		valorTotal.setText("Valor total: 0 €");
			totalDiscos.setText("Nº de discos: 0");
        	
        	Informacion.stage.setTitle(Informacion.TITULO_APLICACION + " - " + Informacion.usuario.getNombre() + " " + Informacion.usuario.getApellidos() + " - Más colecciones");
        });
		
	}
	
	public void cerrarSesion() {
		
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1), barraNavegacion);
        translate.setToX(0);
        translate.play();
        
        translate.setOnFinished((e)->{
        	
        	this.layout.mostrarComoPantallaActual("login");
        	Informacion.stage.setTitle(Informacion.TITULO_APLICACION);
        	
        	refrescar.setDisable(false);
        	
        	tile.getChildren().clear();
    		root.setContent(contenido);
    		labelPane.setVisible(true);
    		refrescarIconoLabel.setVisible(true);
    		refrescarLabel.setVisible(true);
    		noDiscoLabel.setVisible(false);
    		anhadirDiscoIconoLabel.setVisible(false);
    		
    		valorTotal.setText("Valor total: 0 €");
			totalDiscos.setText("Nº de discos: 0");

        });

	}
	
	public void refrescar() {

		List<Musica> albumes = null;
		
		if(Informacion.usuario.getRango() == 2) {
			// Si el usuario tiene rango 2, lo que significa ser administrador, se cargan todos los álbumes de la base de datos
			albumes = MusicaDao.buscarTodos();
			
		} else {
			// Si el usuario tiene rango 1, lo que significa ser usuario normal, se carga la colección privada perteneciente a ese usuario
			albumes = MusicaDao.buscarAlbumesPorUsuario(Informacion.usuario);
			
		}
		
		if(albumes.isEmpty()) {
			
			tile.getChildren().clear();
			root.setContent(contenido);
			
			refrescar.setDisable(true);
			
			refrescarIconoLabel.setVisible(false);
			refrescarLabel.setVisible(false);
			
			noDiscoLabel.setVisible(true);
			anhadirDiscoIconoLabel.setVisible(true);
			
			valorTotal.setText("Valor total: 0 €");
			totalDiscos.setText("Nº de discos: 0");
			
		} else {
			
			tile.getChildren().clear();
			refrescar.setDisable(true);
			
			refrescarIconoLabel.setVisible(false);
			refrescarLabel.setVisible(false);
			noDiscoLabel.setVisible(false);
			anhadirDiscoIconoLabel.setVisible(false);
			
			double precioTotal = 0;
			int numeroDiscos = 0;
			
			for(Musica album : albumes) {
				Button albumBoton;
				albumBoton = createImageView(album);
	            tile.getChildren().addAll(albumBoton);
	            precioTotal = precioTotal + album.getPrecio();
	            numeroDiscos++;
			}
			
			DecimalFormat df = new DecimalFormat("#####.##");
			
			valorTotal.setText("Valor total: " + df.format(precioTotal) + " €");
			totalDiscos.setText("Nº de discos: " + numeroDiscos);
			
			root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	        root.setFitToWidth(true);
	        root.setContent(tile);
			
		}

	}
	
	private Button createImageView(Musica album) {
		
		File portada = new File(album.getCaratula());
		ImageView imageView = new ImageView(new Image(portada.toURI().toString()));
		
		imageView.setFitWidth(210);
		imageView.setFitHeight(210);
		
		Button boton = new Button();
		boton.getStyleClass().add("boton-portadas");
		boton.setGraphic(imageView);
		boton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				try {
					mostrarPantallaFichaTecnica(album);
				} catch (IOException e) {}
				
			}
			
		});
		
		return boton;
		
	}
	
	public void mostrarPantallaFichaTecnica(Musica album) throws IOException {
		this.layout.cargarFichaTecnicaMusica(FichaTecnicaAlbumControlador.class.getResource("FichaTecnicaAlbum.fxml"), album);
		refrescar.setDisable(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		noDiscoLabel.setVisible(false);
		anhadirDiscoIconoLabel.setVisible(false);
		
		valorTotal.setText("Valor total: 0 €");
		totalDiscos.setText("Nº de discos: 0");
		
		valorTotal.setEditable(false);
		totalDiscos.setEditable(false);
		
		tile = new TilePane();
		tile.setPadding(new Insets(20, 25, 25, 20));
        tile.setHgap(25);
        tile.setVgap(25);
        labelPane.setVisible(true);
        contenido = root.getContent();
		
	}

}

