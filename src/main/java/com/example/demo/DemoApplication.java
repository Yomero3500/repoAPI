package com.example.demo;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.controller.*;
import com.example.demo.repository.*;
import com.example.demo.routes.*;
import com.example.demo.service.*;
import io.javalin.Javalin;

public class DemoApplication {
    public static void main(String[] args) {
        // Configurar la base de datos
        DatabaseConfig.initialize();

        // Inicializar dependencias
        MarcaRepository marcaRepository = new MarcaRepository();
        MarcaService marcaService = new MarcaService(marcaRepository);
        MarcaController marcaController = new MarcaController(marcaService);

        ProveedorRepository proveedorRepository = new ProveedorRepository();
        ProveedorService proveedorService = new ProveedorService(proveedorRepository);
        ProveedorController proveedorController = new ProveedorController(proveedorService);

        TipoEquipoRepository tipoEquipoRepository = new TipoEquipoRepository();
        TipoEquipoService tipoEquipoService = new TipoEquipoService(tipoEquipoRepository);
        TipoEquipoController tipoEquipoController = new TipoEquipoController(tipoEquipoService);

        EquipoMedicoRepository equipoMedicoRepository = new EquipoMedicoRepository();
        EquipoMedicoService equipoMedicoService = new EquipoMedicoService(equipoMedicoRepository);
        EquipoMedicoController equipoMedicoController = new EquipoMedicoController(equipoMedicoService);

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        UsuarioController usuarioController = new UsuarioController(usuarioService);

        ResenaRepository resenaRepository = new ResenaRepository();
        ResenaService resenaService = new ResenaService(resenaRepository);
        ResenaController resenaController = new ResenaController(resenaService);

        TicketSoporteRepository ticketSoporteRepository = new TicketSoporteRepository();
        TicketSoporteService ticketSoporteService = new TicketSoporteService(ticketSoporteRepository);
        TicketSoporteController ticketSoporteController = new TicketSoporteController(ticketSoporteService);

        FavoritoRepository favoritoRepository = new FavoritoRepository();
        FavoritoService favoritoService = new FavoritoService(favoritoRepository);
        FavoritoController favoritoController = new FavoritoController(favoritoService);

        HistorialBusquedaRepository historialRepository = new HistorialBusquedaRepository();
        HistorialBusquedaService historialService = new HistorialBusquedaService(historialRepository);
        HistorialBusquedaController historialController = new HistorialBusquedaController(historialService);

        // Crear aplicación Javalin
        Javalin app = Javalin.create(config -> {
            config.plugins.enableDevLogging();

            // Habilita CORS para permitir solicitudes desde otros orígenes (como tu frontend)
            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    it.anyHost(); // Permite cualquier origen, ideal para desarrollo.
                });
            });
        });

        // Configurar rutas
        new MarcaRoutes(marcaController).configureRoutes(app);
        new ProveedorRoutes(proveedorController).configureRoutes(app);
        new TipoEquipoRoutes(tipoEquipoController).configureRoutes(app);
        new EquipoMedicoRoutes(equipoMedicoController).configureRoutes(app);
        new UsuarioRoutes(usuarioController).configureRoutes(app);
        new ResenaRoutes(resenaController).configureRoutes(app);
        new TicketSoporteRoutes(ticketSoporteController).configureRoutes(app);
        new FavoritoRoutes(favoritoController).configureRoutes(app);
        new HistorialBusquedaRoutes(historialController).configureRoutes(app);

        // Iniciar servidor
        app.start(8000);

        // Manejar cierre
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.stop();
            DatabaseConfig.close();
    }));
}
}