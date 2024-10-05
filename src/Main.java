import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        GestionCurso gestionCursos = new GestionCurso();//clase gestora...
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSistema de Gestión de Alumnos y Cursos");
            System.out.println("1. Agregar alumno a un curso");
            System.out.println("2. Eliminar alumno de un curso");
            System.out.println("3. Listar alumnos de un curso");
            System.out.println("4. Contar alumnos en un curso");
            System.out.println("5. Transferir alumno entre cursos");
            System.out.println("6. Listar todos los cursos disponibles");
            System.out.println("7. Verificar si un alumno está inscrito en un curso");
            System.out.println("8. Cambiar nombre de un curso");
            System.out.println("9. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consumir linea en blanco

            switch (opcion) {
                case 1://empezamos a manejar las excepciones!

                    try {
                        System.out.print("Ingrese el nombre del curso: ");
                        String curso = scanner.nextLine();
                        System.out.print("Ingrese el nombre del alumno: ");
                        String nombreAlumno = scanner.nextLine();
                        System.out.print("Ingrese el DNI del alumno: ");
                        String dni = scanner.nextLine();
                        Alumno alumno = new Alumno(nombreAlumno, dni);
                        gestionCursos.agregarAlumno(curso, alumno);
                        System.out.println("Alumno agregado correctamente al curso.");
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    } catch (ExcepcionesPersonalizadas.AlumnoYaInscriptoException e) {
                        System.out.println(e.getMessage());
                    }
              /*
                    try {
                        System.out.print("Ingrese el nombre del curso: ");
                        String curso = scanner.nextLine();
                        System.out.print("Ingrese el nombre del alumno: ");
                        String nombreAlumno = scanner.nextLine();
                        System.out.print("Ingrese el DNI del alumno: ");
                        String dni = scanner.nextLine();
                        Alumno alumno = new Alumno(nombreAlumno, dni);
                        gestionCursos.agregarAlumno(curso, alumno);
                        System.out.println("Alumno agregado correctamente al curso.");
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException | ExcepcionesPersonalizadas.AlumnoYaInscriptoException e) {
                        System.out.println(e.getMessage());//usamos multicatch (es mas comodo)
                    }

               */
                    break;

                case 2:
                    try {
                        System.out.print("Ingrese el nombre del curso: ");
                        String curso = scanner.nextLine();
                        System.out.print("Ingrese el DNI del alumno a eliminar: ");
                        String dni = scanner.nextLine();
                        gestionCursos.eliminarAlumno(curso, dni);
                        System.out.println("Alumno eliminado correctamente del curso.");
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException | ExcepcionesPersonalizadas.AlumnoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Ingrese el nombre del curso: ");
                        String curso = scanner.nextLine();
                        Set<String> alumnos = gestionCursos.listarAlumnos(curso);
                        System.out.println("Alumnos inscritos en el curso " + curso + ":");
                        for (String nombre : alumnos) {
                            System.out.println(nombre);
                        }
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException | ExcepcionesPersonalizadas.CursoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Ingrese el nombre del curso: ");
                        String curso = scanner.nextLine();
                        int cantidad = gestionCursos.contarAlumnos(curso);
                        System.out.println("Cantidad de alumnos en el curso " + curso + ": " + cantidad);
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException | ExcepcionesPersonalizadas.CursoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Ingrese el nombre del curso de origen: ");
                        String cursoOrigen = scanner.nextLine();
                        System.out.print("Ingrese el nombre del curso de destino: ");
                        String cursoDestino = scanner.nextLine();
                        System.out.print("Ingrese el DNI del alumno a transferir: ");
                        String dni = scanner.nextLine();
                        gestionCursos.transferirAlumno(cursoOrigen, cursoDestino, dni);
                        System.out.println("Alumno transferido correctamente.");
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException | ExcepcionesPersonalizadas.AlumnoNoEncontradoException | ExcepcionesPersonalizadas.AlumnoYaInscriptoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        Set<String> cursos = gestionCursos.listarCursos();
                        System.out.println("Cursos disponibles:");
                        for (String nombreCurso : cursos) {
                            System.out.println(nombreCurso);
                        }
                    } catch (ExcepcionesPersonalizadas.NoHayCursosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    try {
                        System.out.print("Ingrese el nombre del curso: ");
                        String curso = scanner.nextLine();
                        System.out.print("Ingrese el DNI del alumno: ");
                        String dni = scanner.nextLine();
                        boolean inscrito = gestionCursos.verificarInscripcion(curso, dni);
                        if (inscrito) {
                            System.out.println("El alumno con DNI " + dni + " está inscrito en el curso " + curso);
                        } else {
                            System.out.println("El alumno con DNI " + dni + " no está inscrito en el curso " + curso);
                        }
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        System.out.print("Ingrese el nombre actual del curso: ");
                        String cursoActual = scanner.nextLine();
                        System.out.print("Ingrese el nuevo nombre del curso: ");
                        String nuevoNombre = scanner.nextLine();
                        gestionCursos.cambiarNombreCurso(cursoActual, nuevoNombre);
                        System.out.println("Nombre del curso cambiado correctamente.");
                    } catch (ExcepcionesPersonalizadas.CursoNoEncontradoException | ExcepcionesPersonalizadas.CursoYaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}
