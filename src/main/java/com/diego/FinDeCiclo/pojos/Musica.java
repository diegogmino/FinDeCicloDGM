package com.diego.FinDeCiclo.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Musica {

	@Id
	private Long isbn;
	
	@Column
	private String titulo;
	
	@Column
	private String artista;
	
	@Column
	private String genero;
	
	@Column
	private String formato;
	
	@Column
	private int duracion;
	
	@Column
	private String portada;
	
	@Column
	private Date fechaPublicacion;
	
	@Column
	private String discografica;

	public Musica(Long isbn, String titulo, String artista, String genero, String formato, int duracion, String portada,
			Date fechaPublicacion, String discografica) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.artista = artista;
		this.genero = genero;
		this.formato = formato;
		this.duracion = duracion;
		this.portada = portada;
		this.fechaPublicacion = fechaPublicacion;
		this.discografica = discografica;
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

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getDiscografica() {
		return discografica;
	}

	public void setDiscografica(String discografica) {
		this.discografica = discografica;
	}
	
	
	
	
}
