package com.diego.FinDeCiclo.hilos;

import com.diego.FinDeCiclo.pojos.Informacion;
import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.LoginControlador;
import com.diego.FinDeCicloDGM.SelectorColeccionControlador;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class HiloIniciarSesion extends Thread {

	private TextField usuario;
	private TextField contrasena;
	private ProgressIndicator procesando;
	private LoginControlador controlador;
	private Button iniciarSesion;
	private Label mensajeError;
	
	public HiloIniciarSesion(TextField usuario, TextField contrasena, ProgressIndicator procesando, LoginControlador loginControlador, Button iniciarSesion, Label mensajeError) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.procesando = procesando;
		this.controlador = loginControlador;
		this.iniciarSesion = iniciarSesion;
		this.mensajeError = mensajeError;
		
	}

	@Override
	public void run() {

		Usuario usuarioEncontrado = UsuarioDao.existeUsuario(usuario.getText(), contrasena.getText());

        if(usuarioEncontrado.getNombreUsuario() == null) {
        	System.out.println("Usuario no encontrado");
        	procesando.setVisible(false);
        	usuario.clear();
    		contrasena.clear();
    		mensajeError.setVisible(true);
        	
        } else {
        	System.out.println("El usuario es: " + usuarioEncontrado.getNombre());
        	// El Platform.runLater evita el error "Not on FX application thread; currentThread = Thread-3" que ocurria al intentar cambiar de pantalla
        	Platform.runLater(()->{
        		Informacion.usuario = usuarioEncontrado;
        		controlador.mostrarSelectorColeccion();
    		});
        	
        	usuario.clear();
    		contrasena.clear();
        	procesando.setVisible(false);
        	mensajeError.setVisible(false);
        	
        }
		
	}
	
}
