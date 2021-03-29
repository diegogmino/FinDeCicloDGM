package com.diego.FinDeCiclo.pojos;

import java.sql.Date;

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
	private String genero;
	
	@Column
	private boolean leido;
	
	@Column
	private Date fechaLectura;
	
	@Column
	private String editorial;
	
	@Column
	private int numeroEdicion;
	
	@Column
	private String sinopsis;
	
	@Column
	private String portada;

	public Libro(Long isbn, String titulo, String autor, int paginas, String genero, boolean leido, Date fechaLectura,
			String editorial, int numeroEdicion, String sinopsis, String portada) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.genero = genero;
		this.leido = leido;
		this.fechaLectura = fechaLectura;
		this.editorial = editorial;
		this.numeroEdicion = numeroEdicion;
		this.sinopsis = sinopsis;
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

	public Date getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(Date fechaLectura) {
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

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}
	
	

}
