package utilidades;

public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CONTRASEÑA="contrasenia";

    public static final String CREAR_TABLAS_USUARIO="CREATE TABLE"+ TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_CONTRASEÑA+" TEXT)";

}
