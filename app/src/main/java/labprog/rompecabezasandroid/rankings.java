package labprog.rompecabezasandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import entidades.ConexionSQLiteHelper;
import entidades.usuario;
import utilidades.Utilidades;

public class rankings extends AppCompatActivity {

    ListView listaDBUsers, listaDBPointM,listaDBPointP;
    ArrayList<String> listaInfoUsers;
    ArrayList<Integer> listaInfoPointM,listaInfoPointP;
    ArrayList<usuario> listaUsers;

    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bdU",null,1);// CONEXION DE LA BD
        //OBTENER LISTAS DE LA VISTA
        listaDBUsers = (ListView) findViewById(R.id.listaUsers);
        listaDBPointM = (ListView) findViewById(R.id.listaPoint);
        listaDBPointP = (ListView) findViewById(R.id.listaPoint2);

        ConsultarLista();// OBTENER LOS USUARIOS DE LA BASE DE DATOS

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoUsers);//NOMBRE USUARIO
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoPointM);//PUNTOS DE MARIO
        ArrayAdapter adaptador3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoPointP);//PUNTOS DE PALOMA

        listaDBUsers.setAdapter(adaptador);//LIGAR DATOS CON LAS LISTAS
        listaDBPointM.setAdapter(adaptador2);//LIGAR DATOS CON LAS LISTAS
        listaDBPointP.setAdapter(adaptador3);//LIGAR DATOS CON LAS LISTAS


    }

    private void ConsultarLista() {
        SQLiteDatabase db = conn.getReadableDatabase();//BD PARA LECTURA

        usuario user =null;
        listaUsers = new ArrayList<usuario>();      //LISTA DE LOS USUARIOS

        Cursor cursor= db.rawQuery("SELECT * FROM " + Utilidades.getTablaUsuario(), null);// OBTENER TODOS LOS USUARIOS

        while(cursor.moveToNext()){
            user = new usuario();                           //INICIALIZAR VARIABLE
            user.setNombre(cursor.getString(1)); //OBTENER EL NOMBRE
            user.setPuntosM(cursor.getInt(4));  //OBTENER LOS PUNTOS DEL JUEGO MARIO
            user.setPuntosP(cursor.getInt(5));  //OBTENER LOS PUNTOS DEL JUEGO PALOMA

            listaUsers.add(user);                           //AGREGAR A LA LISTA DE USUARIOS

        }
        ordenarLista();     //ORDENAR LA LISTA DEPENDIENDO DE LOS PUNTAJES EN LOS DOS JUEGOS
        obtenerLista();
    }

    private void ordenarLista() {

        ArrayList<usuario> listOrdenada;

        for (int i=0; i<listaUsers.size();i++){


            for (int j=i;j<listaUsers.size();j++){

                if(listaUsers.get(i).getPuntosM()+listaUsers.get(i).getPuntosP()>listaUsers.get(j).getPuntosM()+listaUsers.get(j).getPuntosP()){
                    /**
                     * SE ORDENA LA LISTA DE LOS USUARIOS UTILIZANDO UN ARRAY
                     */
                    String name=listaUsers.get(i).getNombre();
                    String pass = listaUsers.get(i).getContraseña();
                    int point = listaUsers.get(i).getPuntosM();         //PUNTOS DE MARIO
                    int point2 = listaUsers.get(i).getPuntosP();        //PUNTOS DE PALOMA

                    listaUsers.get(i).setNombre(listaUsers.get(j).getNombre());
                    listaUsers.get(i).setContraseña(listaUsers.get(j).getContraseña());
                    listaUsers.get(i).setPuntosM(listaUsers.get(j).getPuntosM());
                    listaUsers.get(i).setPuntosP(listaUsers.get(j).getPuntosP());

                    listaUsers.get(j).setNombre(name);
                    listaUsers.get(j).setContraseña(pass);
                    listaUsers.get(j).setPuntosM(point);
                    listaUsers.get(j).setPuntosP(point2);

                }
            }
        }
    }

    /**
     * ASIGNAR LOS REGISTROS A LAS LISTAS QUE TENGAN LOS DOS PUNTAJES,OSEA JUGARON LOS DOS JUEGOS
     */
    private void obtenerLista() {
        listaInfoUsers=new ArrayList<String>();
        listaInfoPointM = new ArrayList<Integer>();
        listaInfoPointP = new ArrayList<Integer>();

        for(int i=0 ; i<listaUsers.size(); i++){

            if(listaUsers.get(i).getPuntosM()!=0 && listaUsers.get(i).getPuntosP()!=0){

                listaInfoUsers.add(listaUsers.get(i).getNombre());

                listaInfoPointM.add(listaUsers.get(i).getPuntosM());
                listaInfoPointP.add(listaUsers.get(i).getPuntosP());
            }


        }
    }
}
