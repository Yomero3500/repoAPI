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
        // Usamos solo el nombre de la unidad de persistencia
        emf = Persistence.createEntityManagerFactory("demo-pu");
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}