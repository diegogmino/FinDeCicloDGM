package com.diego.FinDeCicloDGM;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCiclo.pojos.Musica;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;

public class LayoutPane extends BorderPane {
    
    private Map<String, Node> pantallasDeLaAplicacion;
    
    public LayoutPane() {
        this.pantallasDeLaAplicacion = new HashMap<>();
    }
    
    public void cargarPantalla(String nombreDeLaPantalla, URL urlArchivoFxml) throws IOException {
        FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
        Parent pantalla = cargadorPantallas.load();
        
        ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        pantallasDeLaAplicacion.put(nombreDeLaPantalla, pantalla);
    }
    
    public void mostrarComoPantallaActual(String nombreDeLaPantalla) {
        this.setCenter(pantallasDeLaAplicacion.get(nombreDeLaPantalla));
        
    }
    
    // Método que carga la ventana de ficha técnica de un libro con la información mostrada
    public void cargarFichaTecnicaLibro(URL urlArchivoFxml, Libro libro) throws IOException {
    	
    	FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
    	Parent pantalla = cargadorPantallas.load();
    	
    	ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        FichaTecnicaLibroControlador fichaTecnicaControlador = cargadorPantallas.getController();
		fichaTecnicaControlador.cargarInformacion(libro);
		fichaTecnicaControlador.deshabilitarCampos();
        
        this.setCenter(pantalla);
    	
    }
    
    // Método que carga la ventana de la colección de libros de un usuario con dicha colección visible
    public void cargarColeccionLibros(URL urlArchivoFxml) throws IOException {
    	
    	FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
    	Parent pantalla = cargadorPantallas.load();
    	
    	ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        ColeccionLibrosControlador coleccionLibros = cargadorPantallas.getController();
        coleccionLibros.refrescar();
        
        this.setCenter(pantalla);
    	
    }
    
    // Método que carga la ventana de ficha técnica de un álbum con la información mostrada
    public void cargarFichaTecnicaMusica(URL urlArchivoFxml, Musica musica) throws IOException {
    	
    	FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
    	Parent pantalla = cargadorPantallas.load();
    	
    	ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        FichaTecnicaAlbumControlador fichaTecnicaControlador = cargadorPantallas.getController();
		fichaTecnicaControlador.cargarInformacion(musica);
		fichaTecnicaControlador.deshabilitarCampos();
        
        this.setCenter(pantalla);
    	
    }
    
    // Método que carga la ventana de la colección de música de un usuario con dicha colección visible
    public void cargarColeccionMusica(URL urlArchivoFxml) throws IOException {
    	
    	FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
    	Parent pantalla = cargadorPantallas.load();
    	
    	ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        ColeccionMusicaControlador coleccionMusica = cargadorPantallas.getController();
        coleccionMusica.refrescar();
        
        this.setCenter(pantalla);
    	
    }
    
    // Método que muestra la ventana de selección de un libro buscado con el TableView cargado
    public void cargarLibrosBuscadosTabla(URL urlArchivoFxml, List<Libro> libros) throws IOException {
    	
    	FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
    	Parent pantalla = cargadorPantallas.load();
    	
    	ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        ElegirLibroControlador elegirLibro = cargadorPantallas.getController();
        elegirLibro.cargarLibros(libros);
        
        this.setCenter(pantalla);
    	
    }
    
    // Método que muestra la ventana de selección de un álbum buscado con el TableView cargado
    public void cargarAlbumesBuscadosTabla(URL urlArchivoFxml, List<Musica> albumes) throws IOException {
    	
    	FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
    	Parent pantalla = cargadorPantallas.load();
    	
    	ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setLayout(this);
        
        ElegirAlbumControlador elegirAlbum = cargadorPantallas.getController();
        elegirAlbum.cargarAlbumes(albumes);
        
        this.setCenter(pantalla);
    	
    }

}
