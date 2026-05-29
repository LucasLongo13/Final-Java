package modelo;

public class SocioMenor extends Socio {

    private String tutor;

    public SocioMenor(String nombre, String apellido, String telefono, String email, String tutor) {
        super(nombre, apellido, telefono, email, "MENOR");
        this.tutor = tutor;
    }

    public String getTutor() { return tutor; }

    @Override
    public double calcularPrecioActividad(double precioBase) {
        return precioBase;
    }
}
