package br.com.treinamento.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaFactory {
	
	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("sistema-escolar");
	
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
	
	public static void closeEntityManagerFactory() {
		FACTORY.close();
	}

}
