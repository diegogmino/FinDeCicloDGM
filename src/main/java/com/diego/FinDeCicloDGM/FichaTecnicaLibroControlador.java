package com.diego.FinDeCicloDGM;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;

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
    private TextField editorial;

    @FXML
    private TextField nombreLibro;
    
    @FXML
    private TextField tapa;

    @FXML
    private ImageView portada;
    
    @FXML
    private Label texto;

    @FXML
    private ImageView iconoRefrescar;
    
    @FXML
    private Button cargar;
    
    @FXML
    private Button editar;
    
    @FXML
    private Button guardar;
    
    @FXML
    private Button eliminar;
	
	public void volver() {
		
		this.layout.mostrarComoPantallaActual("coleccionLibros");
		limpiarCampos();
		ocultarCampos();
		texto.setVisible(true);
		iconoRefrescar.setVisible(true);
		cargar.setDisable(false);
		editar.setDisable(false);
		guardar.setVisible(false);
		
		isbn.setDisable(false);
		editar.setVisible(false);
		eliminar.setVisible(false);
		
	}
	
	private void limpiarCampos() {
		isbn.clear();
		titulo.clear();
		autor.clear();
		paginas.clear();
		precio.clear();
		genero.clear();
		editorial.clear();
		nombreLibro.clear();
		tapa.clear();
		portada.setImage(null);
		
	}

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
		
		isbn.setText(Informacion.libroMostrarFichaTecnica.getIsbn().toString());
		titulo.setText(Informacion.libroMostrarFichaTecnica.getTitulo());
		autor.setText(Informacion.libroMostrarFichaTecnica.getAutor());
		paginas.setText(String.valueOf(Informacion.libroMostrarFichaTecnica.getPaginas()) + " páginas");
		precio.setText("Precio: " +String.valueOf(Informacion.libroMostrarFichaTecnica.getPrecio()) + " €");
		genero.setText(Informacion.libroMostrarFichaTecnica.getGenero());
		
		editorial.setText(Informacion.libroMostrarFichaTecnica.getEditorial());
		tapa.setText(String.valueOf(Informacion.libroMostrarFichaTecnica.getTapa()));
		nombreLibro.setText("Ficha de: " + Informacion.libroMostrarFichaTecnica.getTitulo());

		File imgPortada = new File(Informacion.libroMostrarFichaTecnica.getPortada());
		portada.setImage(new Image(imgPortada.toURI().toString()));
		
	}
	
	public void eliminarLibro() {
		
		if(Informacion.usuario.getRango() == 2) {
			// Si el rango del usuario es 2 se elimina el libro de la base de datos
			LibroDao.eliminarLibroAdmin(Informacion.libroMostrarFichaTecnica);
		} else {
			// Si el rango del usuario no es 2, lo que significa que es 1, se elimina el libro de su colección privada
			LibroDao.eliminarLibroUsuario(Informacion.libroMostrarFichaTecnica, Informacion.usuario);
		}
		
		volver();
	}
	
	private void cambiarInformacionEditar() {
		// Método que carga la información en los TextField sin las palabras concatenadas por código para editar más cómodamente
		paginas.setText(String.valueOf(Informacion.libroMostrarFichaTecnica.getPaginas()));
		precio.setText(String.valueOf(Informacion.libroMostrarFichaTecnica.getPrecio()));
		nombreLibro.setText("Editar ficha de: " + Informacion.libroMostrarFichaTecnica.getTitulo());
	}
	
	public void editarLibro() {
		// Método que habilita los campos para cambiar la información del libro seleccionado
		
		editar.setDisable(true);
		guardar.setVisible(true);
		
		cambiarInformacionEditar();
		
		isbn.setDisable(true);
		titulo.setEditable(true);
		autor.setEditable(true);
		paginas.setEditable(true);
		precio.setEditable(true);
		genero.setEditable(true);
		editorial.setEditable(true);
		tapa.setEditable(true);
		nombreLibro.setEditable(true);
		
	}
	
	public void actualizarLibro() {
		
		Libro libroActualizar = Informacion.libroMostrarFichaTecnica;
		
		libroActualizar.setTitulo(titulo.getText());
		libroActualizar.setIsbn(Long.parseLong(isbn.getText()));
		libroActualizar.setAutor(autor.getText());
		libroActualizar.setGenero(genero.getText());
		libroActualizar.setPaginas(Integer.parseInt(paginas.getText()));
		libroActualizar.setEditorial(editorial.getText());
		libroActualizar.setTapa(tapa.getText());
		libroActualizar.setPrecio(Double.parseDouble(precio.getText()));
		
		LibroDao.actualizarLibro(libroActualizar);
		
		volver();
		
	}
	
	private void ocultarCampos() {
		isbn.setVisible(false);
		titulo.setVisible(false);
		autor.setVisible(false);
		paginas.setVisible(false);
		precio.setVisible(false);
		genero.setVisible(false);
		editorial.setVisible(false);
		tapa.setVisible(false);
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
		editorial.setVisible(true);
		tapa.setVisible(true);
		nombreLibro.setVisible(true);
		portada.setVisible(true);
		
		isbn.setEditable(false);
		titulo.setEditable(false);
		autor.setEditable(false);
		paginas.setEditable(false);
		precio.setEditable(false);
		genero.setEditable(false);
		editorial.setEditable(false);
		tapa.setEditable(false);
		nombreLibro.setEditable(false);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ocultarCampos();
		guardar.setVisible(false);
		editar.setVisible(false);
		eliminar.setVisible(false);
	}

}
