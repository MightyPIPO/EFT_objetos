package biblioteca.controlador;

import biblioteca.servicio.PrestamoService;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrestamoController {

    private final PrestamoService servicio =
        new PrestamoService();

    private final ExecutorService executor =
        Executors.newFixedThreadPool(2);

    public void registrarPrestamo(
        int idEstudiante,
        int idLibro,
        int idUsuario,
        Runnable alFinalizar) {
        executor.submit(() -> {
            Exception error = null;
            try {
                servicio.registrarPrestamo(
                    idEstudiante, idLibro, idUsuario
                );
            } catch (Exception ex) {
                error = ex;
            }
            final Exception resultado = error;
            SwingUtilities.invokeLater(() -> {
                if (resultado == null) {
                    JOptionPane.showMessageDialog(
                        null, "Préstamo registrado correctamente."
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        null, "No se pudo registrar el préstamo: " + resultado.getMessage(), "Error", JOptionPane.ERROR_MESSAGE
                    );
                }
                if (alFinalizar != null) {
                    alFinalizar.run();
                }
            });
        });
    }

    public void cerrar() {
        executor.shutdown();
    }
}
