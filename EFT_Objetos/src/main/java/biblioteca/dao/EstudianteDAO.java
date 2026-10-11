package biblioteca.dao;

import biblioteca.conexion.DatabaseConnection;
import biblioteca.modelo.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public void insertar(Estudiante e) throws SQLException {
        String sql = """
            INSERT INTO estudiantes
                (documento, nombre, apellido, correo)
            VALUES (?, ?, ?, ?)
            """;
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, e.getDocumento());
            p.setString(2, e.getNombre());
            p.setString(3, e.getApellido());
            p.setString(4, e.getCorreo());
            p.executeUpdate();
        }
    }

    public List<Estudiante> listar() throws SQLException {
        String sql = """
            SELECT id_estudiante, documento, nombre, apellido, correo
            FROM estudiantes ORDER BY apellido, nombre
            """;
        List<Estudiante> lista = new ArrayList<>();
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet r = p.executeQuery()) {
            while (r.next()) {
                lista.add(mapear(r));
            }
        }
        return lista;
    }

    public Estudiante buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT id_estudiante, documento, nombre, apellido, correo
            FROM estudiantes WHERE id_estudiante = ?
            """;
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                return r.next() ? mapear(r) : null;
            }
        }
    }

    public boolean actualizar(Estudiante e) throws SQLException {
        String sql = """
            UPDATE estudiantes
            SET documento=?, nombre=?, apellido=?, correo=?
            WHERE id_estudiante=?
            """;
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, e.getDocumento());
            p.setString(2, e.getNombre());
            p.setString(3, e.getApellido());
            p.setString(4, e.getCorreo());
            p.setInt(5, e.getIdEstudiante());
            return p.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql =
            "DELETE FROM estudiantes WHERE id_estudiante=?";
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, id);
            return p.executeUpdate() > 0;
        }
    }

    private Estudiante mapear(ResultSet r) throws SQLException {
        return new Estudiante(
            r.getInt("id_estudiante"),
            r.getString("documento"),
            r.getString("nombre"),
            r.getString("apellido"),
            r.getString("correo")
        );
    }
}
