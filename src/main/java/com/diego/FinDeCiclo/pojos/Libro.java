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
	private double precio;
	
	@Column
	private String genero;
	
	@Column
	private String tapa;
	
	@Column
	private String editorial;
	
	@Column
	private String portada;
	
	public Libro() {
	}

	public Libro(Long isbn, String titulo, String autor, int paginas, double precio, String genero, String tapa,
			String editorial, String portada) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.precio = precio;
		this.genero = genero;
		this.tapa = tapa;
		this.editorial = editorial;
		this.portada = portada;
	}

	public Libro(Long isbn, String titulo, String autor, String genero) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTapa() {
		return tapa;
	}

	public void setTapa(String tapa) {
		this.tapa = tapa;
	}
	
	
	
	

}
