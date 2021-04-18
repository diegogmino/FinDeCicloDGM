package com.diego.FinDeCiclo.pojos;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Libro {
	
	@Id
	private Long isbn;
	
	@Column
	private String titulo;
	
	@Column
	private String autor;
	
	@Column
	private int paginas;
	
	@Column
	private int precio;
	
	@Column
	private String genero;
	
	@Column
	private boolean leido;
	
	@Column
	private LocalDate fechaLectura;
	
	@Column
	private String editorial;
	
	@Column
	private int numeroEdicion;
	
	@Column
	private String portada;
	
	public Libro() {
		super();
	}

	public Libro(Long isbn, String titulo, String autor, int paginas, int precio, String genero, boolean leido, LocalDate fechaLectura,
			String editorial, int numeroEdicion, String portada) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.precio = precio;
		this.genero = genero;
		this.leido = leido;
		this.fechaLectura = fechaLectura;
		this.editorial = editorial;
		this.numeroEdicion = numeroEdicion;
		this.portada = portada;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public LocalDate getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(LocalDate fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNumeroEdicion() {
		return numeroEdicion;
	}

	public void setNumeroEdicion(int numeroEdicion) {
		this.numeroEdicion = numeroEdicion;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}
	
	

}
