package com.diego.FinDeCiclo.hilos;

import org.apache.commons.validator.routines.EmailValidator;
import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.LoginControlador;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
	private Label mensajeContrasena;
	private Label mensajeEmail;
	private Label mensajeErrorInsertar;
	private Label mensajeNombreUsuario;
	private Label mensajeObligatorio;
	

	public HiloRegistro(TextField usuarioRegistro, PasswordField contrasenaRegistro, PasswordField repetirContrasena, TextField nombre, TextField apellidos,
			DatePicker fechaNacimiento, TextField email, LoginControlador controlador, ProgressIndicator procesando, Label mensajeContrasena, Label mensajeEmail, 
			Label mensajeErrorInsertar, Label mensajeNombreUsuario, Label mensajeObligatorio) {
		
		this.usuarioRegistro = usuarioRegistro;
		this.contrasenaRegistro = contrasenaRegistro;
		this.repetirContrasena = repetirContrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.controlador = controlador;
		this.procesando = procesando;
		this.mensajeContrasena = mensajeContrasena;
		this.mensajeEmail = mensajeEmail;
		this.mensajeErrorInsertar = mensajeErrorInsertar;
		this.mensajeNombreUsuario = mensajeNombreUsuario;
		this.mensajeObligatorio = mensajeObligatorio;
		
	}
	
	public void run() {
		
    	if(contrasenaRegistro.getText().equals(repetirContrasena.getText())) {
    		
    		if(esEmailValido(email.getText())) {
    			
    			mensajeEmail.setVisible(false);
    			
    			if(UsuarioDao.existeNombreDeUsuario(usuarioRegistro.getText())) {
    				
    				mensajeObligatorio.setVisible(false);
    				mensajeErrorInsertar.setVisible(false);
    				mensajeContrasena.setVisible(false);
    				mensajeEmail.setVisible(false);
    				mensajeNombreUsuario.setVisible(true);
    				
    				//System.out.println("El usuario introducido ya existe, introduce uno distinto");
    				procesando.setVisible(false);
    				
    			} else {
    				
    				Usuario usuario = new Usuario(usuarioRegistro.getText(), contrasenaRegistro.getText(), email.getText(), fechaNacimiento.getValue(), nombre.getText(), apellidos.getText(), 1);
                	
    				if(UsuarioDao.insertarUsuario(usuario)) {
                		controlador.mostrarIniciarSesion();
                	} else {
                		mensajeObligatorio.setVisible(false);
                		mensajeNombreUsuario.setVisible(false);
                		mensajeContrasena.setVisible(false);
                		mensajeEmail.setVisible(false);
                		mensajeErrorInsertar.setVisible(true);
                		//System.out.println("Error al insertar el usuario, vuelve a intentarlo más tarde");
                		procesando.setVisible(false);
                	}
                	
    			}
    			
    		} else {
    			mensajeObligatorio.setVisible(false);
    			mensajeNombreUsuario.setVisible(false);
    			mensajeContrasena.setVisible(false);
    			mensajeErrorInsertar.setVisible(false);
    			mensajeEmail.setVisible(true);
    			
    			//System.out.println("Introduce un email válido");
    			procesando.setVisible(false);
    		}
    		
    	} else {
    		mensajeObligatorio.setVisible(false);
    		mensajeEmail.setVisible(false);
    		mensajeNombreUsuario.setVisible(false);
    		mensajeErrorInsertar.setVisible(false);
    		mensajeContrasena.setVisible(true);
    		
    		//System.out.println("Las contraseñas no coinciden");
    		
    		procesando.setVisible(false);
    	}
	}
	
	public static boolean esEmailValido(String email) {
	       // Creamos una instancia de EmailValidator
	       EmailValidator validator = EmailValidator.getInstance();

	       // Comprobamos si el email pasado por parámetro es válido
	       return validator.isValid(email);
	   }
	
}
