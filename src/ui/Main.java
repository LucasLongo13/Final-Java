package ui;

import java.util.Scanner;
import modelo.*;
import sistema.SistemaGimnasio;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static SistemaGimnasio sistema = new SistemaGimnasio();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nFECHA: 06/06/2025");
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 - SOCIOS");
            System.out.println("2 - ACTIVIDADES");
            System.out.println("3 - EQUIPAMIENTO");
            System.out.println("0 - SALIR");
            System.out.print("Ingrese una opcion: ");

            int op = leerEntero();

            switch (op) {
                case 1 -> menuSocios();
                case 2 -> menuActividades();
                case 3 -> menuEquipamiento();
                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    // ============================================================
    //                      MENU SOCIOS
    // ============================================================

    private static void menuSocios() {
        while (true) {
            System.out.println("\nMENU SOCIOS");
            System.out.println("1 - ALTA DE SOCIO");
            System.out.println("2 - LISTA DE SOCIOS");
            System.out.println("3 - BUSQUEDA DE SOCIO");
            System.out.println("4 - MODIFICAR SOCIO");
            System.out.println("5 - INSCRIBIR SOCIO EN ACTIVIDAD");
            System.out.println("0 - VOLVER");
            System.out.print("Ingrese una opcion: ");

            int op = leerEntero();

            switch (op) {
                case 1 -> altaSocio();
                case 2 -> menuListaSocios();
                case 3 -> buscarSocio();
                case 4 -> modificarSocio();
                case 5 -> inscribirSocioEnActividad();
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void altaSocio() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Telefono: ");
        String tel = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.println("Tipo de socio:");
        System.out.println("1 - ADULTO");
        System.out.println("2 - MENOR");
        System.out.println("3 - JUBILADO");
        int tipo = leerEntero();

        Socio s = switch (tipo) {
            case 1 -> new SocioAdulto(nombre, apellido, tel, email);
            case 2 -> {
                System.out.print("Nombre del tutor: ");
                String tutor = sc.nextLine();
                yield new SocioMenor(nombre, apellido, tel, email, tutor);
            }
            case 3 -> new SocioJubilado(nombre, apellido, tel, email);
            default -> null;
        };

        if (s == null) {
            System.out.println("Tipo invalido.");
            return;
        }

        sistema.altaSocio(s);
    }

    private static void menuListaSocios() {
        while (true) {
            System.out.println("\nLISTA DE SOCIOS");
            System.out.println("1 - LISTAR TODOS");
            System.out.println("2 - LISTAR POR TIPO");
            System.out.println("0 - VOLVER");
            System.out.print("Ingrese una opcion: ");

            int op = leerEntero();

            switch (op) {
                case 1 -> sistema.listarSocios();
                case 2 -> {
                    System.out.print("Ingrese tipo (ADULTO/MENOR/JUBILADO): ");
                    String tipo = sc.nextLine();
                    sistema.listarSociosPorTipo(tipo);
                }
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static Socio buscarSocioComun() {
        System.out.println("Buscar socio por:");
        System.out.println("1 - Codigo");
        System.out.println("2 - Apellido");
        int op = leerEntero();

        return switch (op) {
            case 1 -> {
                System.out.print("Codigo: ");
                int cod = leerEntero();
                yield sistema.buscarSocioPorCodigo(cod);
            }
            case 2 -> {
                System.out.print("Apellido: ");
                String ape = sc.nextLine();
                yield sistema.buscarSocioPorApellido(ape);
            }
            default -> null;
        };
    }

    private static void buscarSocio() {
        Socio s = buscarSocioComun();
        if (s == null) System.out.println("Socio no encontrado.");
        else System.out.println("Encontrado: " + s);
    }

    private static void modificarSocio() {
        Socio s = buscarSocioComun();
        if (s == null) {
            System.out.println("Socio no encontrado.");
            return;
        }

        while (true) {
            System.out.println("\nMODIFICAR SOCIO");
            System.out.println("1 - MODIFICAR DATOS DE CONTACTO");
            System.out.println("2 - MODIFICAR TIPO DE SOCIO");
            System.out.println("3 - REGISTRAR PAGO");
            System.out.println("0 - VOLVER");
            int op = leerEntero();

            switch (op) {
                case 1 -> {
                    System.out.print("Nuevo telefono: ");
                    String tel = sc.nextLine();
                    System.out.print("Nuevo email: ");
                    String email = sc.nextLine();
                    sistema.modificarContacto(s, tel, email);
                }
                case 2 -> {
                    System.out.print("Nuevo tipo (ADULTO/MENOR/JUBILADO): ");
                    String tipo = sc.nextLine();
                    sistema.modificarTipoSocio(s, tipo);
                }
                case 3 -> sistema.registrarPago(s);
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void inscribirSocioEnActividad() {
        Socio s = buscarSocioComun();
        if (s == null) {
            System.out.println("Socio no encontrado.");
            return;
        }

        System.out.print("Codigo de actividad: ");
        int cod = leerEntero();

        Actividad a = sistema.buscarActividadPorCodigo(cod);
        if (a == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }

        sistema.inscribirSocioEnActividad(s, a);
    }

    // ============================================================
    //                      MENU ACTIVIDADES
    // ============================================================

    private static void menuActividades() {
        while (true) {
            System.out.println("\nMENU ACTIVIDADES");
            System.out.println("1 - ALTA DE ACTIVIDAD");
            System.out.println("2 - LISTA DE ACTIVIDADES");
            System.out.println("3 - MODIFICAR ACTIVIDAD");
            System.out.println("4 - CAMBIO DE PRECIO POR CATEGORIA");
            System.out.println("0 - VOLVER");
            int op = leerEntero();

            switch (op) {
                case 1 -> altaActividad();
                case 2 -> sistema.listarActividades();
                case 3 -> modificarActividad();
                case 4 -> cambioPrecioCategoria();
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void altaActividad() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Categoria (MUSCULACION/CARDIO/GRUPAL/ACUATICA): ");
        String cat = sc.nextLine();

        System.out.print("Horario: ");
        String horario = sc.nextLine();

        System.out.print("Cupo maximo: ");
        int cupo = leerEntero();

        System.out.print("Precio mensual: ");
        double precio = leerDouble();

        Actividad a = new Actividad(nombre, cat, horario, cupo, precio);
        sistema.altaActividad(a);
    }

    private static void modificarActividad() {
        System.out.println("Buscar actividad por:");
        System.out.println("1 - Codigo");
        System.out.println("2 - Nombre");
        int op = leerEntero();

        Actividad a = null;

        switch (op) {
            case 1 -> {
                System.out.print("Codigo: ");
                int cod = leerEntero();
                a = sistema.buscarActividadPorCodigo(cod);
            }
            case 2 -> {
                System.out.print("Nombre: ");
                String nom = sc.nextLine();
                a = sistema.buscarActividadPorNombre(nom);
            }
        }

        if (a == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }

        while (true) {
            System.out.println("\nMODIFICAR ACTIVIDAD");
            System.out.println("1 - MODIFICAR CUPO");
            System.out.println("2 - MODIFICAR HORARIO");
            System.out.println("3 - MODIFICAR PRECIO");
            System.out.println("0 - VOLVER");
            int op2 = leerEntero();

            switch (op2) {
                case 1 -> {
                    System.out.print("Nuevo cupo: ");
                    int cupo = leerEntero();
                    sistema.modificarCupo(a, cupo);
                }
                case 2 -> {
                    System.out.print("Nuevo horario: ");
                    String h = sc.nextLine();
                    sistema.modificarHorario(a, h);
                }
                case 3 -> {
                    System.out.print("Nuevo precio: ");
                    double p = leerDouble();
                    sistema.modificarPrecio(a, p);
                }
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void cambioPrecioCategoria() {
        System.out.print("Categoria: ");
        String cat = sc.nextLine();

        System.out.print("Porcentaje (+ o -): ");
        double porc = leerDouble();

        sistema.aplicarCambioPrecioCategoria(cat, porc);
    }

    // ============================================================
    //                      MENU EQUIPAMIENTO
    // ============================================================

    private static void menuEquipamiento() {
        while (true) {
            System.out.println("\nMENU EQUIPAMIENTO");
            System.out.println("1 - REGISTRAR EQUIPO");
            System.out.println("2 - LISTA DE EQUIPOS");
            System.out.println("3 - REGISTRAR MANTENIMIENTO");
            System.out.println("0 - VOLVER");
            int op = leerEntero();

            switch (op) {
                case 1 -> registrarEquipo();
                case 2 -> menuListaEquipos();
                case 3 -> registrarMantenimiento();
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void registrarEquipo() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Tipo (CARDIO/MUSCULACION/PILETA/VARIOS): ");
        String tipo = sc.nextLine();

        System.out.print("Fecha de adquisicion: ");
        String fecha = sc.nextLine();

        System.out.print("Estado inicial (OPERATIVO/EN MANTENIMIENTO/FUERA DE SERVICIO): ");
        String estado = sc.nextLine();

        Equipo e = new Equipo(nombre, tipo, fecha, estado);
        sistema.registrarEquipo(e);
    }

    private static void menuListaEquipos() {
        while (true) {
            System.out.println("\nLISTA DE EQUIPOS");
            System.out.println("1 - LISTAR TODOS");
            System.out.println("2 - LISTAR POR ESTADO");
            System.out.println("0 - VOLVER");
            int op = leerEntero();

            switch (op) {
                case 1 -> sistema.listarEquipos();
                case 2 -> {
                    System.out.print("Estado: ");
                    String est = sc.nextLine();
                    sistema.listarEquiposPorEstado(est);
                }
                case 0 -> { return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void registrarMantenimiento() {
        System.out.print("Codigo del equipo: ");
        int cod = leerEntero();

        Equipo e = sistema.buscarEquipoPorCodigo(cod);
        if (e == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        System.out.print("Descripcion del mantenimiento: ");
        String desc = sc.nextLine();

        System.out.print("Fecha: ");
        String fecha = sc.nextLine();

        sistema.registrarMantenimiento(e, desc, fecha);

        System.out.println("¿Finalizar mantenimiento ahora? (1=SI / 2=NO)");
        int op = leerEntero();

        if (op == 1) sistema.finalizarMantenimiento(e);
    }

    // ============================================================
    //                      VALIDACIONES
    // ============================================================

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}
