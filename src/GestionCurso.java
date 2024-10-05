import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GestionCurso {

    // mapa que almacena cursos y su lista de alumnos (par: clave/valor)
    private HashMap<String, HashSet<Alumno>> cursos;//uso HashSet para evitar duplicados
    //cursos es un obj de tipo hashMap donde la clave es el nombre del curso, y el valor
    //es un cjto de alumnos (HashSet)
    public GestionCurso() {
        this.cursos = new HashMap<>();//fabricamos el mapa...
    }

    // agregar un alumno a un curso
    public void agregarAlumno(String curso, Alumno alumno)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException,
            ExcepcionesPersonalizadas.AlumnoYaInscriptoException //aviso que puedo lanzar excepciones
        {
            if (!cursos.containsKey(curso)) {//containsKey?...recordemos!
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso " + curso + " no existe.");
        }
        //aca buscamos el nombre del curso entre las claves del HashMap...
        HashSet<Alumno> alumnos = cursos.get(curso);//si existe el curso, devuelvo los alumnos, sino; null
        if (alumnos.contains(alumno)) {//alumnos: contiene a los alumnos del curso en cuestion...
            //si el alumno a insertar ya esta en la lista de alumnos, lanzo la excepcion!
            throw new ExcepcionesPersonalizadas.AlumnoYaInscriptoException("El alumno con DNI " + alumno.getDni() + " ya está inscripto en el curso " + curso);
        }
        //si no estaba el alumno, lo agrego...
        alumnos.add(alumno);
    }

    // eliminar un alumno de un curso

    //sigo el mismo criterio para el resto...
    public void eliminarAlumno(String curso, String dni)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException,
            ExcepcionesPersonalizadas.AlumnoNoEncontradoException
        {
            if (!cursos.containsKey(curso)) {
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso " + curso + " no existe.");
        }

        HashSet<Alumno> alumnos = cursos.get(curso);
        Alumno alumnoAEliminar = buscarAlumnoPorDni(alumnos, dni);
        if (alumnoAEliminar == null) {
            throw new ExcepcionesPersonalizadas.AlumnoNoEncontradoException("El alumno con DNI " + dni + " no está inscripto en el curso " + curso);
        }

        alumnos.remove(alumnoAEliminar);
    }

    // listar alumnos de un curso
    public Set<String> listarAlumnos(String curso)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException,
            ExcepcionesPersonalizadas.CursoVacioException
        {
            if (!cursos.containsKey(curso)) {
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso " + curso + " no existe.");
        }

        HashSet<Alumno> alumnos = cursos.get(curso);
        if (alumnos.isEmpty()) {
            throw new ExcepcionesPersonalizadas.CursoVacioException("El curso " + curso + " no tiene alumnos.");
        }

        // retornamos los nombres de los alumnos
        Set<String> nombres = new HashSet<>();
        for (Alumno alumno : alumnos) {
            nombres.add(alumno.getNombre());
        }

        return nombres;
    }

    // contar alumnos en un curso
    public int contarAlumnos(String curso)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException,
            ExcepcionesPersonalizadas.CursoVacioException
        {
            if (!cursos.containsKey(curso)) {
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso " + curso + " no existe.");
        }

        HashSet<Alumno> alumnos = cursos.get(curso);
        if (alumnos.isEmpty()) {
            throw new ExcepcionesPersonalizadas.CursoVacioException("El curso " + curso + " no tiene alumnos.");
        }

        return alumnos.size();
    }

    // transferir un alumno entre cursos
    public void transferirAlumno(String cursoOrigen, String cursoDestino, String dni)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException,
            ExcepcionesPersonalizadas.AlumnoNoEncontradoException,
            ExcepcionesPersonalizadas.AlumnoYaInscriptoException
        {
            if (!cursos.containsKey(cursoOrigen)) {
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso de origen " + cursoOrigen + " no existe.");
        }
        if (!cursos.containsKey(cursoDestino)) {
            throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso de destino " + cursoDestino + " no existe.");
        }

        HashSet<Alumno> alumnosOrigen = cursos.get(cursoOrigen);
        Alumno alumno = buscarAlumnoPorDni(alumnosOrigen, dni);
        if (alumno == null) {
            throw new ExcepcionesPersonalizadas.AlumnoNoEncontradoException("El alumno con DNI " + dni + " no está inscrito en el curso de origen " + cursoOrigen);
        }

        HashSet<Alumno> alumnosDestino = cursos.get(cursoDestino);
        if (alumnosDestino.contains(alumno)) {
            throw new ExcepcionesPersonalizadas.AlumnoYaInscriptoException("El alumno con DNI " + dni + " ya está inscrito en el curso de destino " + cursoDestino);
        }

        // transferir el alumno
        alumnosOrigen.remove(alumno);
        alumnosDestino.add(alumno);
    }

    // listar todos los cursos disponibles
    public Set<String> listarCursos() throws ExcepcionesPersonalizadas.NoHayCursosException
        {
            if (cursos.isEmpty()) {
                throw new ExcepcionesPersonalizadas.NoHayCursosException("No hay cursos disponibles.");
        }

        return cursos.keySet();
    }

    // verificar si un alumno esta inscripto en un curso
    public boolean verificarInscripcion(String curso, String dni)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException
        {
            if (!cursos.containsKey(curso)) {
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso " + curso + " no existe.");
        }

        HashSet<Alumno> alumnos = cursos.get(curso);
        return buscarAlumnoPorDni(alumnos, dni) != null;
    }

    // cambiar el nombre de un curso
    public void cambiarNombreCurso(String cursoActual, String nuevoNombre)
            throws ExcepcionesPersonalizadas.CursoNoEncontradoException,
            ExcepcionesPersonalizadas.CursoYaExistenteException
        {
            if (!cursos.containsKey(cursoActual)) {
                throw new ExcepcionesPersonalizadas.CursoNoEncontradoException("El curso " + cursoActual + " no existe.");
        }

        if (cursos.containsKey(nuevoNombre)) {
            throw new ExcepcionesPersonalizadas.CursoYaExistenteException("Ya existe un curso con el nombre " + nuevoNombre);
        }

        // Cambiar el nombre del curso
        HashSet<Alumno> alumnos = cursos.remove(cursoActual);
        cursos.put(nuevoNombre, alumnos);
    }

    // auxiliar para buscar un alumno por DNI
    private Alumno buscarAlumnoPorDni(HashSet<Alumno> alumnos, String dni) {
        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(dni)) {
                return alumno;
            }
        }
        return null;
    }
}
