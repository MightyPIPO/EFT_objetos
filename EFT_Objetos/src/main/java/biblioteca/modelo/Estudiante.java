package biblioteca.modelo;

public class Estudiante {
    private int idEstudiante;
    private String documento;
    private String nombre;
    private String apellido;
    private String correo;

    public Estudiante() {}

    public Estudiante(int idEstudiante, String documento,
        String nombre, String apellido, String correo) {
        this.idEstudiante = idEstudiante;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public int getIdEstudiante() { return idEstudiante; }

    public void setIdEstudiante(int id) { this.idEstudiante = id; }

    public String getDocumento() { return documento; }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }
}