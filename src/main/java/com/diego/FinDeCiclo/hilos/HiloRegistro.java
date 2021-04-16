package com.diego.FinDeCiclo.hilos;

import java.sql.Date;
import java.time.LocalDate;

import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.LoginControlador;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HiloRegistro extends Thread {

	private TextField usuarioRegistro;
	private PasswordField contrasenaRegistro;
	private PasswordField repetirContrasena;
	private TextField nombre;
	private TextField apellidos;
	private DatePicker fechaNacimiento;
	private TextField email;
	private LoginControlador controlador;
	

	
	public HiloRegistro(TextField usuarioRegistro, PasswordField contrasenaRegistro, PasswordField repetirContrasena, TextField nombre, TextField apellidos,
			DatePicker fechaNacimiento, TextField email, LoginControlador controlador) {
		
		this.usuarioRegistro = usuarioRegistro;
		this.contrasenaRegistro = contrasenaRegistro;
		this.repetirContrasena = repetirContrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.controlador = controlador;
		
	}
	
	public void run() {
    	
    	if(contrasenaRegistro.getText().equals(repetirContrasena.getText())) {
    		
    		Usuario usuario = new Usuario(usuarioRegistro.getText(), contrasenaRegistro.getText(), email.getText(), fechaNacimiento.getValue(), nombre.getText(), apellidos.getText());
        	
        	if(UsuarioDao.insertarUsuario(usuario)) {
        		controlador.mostrarIniciarSesion(null);
        	} else {
        		System.out.println("Error al insertar el usuario");
        	}
    		
    	} else {
    		System.out.println("Las contraseñas no coinciden");
    	}
	}
	
}
