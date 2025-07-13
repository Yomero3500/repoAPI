//DemoApllication.java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("¡Conexión exitosa a la base de datos!");
            System.out.println("URL: " + dataSource.getConnection().getMetaData().getURL());
            System.out.println("Usuario: " + dataSource.getConnection().getMetaData().getUserName());

            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}