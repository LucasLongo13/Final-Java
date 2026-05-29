package sistema;

import modelo.*;

public class SistemaGimnasio {

    private Socio[] socios = new Socio[500];
    private Actividad[] actividades = new Actividad[500];
    private Equipo[] equipos = new Equipo[500];

    private int cantSocios = 0;
    private int cantActividades = 0;
    private int cantEquipos = 0;

    // ============================================================
    //                      SOCIOS
    // ============================================================

    public void altaSocio(Socio s) {
        if (cantSocios < socios.length) {
            socios[cantSocios++] = s;
            System.out.println("Socio registrado correctamente.");
        } else {
            System.out.println("No hay más espacio para socios.");
        }
    }

    public void listarSocios() {
        if (cantSocios == 0) {
            System.out.println("No hay socios registrados.");
            return;
        }

        for (int i = 0; i < cantSocios; i++) {
            System.out.println(socios[i]);
        }
    }

    public void listarSociosPorTipo(String tipo) {
        boolean encontrado = false;

        for (int i = 0; i < cantSocios; i++) {
            if (socios[i].getTipo().equalsIgnoreCase(tipo)) {
                System.out.println(socios[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron socios del tipo: " + tipo);
        }
    }

    public Socio buscarSocioPorCodigo(int codigo) {
        for (int i = 0; i < cantSocios; i++) {
            if (socios[i].getCodigo() == codigo) {
                return socios[i];
            }
        }
        return null;
    }

    public Socio buscarSocioPorApellido(String apellido) {
        for (int i = 0; i < cantSocios; i++) {
            if (socios[i].getApellido().equalsIgnoreCase(apellido)) {
                return socios[i];
            }
        }
        return null;
    }

    public void modificarContacto(Socio s, String nuevoTel, String nuevoEmail) {
        s.setTelefono(nuevoTel);
        s.setEmail(nuevoEmail);
        System.out.println("Datos de contacto actualizados.");
    }

    public void modificarTipoSocio(Socio s, String nuevoTipo) {
        s.setTipo(nuevoTipo);
        System.out.println("Tipo de socio actualizado.");
    }

    public void registrarPago(Socio s) {
        System.out.println("Pago registrado para el socio: " + s.getNombre());
    }

    public void inscribirSocioEnActividad(Socio s, Actividad a) {
        if (!a.hayCupo()) {
            System.out.println("La actividad está completa.");
            return;
        }

        double precioFinal = s.calcularPrecioActividad(a.getPrecio());
        System.out.println("Precio final para " + s.getNombre() + ": $" + precioFinal);

        a.inscribir();
        System.out.println("Inscripción realizada con éxito.");
    }

    // ============================================================
    //                      ACTIVIDADES
    // ============================================================

    public void altaActividad(Actividad a) {
        if (cantActividades < actividades.length) {
            actividades[cantActividades++] = a;
            System.out.println("Actividad registrado correctamente.");
        } else {
            System.out.println("No hay más espacio para actividades.");
        }
    }

    public void listarActividades() {
        if (cantActividades == 0) {
            System.out.println("No hay actividades registradas.");
            return;
        }

        for (int i = 0; i < cantActividades; i++) {
            Actividad a = actividades[i];
            String estado = a.hayCupo() ? "LUGARES DISPONIBLES" : "COMPLETA";
            System.out.println(a + " - " + estado);
        }
    }

    public Actividad buscarActividadPorCodigo(int codigo) {
        for (int i = 0; i < cantActividades; i++) {
            if (actividades[i].getCodigo() == codigo) {
                return actividades[i];
            }
        }
        return null;
    }

    public Actividad buscarActividadPorNombre(String nombre) {
        for (int i = 0; i < cantActividades; i++) {
            if (actividades[i].getNombre().equalsIgnoreCase(nombre)) {
                return actividades[i];
            }
        }
        return null;
    }

    public void modificarCupo(Actividad a, int nuevoCupo) {
        a.setCupoMaximo(nuevoCupo);
        System.out.println("Cupo modificado correctamente.");
    }

    public void modificarHorario(Actividad a, String nuevoHorario) {
        a.setHorario(nuevoHorario);
        System.out.println("Horario modificado correctamente.");
    }

    public void modificarPrecio(Actividad a, double nuevoPrecio) {
        a.setPrecio(nuevoPrecio);
        System.out.println("Precio modificado correctamente.");
    }

    public void aplicarCambioPrecioCategoria(String categoria, double porcentaje) {
        boolean encontrado = false;

        for (int i = 0; i < cantActividades; i++) {
            if (actividades[i].getCategoria().equalsIgnoreCase(categoria)) {
                double precioActual = actividades[i].getPrecio();
                double nuevoPrecio = precioActual + (precioActual * porcentaje / 100);
                actividades[i].setPrecio(nuevoPrecio);
                encontrado = true;
            }
        }

        if (encontrado) {
            System.out.println("Cambio aplicado correctamente a la categoría " + categoria);
        } else {
            System.out.println("No se encontraron actividades de esa categoría.");
        }
    }

    // ============================================================
    //                      EQUIPAMIENTO
    // ============================================================

    public void registrarEquipo(Equipo e) {
        if (cantEquipos < equipos.length) {
            equipos[cantEquipos++] = e;
            System.out.println("Equipo registrado correctamente.");
        } else {
            System.out.println("No hay más espacio para equipos.");
        }
    }

    public void listarEquipos() {
        if (cantEquipos == 0) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        for (int i = 0; i < cantEquipos; i++) {
            System.out.println(equipos[i]);
        }
    }

    public void listarEquiposPorEstado(String estado) {
        boolean encontrado = false;

        for (int i = 0; i < cantEquipos; i++) {
            if (equipos[i].getEstado().equalsIgnoreCase(estado)) {
                System.out.println(equipos[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron equipos con estado: " + estado);
        }
    }

    public Equipo buscarEquipoPorCodigo(int codigo) {
        for (int i = 0; i < cantEquipos; i++) {
            if (equipos[i].getCodigo() == codigo) {
                return equipos[i];
            }
        }
        return null;
    }

    public void registrarMantenimiento(Equipo e, String descripcion, String fecha) {
        e.setEstado("EN MANTENIMIENTO");
        System.out.println("Mantenimiento registrado: " + descripcion + " (" + fecha + ")");
    }

    public void finalizarMantenimiento(Equipo e) {
        e.setEstado("OPERATIVO");
        System.out.println("El equipo volvió a estado OPERATIVO.");
    }
}
