package com.diego.FinDeCiclo.pojos;

import javafx.stage.Stage;

public class Informacion {
	// Clase cuya finalidad es pasar información entre vistas
	public static Usuario usuario = new Usuario();
	public static Stage stage = null;
	
	// Libros
	
	public static Stage dialogoAnhadirLibro = null;
	public static Stage dialogoSeleccionarLibro = null;
	public static Libro libroSeleccionado = new Libro();
	
	// Música
	
	public static Musica albumSeleccionado = new Musica();
	public static Stage dialogoAnhadirAlbum = null;
	public static Stage dialogoSeleccionarAlbum = null;
	
	// Constantes
	
	public static final String TITULO_APLICACION = "Mis Colecciones"; 


}
