package biblioteca.dao;

import biblioteca.conexion.DatabaseConnection;
import biblioteca.modelo.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public void insertar(Libro libro) throws SQLException {
        String sql = """
            INSERT INTO libros
                (titulo, autor, isbn, id_categoria, stock)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection con =
            DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setInt(4, libro.getIdCategoria());
            ps.setInt(5, libro.getStock());
            ps.executeUpdate();
        }
    }

    public List<Libro> listar() throws SQLException {
        String sql = """
            SELECT id_libro, titulo, autor, isbn,
                   id_categoria, stock
            FROM libros
            ORDER BY titulo
            """;
        List<Libro> libros = new ArrayList<>();
        try (Connection con =
            DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                libros.add(new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("isbn"),
                    rs.getInt("id_categoria"),
                    rs.getInt("stock")
                ));
            }
        }
        return libros;
    }

    public Libro buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT id_libro, titulo, autor, isbn,
                   id_categoria, stock
            FROM libros
            WHERE id_libro = ?
            """;
        try (Connection con =
            DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {=
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getInt("id_categoria"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    public boolean actualizar(Libro libro) throws SQLException {
        String sql = """
            UPDATE libros
            SET titulo = ?, autor = ?, isbn = ?,
                id_categoria = ?, stock = ?
            WHERE id_libro = ?
            """;
        try (Connection con =
            DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setInt(4, libro.getIdCategoria());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdLibro());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql =
                "DELETE FROM libros WHERE id_libro = ?";
        try (Connection con =
            DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}