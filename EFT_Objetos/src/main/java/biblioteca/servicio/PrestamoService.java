package biblioteca.servicio;

import biblioteca.conexion.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;

public class PrestamoService {

    public synchronized void registrarPrestamo(
        int idEstudiante,
        int idLibro,
        int idUsuario) throws SQLException {
        String descontarStock = """
            UPDATE libros
            SET stock = stock - 1
            WHERE id_libro = ? AND stock > 0
            """;
        String insertarPrestamo = """
            INSERT INTO prestamos
                (id_estudiante, id_libro, id_usuario,
                 fecha_prestamo, fecha_devolucion, estado)
            VALUES (?, ?, ?, ?, NULL, ?)
            """;
        try (Connection con =
            DatabaseConnection.getInstance().getConnection()) {
            con.setAutoCommit(false);
            try {
                try (PreparedStatement ps =
                    con.prepareStatement(descontarStock)) {
                    ps.setInt(1, idLibro);
                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                            "Libro inexistente o sin stock disponible."
                        );
                    }
                }
                try (PreparedStatement ps =
                    con.prepareStatement(insertarPrestamo)) {
                    ps.setInt(1, idEstudiante);
                    ps.setInt(2, idLibro);
                    ps.setInt(3, idUsuario);
                    ps.setDate(4, Date.valueOf(LocalDate.now()));
                    ps.setString(5, "ACTIVO");
                    ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}