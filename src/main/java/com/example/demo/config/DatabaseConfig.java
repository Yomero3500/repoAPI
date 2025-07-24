// DatabaseConfig.java
package com.example.demo.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConfig {
    private static EntityManagerFactory emf;

    public static void initialize() {
        if (emf == null) {
            Map<String, String> properties = new HashMap<>();
            
            // Obtener variables de entorno
            String dbUrl = System.getenv("DATABASE_URL");
            String dbUser = System.getenv("DATABASE_USERNAME");
            String dbPassword = System.getenv("DATABASE_PASSWORD");

            // Si no hay variables de entorno, usar valores por defecto
            if (dbUrl == null) dbUrl = "jdbc:mysql://184.73.246.53/EquipLinkDBBB";
            if (dbUser == null) dbUser = "jbj9";
            if (dbPassword == null) dbPassword = "274318294";

            properties.put("jakarta.persistence.jdbc.url", dbUrl);
            properties.put("jakarta.persistence.jdbc.user", dbUser);
            properties.put("jakarta.persistence.jdbc.password", dbPassword);
            properties.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            
            emf = Persistence.createEntityManagerFactory("demo-pu", properties);
        }
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            initialize();
        }
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}