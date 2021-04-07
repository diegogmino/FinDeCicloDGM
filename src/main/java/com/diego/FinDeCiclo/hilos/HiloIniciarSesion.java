package com.diego.FinDeCiclo.hilos;

import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.LoginControlador;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;

public class HiloIniciarSesion extends Thread {

	private String usuario;
	private String contrasena;
	private ProgressIndicator procesando;
	private LoginControlador controlador;
	
	public HiloIniciarSesion(String usuario, String contrasena, ProgressIndicator procesando, LoginControlador loginControlador) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.procesando = procesando;
		this.controlador = loginControlador;
		
	}

	@Override
	public void run() {

		Usuario usuarioEncontrado = UsuarioDao.existeUsuario(usuario, contrasena);

        if(usuarioEncontrado.getNombreUsuario() == null) {
        	System.out.println("Usuario no encontrado");
        } else {
        	System.out.println("El usuario es: " + usuarioEncontrado.getNombre());
        	// El Platform.runLater evita el error "Not on FX application thread; currentThread = Thread-3" que ocurria al intentar cambiar de pantalla
        	Platform.runLater(()->{
        		controlador.mostrarSelectorColeccion();
    		});
        	
        	procesando.setVisible(false);
        	
        }
		
	}
	
}
