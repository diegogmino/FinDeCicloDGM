package com.diego.FinDeCicloDGM.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.diego.FinDeCiclo.pojos.Usuario;

public class UsuarioDao {
	
	public UsuarioDao() {
		crearTablasSiNoExisten();
	}

	private void crearTablasSiNoExisten() {
		// TODO Auto-generated method stub
		
	}
	
	public Usuario existeUsuario(String usuario, String contrasena) {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			//Consulta para comprobar si un usuario ya está en la base de datos
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		return null;
		
	}
	
	public boolean insertarUsuario (Usuario usuario) {
		
		
		
		return true;
		
	}

}
