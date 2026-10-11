package biblioteca.dao;

import biblioteca.conexion.DatabaseConnection;
import biblioteca.modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UsuarioDAO {

    public Usuario autenticar(String nombreUsuario, String password) throws SQLException {
        String sql = """
            SELECT id_usuario, nombre_usuario, password_hash, rol
            FROM usuarios WHERE nombre_usuario = ?
            """;
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, nombreUsuario);
            try (ResultSet r = p.executeQuery()) {
                if (!r.next()) {
                    return null;
                }
                String hash = r.getString("password_hash");
                if (hash == null || !BCrypt.checkpw(password, hash)) {
                    return null;
                }
                return new Usuario(
                    r.getInt("id_usuario"),
                    r.getString("nombre_usuario"),
                    null,
                    r.getString("rol")
                );
            }
        }
    }

    public void insertar(String nombreUsuario, String password, String rol) throws SQLException {
        String hash = BCrypt.hashpw(
            password, BCrypt.gensalt(12)
        );
        String sql = """
            INSERT INTO usuarios (nombre_usuario, password_hash, rol)
            VALUES (?, ?, ?)
            """;
        try (Connection c = DatabaseConnection.getInstance().getConnection();
            PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, nombreUsuario);
            p.setString(2, hash);
            p.setString(3, rol);
            p.executeUpdate();
        }
    }
}
