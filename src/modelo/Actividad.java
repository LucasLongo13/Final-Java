package modelo;

public class Actividad {

    private static int contador = 1;

    private int codigo;
    private String nombre;
    private String categoria;
    private String horario;
    private int cupoMaximo;
    private int cupoActual;
    private double precio;

    public Actividad(String nombre, String categoria, String horario, int cupoMaximo, double precio) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.categoria = categoria;
        this.horario = horario;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = 0;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getHorario() { return horario; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getCupoActual() { return cupoActual; }
    public double getPrecio() { return precio; }

    public void setHorario(String horario) { this.horario = horario; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }
    public void setPrecio(double precio) { this.precio = precio; }

    public boolean hayCupo() {
        return cupoActual < cupoMaximo;
    }

    public void inscribir() {
        if (hayCupo()) cupoActual++;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + categoria + ") Cupo: " + cupoActual + "/" + cupoMaximo;
    }
}
