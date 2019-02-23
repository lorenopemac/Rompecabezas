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

    ListView listaDBUsers, listaDBPoint;
    ArrayList<String> listaInfoUsers;
    ArrayList<Integer> listaInfoPoint;
    ArrayList<usuario> listaUsers;

    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bdU",null,1);

        listaDBUsers = (ListView) findViewById(R.id.listaUsers);
        listaDBPoint = (ListView) findViewById(R.id.listaPoint);

        ConsultarLista();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoUsers);
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfoPoint);

        listaDBUsers.setAdapter(adaptador);
        listaDBPoint.setAdapter(adaptador2);


    }

    private void ConsultarLista() {
        SQLiteDatabase db = conn.getReadableDatabase();

        usuario user =null;
        listaUsers = new ArrayList<usuario>();

        Cursor cursor= db.rawQuery("SELECT * FROM " + Utilidades.getTablaUsuario(), null);

        while(cursor.moveToNext()){
            user = new usuario();
            user.setNombre(cursor.getString(1));
            user.setPuntaje(cursor.getInt(3));

            listaUsers.add(user);

        }
        obtenerLista();
    }


  private void obtenerLista() {
        listaInfoUsers=new ArrayList<String>();
        listaInfoPoint = new ArrayList<Integer>();

        for(int i=0 ; i<listaUsers.size(); i++){


            listaInfoUsers.add(listaUsers.get(i).getNombre());

            listaInfoPoint.add(listaUsers.get(i).getPuntaje());

        }
    }
}
