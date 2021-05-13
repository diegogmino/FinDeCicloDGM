package com.diego.FinDeCicloDGM.dao;

import java.net.ConnectException;
import java.util.List;

import javax.persistence.Query;

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
		
	}
	
	public static List<Usuario> buscarTodos() {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			Query query = session.createQuery("SELECT u FROM Usuario u");
			List<Usuario> usuarios = query.getResultList();
			
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		return usuarios;
		
	}
	
	public static Usuario existeUsuario(String usuario, String contrasena) {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			Query query = session.createQuery("SELECT u FROM Usuario u WHERE nombreUsuario LIKE :usu AND contrasena LIKE MD5(:pass)");
			List<Usuario> usuarios = query.setParameter("usu", usuario).setParameter("pass", contrasena).getResultList();
			
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		for(Usuario usuarioLista : usuarios) {
			System.out.println(usuarioLista.getNombre());
		}
		
		if(!usuarios.isEmpty()) {
			return usuarios.get(0);
		} else {
			Usuario usuarioVacio = new Usuario();
			return usuarioVacio;
		}
		
	}
	
	public static boolean existeNombreDeUsuario(String usuario) {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			Query query = session.createQuery("SELECT u FROM Usuario u WHERE nombreUsuario LIKE :usu");
			List<Usuario> usuarios = query.setParameter("usu", usuario).getResultList();
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		if(usuarios.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static boolean insertarUsuario(Usuario usuario) {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			session.save(usuario);
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		return true;
		
		
	}

}
