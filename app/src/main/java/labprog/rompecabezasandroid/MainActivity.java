package labprog.rompecabezasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import entidades.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);
        init();

    }
    public Button but1;
    public Button but2;
    public Button but3;
    public void init(){
        //BOTON PARA ACCEDER A LA BD
        but1 = (Button) findViewById(R.id.button1);
        but1.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toy;
                toy = new Intent(MainActivity.this , Login.class);
                startActivity(toy);
            }
        });
        //BOTON PARA REGISTRARSE
        but2 = (Button) findViewById(R.id.button2);
        but2.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toys;
                toys = new Intent(MainActivity.this , Registro.class);
                startActivity(toys);
            }
        });
    }



}
