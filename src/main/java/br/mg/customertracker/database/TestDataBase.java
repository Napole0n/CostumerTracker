package br.mg.customertracker.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestDataBase {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emfact = Persistence.createEntityManagerFactory("persistence");
		EntityManager emManager = emfact.createEntityManager();
	}

}
