package modelo;

public class SocioAdulto extends Socio {

    public SocioAdulto(String nombre, String apellido, String telefono, String email) {
        super(nombre, apellido, telefono, email, "ADULTO");
    }

    @Override
    public double calcularPrecioActividad(double precioBase) {
        return precioBase;
    }
}
