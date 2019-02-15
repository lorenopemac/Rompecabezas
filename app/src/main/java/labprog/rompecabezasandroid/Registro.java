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
        registrarUsuario();
    }

    private void registrarUsuario() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();
        String nombre = campoNombre.getText().toString();
        String contrasenia = campoContrasenia.getText().toString();


        ContentValues values= new ContentValues();
        //values.put(Utilidades.CAMPO_ID,"1");//campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,nombre);
        values.put(Utilidades.CAMPO_CONTRASEÑA,contrasenia);
        values.put("puntos",0);

        Long idResultante=db.insert("usuario",null, values);
        Toast.makeText(this,"Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"Id Registro:"+idResultante,Toast.LENGTH_SHORT).show();
        //Long idResultante=db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID,values);
        //Toast.makeText(getApplicationContext(),"Id Registro:"+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void verificarUsuario(){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();
        String nombres = campoNombre.getText().toString();

        Cursor fila = db.rawQuery("select nombre from usuario where nombre='"+nombres+"'",null);

       if(fila.moveToFirst()){
            Toast.makeText(this,"El usuario ya existe!", Toast.LENGTH_SHORT).show();
            db.close();
        }else{
            registrarUsuario();
        }
    }

}
