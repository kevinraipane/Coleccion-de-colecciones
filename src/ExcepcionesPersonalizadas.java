
//ATENCION: aca estamos metiendo todas las excepciones en un solo .Java, pero
//en caso de proyectos mas grandes, recordemos que convendria usar un .Java
//por cada tipo de excepcion

public class ExcepcionesPersonalizadas {

    //aqui armamos las excepciones personalizadas segun pide el enunciado...

    public static class CursoNoEncontradoException extends Exception {
        public CursoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class AlumnoYaInscriptoException extends Exception {
        public AlumnoYaInscriptoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class AlumnoNoEncontradoException extends Exception {
        public AlumnoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class CursoVacioException extends Exception {
        public CursoVacioException(String mensaje) {
            super(mensaje);
        }
    }

    public static class CursoYaExistenteException extends Exception {
        public CursoYaExistenteException(String mensaje) {
            super(mensaje);
        }
    }

    public static class NoHayCursosException extends Exception {
        public NoHayCursosException(String mensaje) {
            super(mensaje);
        }
    }
}
