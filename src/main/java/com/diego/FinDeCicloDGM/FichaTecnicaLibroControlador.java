package com.diego.FinDeCicloDGM;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Informacion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FichaTecnicaLibroControlador extends ControladorConNavegabilidad implements Initializable {

	@FXML
    private TextField isbn;

    @FXML
    private TextField titulo;

    @FXML
    private TextField autor;

    @FXML
    private TextField paginas;

    @FXML
    private TextField precio;

    @FXML
    private TextField genero;

    @FXML
    private TextField fechaLectura;

    @FXML
    private TextField editorial;

    @FXML
    private TextField numeroEdicion;

    @FXML
    private TextField nombreLibro;

    @FXML
    private ImageView portada;
    
    @FXML
    private Label texto;

    @FXML
    private ImageView iconoRefrescar;
    
    @FXML
    private Button cargar;
	
	public void volver() {
		this.layout.mostrarComoPantallaActual("coleccionLibros");
		limpiarCampos();
		ocultarCampos();
		texto.setVisible(true);
		iconoRefrescar.setVisible(true);
		cargar.setDisable(false);
		
	}
	
	private void limpiarCampos() {
		isbn.clear();
		titulo.clear();
		autor.clear();
		paginas.clear();
		precio.clear();
		genero.clear();
		fechaLectura.clear();
		editorial.clear();
		numeroEdicion.clear();
		nombreLibro.clear();
		portada.setImage(null);
		
	}

	public void cargarInformacion() {
		
		cargar.setDisable(true);
		
		mostrarCampos();
		texto.setVisible(false);
		iconoRefrescar.setVisible(false);
		
		isbn.setText(Informacion.libroMostrarFichaTecnica.getIsbn().toString());
		titulo.setText(Informacion.libroMostrarFichaTecnica.getTitulo());
		autor.setText(Informacion.libroMostrarFichaTecnica.getAutor());
		paginas.setText(String.valueOf(Informacion.libroMostrarFichaTecnica.getPaginas()) + " páginas");
		precio.setText("Precio: " +String.valueOf(Informacion.libroMostrarFichaTecnica.getPrecio()) + " €");
		genero.setText(Informacion.libroMostrarFichaTecnica.getGenero());
		
		if(!Informacion.libroMostrarFichaTecnica.isLeido()) {
			fechaLectura.setVisible(false);
		} else {
			fechaLectura.setVisible(true);
			fechaLectura.setText(Informacion.libroMostrarFichaTecnica.getFechaLectura().toString());
		}
		
		editorial.setText("Editorial: " + Informacion.libroMostrarFichaTecnica.getEditorial());
		numeroEdicion.setText("Número edición: " + String.valueOf(Informacion.libroMostrarFichaTecnica.getNumeroEdicion()));
		nombreLibro.setText("Ficha de: " + Informacion.libroMostrarFichaTecnica.getTitulo());

		File imgPortada = new File(Informacion.libroMostrarFichaTecnica.getPortada());
		portada.setImage(new Image(imgPortada.toURI().toString()));
		
	}
	
	private void ocultarCampos() {
		isbn.setVisible(false);
		titulo.setVisible(false);
		autor.setVisible(false);
		paginas.setVisible(false);
		precio.setVisible(false);
		genero.setVisible(false);
		fechaLectura.setVisible(false);
		editorial.setVisible(false);
		numeroEdicion.setVisible(false);
		nombreLibro.setVisible(false);
		portada.setVisible(false);
	}
	
	private void mostrarCampos() {
		isbn.setVisible(true);
		titulo.setVisible(true);
		autor.setVisible(true);
		paginas.setVisible(true);
		precio.setVisible(true);
		genero.setVisible(true);
		fechaLectura.setVisible(true);
		editorial.setVisible(true);
		numeroEdicion.setVisible(true);
		nombreLibro.setVisible(true);
		portada.setVisible(true);
		
		isbn.setEditable(false);
		titulo.setEditable(false);
		autor.setEditable(false);
		paginas.setEditable(false);
		precio.setEditable(false);
		genero.setEditable(false);
		fechaLectura.setEditable(false);
		editorial.setEditable(false);
		numeroEdicion.setEditable(false);
		nombreLibro.setEditable(false);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ocultarCampos();
	}

}
