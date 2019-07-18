package br.mg.customertracker.database;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.core.controller.Kimera;

public class SqliteDatabase {

	private static SessionFactory factory = null;
	private static Kimera kim;

	// <property name="hbm2ddl.auto">update</property>

	private static void createFactory() {
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void close() {
		if (factory != null) {
			factory.close();
			factory = null;
		}

		if (kim != null) {
			kim = null;
		}
	}

	public static Session getSession() {
		checkFactory();
		return factory.openSession();

	}

	public static Kimera getKimera() {

		checkFactory();
		if (kim == null) {
			kim = new Kimera(factory);
		}

		return kim;
	}

	private static void checkFactory() {
		if (factory == null) {
			createFactory();
		}
	}

}
