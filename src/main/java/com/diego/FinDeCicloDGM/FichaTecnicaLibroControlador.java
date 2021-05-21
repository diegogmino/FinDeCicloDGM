package com.diego.FinDeCicloDGM;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCicloDGM.dao.LibroDao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Button editar;
    
    @FXML
    private Button guardar;
    
    @FXML
    private Button eliminar;
    
    // Variable en la que se guarda la información del libro seleccionado y que se está mostrando en la ficha técnica
    private Libro libro;
	
	public void volver() {
		
		try {
			this.layout.cargarColeccionLibros(ColeccionLibrosControlador.class.getResource("ColeccionLibros.fxml"));
		} catch (IOException e) {}
		
		limpiarCampos();
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

	public void cargarInformacion(Libro libro) {
		
		this.libro = libro;
		
		if(Informacion.usuario.getRango() == 2) {
    		// Si el usuario tiene rango 2, lo que significa que es administador, tiene permisos para editar la información,
    		// cosa que no puede hacer un usuario normal
    		editar.setVisible(true);
    	} 
		
		eliminar.setVisible(true);
		
		isbn.setText(libro.getIsbn().toString());
		titulo.setText(libro.getTitulo());
		autor.setText(libro.getAutor());
		paginas.setText(String.valueOf(libro.getPaginas()) + " páginas");
		precio.setText("Precio: " +String.valueOf(libro.getPrecio()) + " €");
		genero.setText(libro.getGenero());
		
		editorial.setText(libro.getEditorial());
		tapa.setText(String.valueOf(libro.getTapa()));
		nombreLibro.setText("Ficha de: " + libro.getTitulo());

		File imgPortada = new File(libro.getPortada());
		portada.setImage(new Image(imgPortada.toURI().toString()));
		
	}
	
	public void eliminarLibro() {
		
		if(Informacion.usuario.getRango() == 2) {
			// Si el rango del usuario es 2 se elimina el libro de la base de datos
			Alert popup = Popup.lanzarPopup("Eliminar", "Vas a eliminar este libro de la base de datos ¿Estás segur@?", 3);
			Optional<ButtonType> resultado = popup.showAndWait();
			
			if (resultado.get() == ButtonType.OK) {
				LibroDao.eliminarLibroAdmin(this.libro);
				volver();
			}
			
			
		} else {
			// Si el rango del usuario no es 2, lo que significa que es 1, se elimina el libro de su colección privada
			Alert popup = Popup.lanzarPopup("Eliminar", "Vas a eliminar este libro de la tu colección ¿Estás segur@?", 3);
			Optional<ButtonType> resultado = popup.showAndWait();
			
			if (resultado.get() == ButtonType.OK) {
				LibroDao.eliminarLibroUsuario(this.libro, Informacion.usuario);
				volver();
			}
			
		}
		
		
	}
	
	private void cambiarInformacionEditar() {
		// Método que carga la información en los TextField sin las palabras concatenadas por código para editar más cómodamente
		paginas.setText(String.valueOf(libro.getPaginas()));
		precio.setText(String.valueOf(libro.getPrecio()));
		nombreLibro.setText("Editar ficha de: " + libro.getTitulo());
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
		
		Alert popup = Popup.lanzarPopup("Editar", "Vas a editar la información de este libro ¿Estás segur@?", 3);
		Optional<ButtonType> resultado = popup.showAndWait();
		
		if (resultado.get() == ButtonType.OK) {
			
			libro.setTitulo(titulo.getText());
			libro.setIsbn(Long.parseLong(isbn.getText()));
			libro.setAutor(autor.getText());
			libro.setGenero(genero.getText());
			libro.setPaginas(Integer.parseInt(paginas.getText()));
			libro.setEditorial(editorial.getText());
			libro.setTapa(tapa.getText());
			libro.setPrecio(Double.parseDouble(precio.getText()));
			
			LibroDao.actualizarLibro(libro);
			
			volver();
			
		}

	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		guardar.setVisible(false);
		editar.setVisible(false);
		eliminar.setVisible(false);
	}

}
