package com.diego.FinDeCiclo.pojos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Usuario {
	
	@Id
	private String nombreUsuario;
	
	@ColumnTransformer(
			write=" MD5(?) "
	)
	private String contrasena;
	
	@Column
	private String email;
	
	@Column
	private LocalDate fechaNacimiento;
	
	@CreationTimestamp
	private Date fechaAlta;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidos;
	
	@Column
	private int rango;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Libro> libros;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Musica> musica;
	
	
	public Usuario() {
		super();
	}

	public Usuario(String nombreUsuario, String contrasena, String email, LocalDate fechaNacimiento, String nombre, String apellidos) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.apellidos = apellidos;
		libros = new ArrayList<Libro>();
		musica = new ArrayList<Musica>();
	}
	

	public Usuario(String nombreUsuario, String contrasena) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public List<Musica> getMusica() {
		return musica;
	}

	public void setMusica(List<Musica> musica) {
		this.musica = musica;
	}
	
	public void anhadirLibro(Libro l) {
		libros.add(l);
	}
	
	public void eliminarLibro(Libro l) {
		libros.remove(l);
	}
	
	public void anhadirMusica(Musica m) {
		musica.add(m);
	}
	
	public void eliminarMusica(Musica m) {
		musica.remove(m);
	}


}
