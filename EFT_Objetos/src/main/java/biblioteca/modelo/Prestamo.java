package biblioteca.modelo;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private int idEstudiante;
    private int idLibro;
    private int idUsuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;

    public Prestamo() {}

    public int getIdPrestamo() { return idPrestamo; }

    public void setIdPrestamo(int id) { this.idPrestamo = id; }

    public int getIdEstudiante() { return idEstudiante; }

    public void setIdEstudiante(int id) {
        this.idEstudiante = id;
    }

    public int getIdLibro() { return idLibro; }

    public void setIdLibro(int id) { this.idLibro = id; }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int id) { this.idUsuario = id; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }

    public void setFechaPrestamo(LocalDate fecha) {
        this.fechaPrestamo = fecha;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fecha) {
        this.fechaDevolucion = fecha;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}
