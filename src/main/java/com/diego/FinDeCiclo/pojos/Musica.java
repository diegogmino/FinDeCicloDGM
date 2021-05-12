package com.diego.FinDeCiclo.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Musica {

	@Id
	private Long ean;
	
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
	private double precio;
	
	@Column
	private String discografica;
	
	public Musica() {}

	public Musica(Long ean, String titulo, String artista, String genero, String formato, int duracion, String portada,
			Date fechaPublicacion, double precio, String discografica) {
		super();
		this.ean = ean;
		this.titulo = titulo;
		this.artista = artista;
		this.genero = genero;
		this.formato = formato;
		this.duracion = duracion;
		this.portada = portada;
		this.fechaPublicacion = fechaPublicacion;
		this.precio = precio;
		this.discografica = discografica;
	}


	public Long getEan() {
		return ean;
	}

	public void setEan(Long ean) {
		this.ean = ean;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}
