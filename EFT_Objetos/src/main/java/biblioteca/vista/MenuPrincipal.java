package biblioteca.vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Biblioteca");
        setSize(650, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel titulo = new JLabel(
            "Sistema de Gestión de Biblioteca",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));

        JButton libros = new JButton("Gestión de libros");
        JButton estudiantes = new JButton("Gestión de estudiantes");
        JButton prestamos = new JButton("Préstamos y devoluciones");
        JButton reportes = new JButton("Reportes");

        libros.addActionListener(e ->
            new LibrosFrame().setVisible(true)
        );
        estudiantes.addActionListener(e ->
            new EstudiantesFrame().setVisible(true)
        );
        prestamos.addActionListener(e ->
            new PrestamosFrame().setVisible(true)
        );
        reportes.addActionListener(e ->
            new ReportesFrame().setVisible(true)
        );

        JPanel botones = new JPanel(new GridLayout(4, 1, 10, 10));
        botones.add(libros);
        botones.add(estudiantes);
        botones.add(prestamos);
        botones.add(reportes);

        setLayout(new BorderLayout(15, 15));
        add(titulo, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new MenuPrincipal().setVisible(true)
        );
    }
}