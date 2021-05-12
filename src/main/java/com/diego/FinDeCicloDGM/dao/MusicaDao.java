package com.diego.FinDeCicloDGM.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.diego.FinDeCiclo.pojos.Musica;
import com.diego.FinDeCiclo.pojos.Usuario;

public class MusicaDao {
	
	public static boolean insertarAlbum(Musica album) {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			session.save(album);
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		return true;
		
	}
	
	public static List<Musica> buscarAlbumesPorUsuario(Usuario usuario) {
		
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();

			Query query = session.createQuery("SELECT u FROM Usuario u WHERE nombreUsuario LIKE :usu");
			List<Usuario> usuarios = query.setParameter("usu", usuario.getNombreUsuario()).getResultList();
			Usuario usuarioEncontrado = usuarios.get(0);
			List<Musica> albumes = usuarioEncontrado.getMusica();
		
		session.getTransaction().commit();

		session.close();
		sf.close();
		
		return albumes;
		
	}

	public static void anhadirAlbumUsuario(Musica album, Usuario usuario) {
	
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
	
			usuario.anhadirMusica(album);
			session.saveOrUpdate(usuario);
	
		session.getTransaction().commit();

		session.close();
		sf.close();
	
	}

	public static Musica buscarAlbumEAN(String ean) {
	
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();
		
			Query query = session.createQuery("SELECT m FROM Musica m WHERE ean = :cod ");
			List<Musica> albumes = query.setParameter("cod", Long.parseLong(ean)).getResultList();
	
		session.getTransaction().commit();

		session.close();
		sf.close();
	
		if(!albumes.isEmpty()) {
			return albumes.get(0);
		} else {
			Musica album = new Musica();
			return album;
		}
	}

	public static List<Musica> buscarAlbumTitulo(String titulo) {
	
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();

			Query query = session.createQuery("SELECT m FROM Musica m WHERE titulo = :tit ");
			List<Musica> albumes = query.setParameter("tit", titulo).getResultList();
	
		session.getTransaction().commit();

		session.close();
		sf.close();
	
		return albumes;
	}

	public static List<Musica> buscarAlbumGrupo(String grupo) {
	
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();

			Query query = session.createQuery("SELECT m FROM Musica m WHERE artista = :art ");
			List<Musica> albumes = query.setParameter("art", grupo).getResultList();
	
		session.getTransaction().commit();

		session.close();
		sf.close();
	
		return albumes;

	}

	public static List<Musica> buscarAlbumGenero(String genero) {
	
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();

			Query query = session.createQuery("SELECT m FROM Musica m WHERE genero = :gen ");
			List<Musica> albumes = query.setParameter("gen", genero).getResultList();
	
		session.getTransaction().commit();

		session.close();
		sf.close();
	
		return albumes;
	
	}

}
