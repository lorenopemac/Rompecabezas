package entidades;

public class usuario {

    private Integer id;
    private String nombre;
    private String contraseña;
    private int puntos;


    public usuario(Integer id, String nombre, String contraseña,int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.puntos = puntos;
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

    public int getPuntos(){return this.puntos;}

    public void setPuntos(int puntos){ this.puntos = puntos;}
}
