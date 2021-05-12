package com.diego.FinDeCiclo.pojos;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Informacion {
	// Clase cuya finalidad es pasar información entre vistas
	public static Usuario usuario = new Usuario();
	public static Stage stage = null;
	
	// Libros
	
	public static Stage dialogoAnhadirLibro = null;
	public static Stage dialogoSeleccionarLibro = null;
	public static List<Libro> libros = new ArrayList<Libro>();
	public static Libro libroSeleccionado = new Libro();
	public static Button refrescar;
	public static Libro libroMostrarFichaTecnica = new Libro();
	
	// Música
	
	public static Musica albumSeleccionado = new Musica();
	public static List<Musica> albumes = new ArrayList<Musica>();
	public static Musica albumMostrarFichaTecnica = new Musica();
	public static Stage dialogoAnhadirAlbum = null;
	public static Stage dialogoSeleccionarAlbum = null;
	
	// Constantes
	
	public static final String TITULO_APLICACION = "Gestor de colecciones"; 


}
