package entidades;

public class usuario {

    private Integer id;
    private String nombre;
    private String contraseña;
    private int puntos;
    private int puntosM;
    private int puntosP;


    public usuario(){
        this.id = null;
        this.nombre = "";
        this.contraseña = "";
        this.puntos=1000;
        this.puntosM = 1000;
        this.puntosP= 1000;
    }
    public usuario(Integer id, String nombre, String contraseña, int puntaje, int m, int p) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.puntos = puntaje;
        this.puntosM=m;
        this.puntosP=p;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getPuntaje () {return puntos; }

    public void setPuntaje(int puntaje){ this.puntos=puntaje; }

    public int getPuntosM() {
        return puntosM;
    }

    public void setPuntosM(int puntosM) {
        this.puntosM = puntosM;
    }

    public int getPuntosP() {
        return puntosP;
    }

    public void setPuntosP(int puntosP) {
        this.puntosP = puntosP;
    }
}
