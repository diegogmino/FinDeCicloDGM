package com.diego.FinDeCiclo.hilos;

import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.scene.control.ProgressIndicator;

public class HiloIniciarSesion extends Thread {

	private String usuario;
	private String contrasena;
	private ProgressIndicator procesando;
	
	public HiloIniciarSesion(String usuario, String contrasena, ProgressIndicator procesando) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.procesando = procesando;
		
	}
	
	public void run() {
		
		Usuario usuarioEncontrado = UsuarioDao.existeUsuario(usuario, contrasena);
         
        
        if(usuarioEncontrado.getNombreUsuario() == null) {
        	System.out.println("Usuario no encontrado");
        } else {
        	System.out.println("El usuario es: " + usuarioEncontrado.getNombre());
        }
        
        procesando.setVisible(false);
		
	}
	
}
