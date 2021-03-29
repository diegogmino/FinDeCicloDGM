package com.diego.FinDeCicloDGM;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.diego.FinDeCiclo.pojos.Libro;
import com.diego.FinDeCiclo.pojos.Musica;
import com.diego.FinDeCiclo.pojos.Usuario;

public class PrincipalPruebas {

	public static void main(String[] args) {

		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session session = sf.openSession();

		session.getTransaction().begin();

			Libro libro1 = new Libro((long) 978849062, "El hombre duplicado", "Jose Saramago", 250, "Ficcion", false, null, "Debolsillo", 10, null, "portada");
			Musica musica1 = new Musica((long) 46795213, "Death by Rock and Roll", "The Pretty Reckless", "Rock", "Vinilo", 60, "portada", null, "Discografica");
			
			Usuario usuario = new Usuario("DiAyden", "1234", "diego@diego.com", null, "Diego", "Garcia Miño");
			
			usuario.anhadirLibro(libro1);
			usuario.anhadirMusica(musica1);
			
			session.save(libro1);
			session.save(musica1);
			session.save(usuario);
		
		session.getTransaction().commit();

		session.close();
		sf.close();

	}

}
