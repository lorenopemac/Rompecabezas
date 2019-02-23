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
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bdU",null,1);

        listaDBUsers = (ListView) findViewById(R.id.listaUsers);
        listaDBPointM = (ListView) findViewById(R.id.listaPoint);
        listaDBPointP = (ListView) findViewById(R.id.listaPoint2);

        ConsultarLista();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoUsers);
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoPointM);
        ArrayAdapter adaptador3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoPointP);

        listaDBUsers.setAdapter(adaptador);
        listaDBPointM.setAdapter(adaptador2);
        listaDBPointP.setAdapter(adaptador3);


    }

    private void ConsultarLista() {
        SQLiteDatabase db = conn.getReadableDatabase();

        usuario user =null;
        listaUsers = new ArrayList<usuario>();

        Cursor cursor= db.rawQuery("SELECT * FROM " + Utilidades.getTablaUsuario(), null);

        while(cursor.moveToNext()){
            user = new usuario();
            user.setNombre(cursor.getString(1));
            user.setPuntosM(cursor.getInt(4));
            user.setPuntosP(cursor.getInt(5));

            listaUsers.add(user);

        }
        ordenarLista();
        obtenerLista();
    }

    private void ordenarLista() {

        ArrayList<usuario> listOrdenada;

        for (int i=0; i<listaUsers.size();i++){


            for (int j=i;j<listaUsers.size();j++){

                if(listaUsers.get(i).getPuntosM()+listaUsers.get(i).getPuntosP()>listaUsers.get(j).getPuntosM()+listaUsers.get(j).getPuntosP()){
                    String name=listaUsers.get(i).getNombre();
                    String pass = listaUsers.get(i).getContraseña();
                    int point = listaUsers.get(i).getPuntosM();
                    int point2 = listaUsers.get(i).getPuntosP();

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
