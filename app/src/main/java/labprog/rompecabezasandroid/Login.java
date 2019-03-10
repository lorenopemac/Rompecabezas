package labprog.rompecabezasandroid;

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

public class Login extends AppCompatActivity {

    EditText nombre,contrasenia;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        this.nombre = (EditText) findViewById(R.id.txtNombre);          //LIGAR TEXTFIELD CON UNA VARIABLE
        this.contrasenia = (EditText) findViewById(R.id.txtContraseña); //LIGAR TEXTFIELD CON UNA VARIABLE
        this.btn1 = (Button) findViewById(R.id.btnIngreso);             //LIGAR BOTON CON UNA VARIABLE
        btn1.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                verificarUsuario();
            }
        });
    }

    /**
    *   SE VERIFICA SI EL USUARIO EXISTE Y SE LE PERMITE ACCEDER AL JUEGO
    */
    public void verificarUsuario(){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1); //CONECCION A LA BD
        SQLiteDatabase db= conn.getWritableDatabase();
        String nombres = nombre.getText().toString();
        String contraseniaString = contrasenia.getText().toString();

        //CONSULTAMOS A LA BASE DE DATOS, VERIFICANDO EL NOMBRE DEL USUARIO Y SU CONTRASEÑA
        Cursor fila = db.rawQuery("select nombre from usuario where nombre='"+nombres+"' and contrasenia='"+contraseniaString+"'",null);

        if(fila.moveToFirst()){//   NOS MOVEMOS AL PRIMER REGISTRO
            Toast.makeText(this,"Correcto!", Toast.LENGTH_SHORT).show();
            db.close();
            Intent toys;
            toys = new Intent(Login.this , Menu.class);//REEDIRECCION A LA PANTALLA DE MENU PARA LA SELECCION DE JUEGO
            toys.putExtra("nom",nombres);//ENVIAMOS NOMBRE PARA LUEGO VERIFICAR EL GUARDADO DE PUNTOS
            startActivity(toys);
        }else{//NO EXISTE EL USUARIO O LA CONTRASEÑA ES INCORRECTA
            Toast.makeText(this,"Datos Incorrectos!", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }



}
