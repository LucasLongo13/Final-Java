package modelo;

public abstract class Socio {
    protected static int contador = 1;

    private int codigo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String tipo;

    public Socio(String nombre, String apellido, String telefono, String email, String tipo) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getTipo() { return tipo; }

    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public abstract double calcularPrecioActividad(double precioBase);

    @Override
    public String toString() {
        return codigo + " - " + apellido + ", " + nombre + " (" + tipo + ")";
    }
}
