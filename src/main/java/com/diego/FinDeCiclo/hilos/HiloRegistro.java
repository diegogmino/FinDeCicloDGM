package com.diego.FinDeCiclo.hilos;

import java.time.LocalDate;

import org.apache.commons.validator.routines.EmailValidator;

import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.LoginControlador;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
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
	private ProgressIndicator procesando;

	
	public HiloRegistro(TextField usuarioRegistro, PasswordField contrasenaRegistro, PasswordField repetirContrasena, TextField nombre, TextField apellidos,
			DatePicker fechaNacimiento, TextField email, LoginControlador controlador, ProgressIndicator procesando) {
		
		this.usuarioRegistro = usuarioRegistro;
		this.contrasenaRegistro = contrasenaRegistro;
		this.repetirContrasena = repetirContrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.controlador = controlador;
		this.procesando = procesando;
		
	}
	
	public void run() {
		
    	if(contrasenaRegistro.getText().equals(repetirContrasena.getText())) {
    		
    		if(isValidEmail(email.getText())) {
    			
    			if(UsuarioDao.existeNombreDeUsuario(usuarioRegistro.getText())) {
    				System.out.println("El usuario introducido ya existe, introduce uno distinto");
    				procesando.setVisible(false);
    				
    			} else {
    				
    				Usuario usuario = new Usuario(usuarioRegistro.getText(), contrasenaRegistro.getText(), email.getText(), fechaNacimiento.getValue(), nombre.getText(), apellidos.getText());
                	
    				if(UsuarioDao.insertarUsuario(usuario)) {
                		controlador.mostrarIniciarSesion(null);
                	} else {
                		System.out.println("Error al insertar el usuario, vuelve a intentarlo más tarde");
                		procesando.setVisible(false);
                	}
                	
    			}
    			
    		} else {
    			System.out.println("Introduce un email válido");
    			procesando.setVisible(false);
    		}
    		
    	} else {
    		System.out.println("Las contraseñas no coinciden");
    		procesando.setVisible(false);
    	}
	}
	
	public static boolean isValidEmail(String email) {
	       // Creamos una instancia de EmailValidator
	       EmailValidator validator = EmailValidator.getInstance();

	       // Comprobamos si el email pasado por parámetro es válido
	       return validator.isValid(email);
	   }
	
}
