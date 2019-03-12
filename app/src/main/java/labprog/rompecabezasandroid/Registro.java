package labprog.rompecabezasandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import entidades.ConexionSQLiteHelper;
import utilidades.Utilidades;

public class Registro extends AppCompatActivity {
    Button but1;
    EditText campoId,campoNombre, campoContrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * LIGAR TEXT/BOTONES CON VARIABLES
         */
        campoNombre= (EditText) findViewById(R.id.editTextUsuario);
        campoContrasenia= (EditText) findViewById(R.id.editTextContraseña);
        but1 = (Button) findViewById(R.id.buttonRegistro);
        but1.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                verificarUsuario();
            }
        });

    }

    public void Onclick(View view ){
        registrarUsuario();// SE REGISTRA EL USUARIO
    }

    /**
     * SE PROCEDE A REGISTRAR AL USUARIO EN LA BASE DE DATOS CON UN NOMBRE Y CONTRASEÑA
     */
    private void registrarUsuario() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);//CONECTAR A LA BASE DE DATOS
        SQLiteDatabase db= conn.getWritableDatabase();// OBTENER BD
        String nombre = campoNombre.getText().toString();
        String contrasenia = campoContrasenia.getText().toString();


        ContentValues values= new ContentValues();// SE ARMA UN REGISTRO CON LOS VALORES DE USUARIO NOMBRE Y CONTRASEÑA
        values.put(Utilidades.getCampoNombre(),nombre);
        values.put(Utilidades.getCampoContraseña(),contrasenia);

        Long idResultante=db.insert("usuario",null, values);// SE INSERTA EL REGISTRO
        Toast.makeText(this,"Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show();
        db.close();//CIERRE DE BASE DE DATOS
    }

    /**
     * SE VERIFICA QUE EL USUARIO NO EXISTA, PARA EVITAR DUPLICADOS
     */
    public void verificarUsuario(){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();
        String nombres = campoNombre.getText().toString();

        Cursor fila = db.rawQuery("select nombre from usuario where nombre='"+nombres+"'",null);

       if(fila.moveToFirst()){  //SI TIENE UN REGISTRO; ENTONCES EXISTE EL USUARIO
            Toast.makeText(this,"El usuario ya existe!", Toast.LENGTH_SHORT).show();
        }else{
            registrarUsuario();
        }
        db.close();
    }

}
