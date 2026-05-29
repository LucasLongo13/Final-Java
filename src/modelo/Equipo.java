package modelo;

public class Equipo {

    private static int contador = 1;

    private int codigo;
    private String nombre;
    private String tipo;
    private String fechaAdquisicion;
    private String estado;

    public Equipo(String nombre, String tipo, String fechaAdquisicion, String estado) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaAdquisicion = fechaAdquisicion;
        this.estado = estado;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getFechaAdquisicion() { return fechaAdquisicion; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + tipo + ") Estado: " + estado;
    }
}
