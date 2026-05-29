package modelo;

public class SocioJubilado extends Socio {

    private static final double DESCUENTO = 0.30;

    public SocioJubilado(String nombre, String apellido, String telefono, String email) {
        super(nombre, apellido, telefono, email, "JUBILADO");
    }

    @Override
    public double calcularPrecioActividad(double precioBase) {
        return precioBase * (1 - DESCUENTO);
    }
}
