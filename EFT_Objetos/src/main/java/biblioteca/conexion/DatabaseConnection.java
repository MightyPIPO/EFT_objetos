package biblioteca.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static volatile DatabaseConnection instance;

    private final String url;
    private final String usuario;
    private final String password;

    private DatabaseConnection() {
        this.url = System.getenv().getOrDefault(
            "DB_URL",
            "jdbc:mysql://localhost:3306/nombre_de_tu_base"
        );
        this.usuario = System.getenv().getOrDefault(
            "DB_USER", "root"
        );
        this.password = System.getenv().getOrDefault(
            "DB_PASSWORD", ""
        );
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                url, usuario, password
        );
    }
}