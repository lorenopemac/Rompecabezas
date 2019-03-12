package labprog.rompecabezasandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import entidades.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerctrl;
    Button btn;
    Locale unLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1); //CONEXION A LA BD
        spinnerctrl = (Spinner) findViewById(R.id.spinner1);// LIGAR DISEÑO CON VARIABLE
        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {    //EVENTO DE SELECCION DE UN IDIOMA

            public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {

                if (pos == 1) {
                    Toast.makeText(parent.getContext(),"You have selected Español", Toast.LENGTH_SHORT).show();
                    cambiarIdioma("sp");
                } else if (pos == 2) {
                    Toast.makeText(parent.getContext(),"You have selected English", Toast.LENGTH_SHORT).show();
                    cambiarIdioma("en");
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
        init();
    }
    public Button but1;
    public Button but2;
    public Button but3;
    public MediaPlayer sound;
    public Button bts, bts2;

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

        //BOTON DE RANKING
        but3 = (Button) findViewById(R.id.button3);
        but3.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v) {
                Intent toys;
                toys = new Intent(MainActivity.this, rankings.class);
                startActivity(toys);
            }
        });

        //BOTON DE SONIDO
        bts = (Button) findViewById(R.id.sonido);

        sound = MediaPlayer.create(this, R.raw.juego_de_tronos);

        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sound.isPlaying()){              // SI SE ESTA EN REPRODUCCION
                    sound.pause();
                    bts.setBackgroundResource(R.drawable.sin_sonido2);
                    Toast.makeText(MainActivity.this,"Pausar",Toast.LENGTH_SHORT).show();
                }
                else{
                    sound.start();
                    bts.setBackgroundResource(R.drawable.sonido3);
                    Toast.makeText(MainActivity.this,"Reproducir",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cambiarIdioma(String lenguaje) {
        unLocale = new Locale(lenguaje);                //VARIABLE DE REGIONES
        Resources res = getResources();                 //OBTENER RECURSOS
        DisplayMetrics dm = res.getDisplayMetrics();    //OBTENER PROPIEDADES DE LA PANTALLA
        Configuration conf = res.getConfiguration();    //CONFIGURACION
        conf.locale = unLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);                        //REFRESCAR PANTALLA
    }




}
