package utilidades;

import android.icu.lang.UCharacter;

import java.awt.font.NumericShaper;

public class Utilidades {

    //Constantes campos tabla usuario
    private static final String TABLA_USUARIO="usuario";
    private static final String CAMPO_ID="id";
    private static final String CAMPO_NOMBRE="nombre";
    private static final String CAMPO_CONTRASEÑA="contrasenia";
    private static final String CAMPO_PUNTAJE= "puntos";

    private static final String CREAR_TABLAS_USUARIO="CREATE TABLE"+ TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_CONTRASEÑA+" TEXT"+CAMPO_PUNTAJE+" INTEGER)";


    public static String getTablaUsuario() {
        return TABLA_USUARIO;
    }

    public static String getCampoId() {
        return CAMPO_ID;
    }

    public static String getCampoNombre() {
        return CAMPO_NOMBRE;
    }

    public static String getCampoContraseña() {
        return CAMPO_CONTRASEÑA;
    }

    public static String getCampoPuntaje() {
        return CAMPO_PUNTAJE;
    }

    public static String getCrearTablasUsuario() {
        return CREAR_TABLAS_USUARIO;
    }
}
