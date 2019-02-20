package labprog.rompecabezasandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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
    Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);
        spinnerctrl = (Spinner) findViewById(R.id.spinner1);
        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {

                if (pos == 1) {
                    Toast.makeText(parent.getContext(),"You have selected Español", Toast.LENGTH_SHORT).show();
                    setLocale("sp");
                } else if (pos == 2) {
                    Toast.makeText(parent.getContext(),"You have selected English", Toast.LENGTH_SHORT).show();
                    setLocale("en");
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

    public void setLocale(String lenguaje) {

        myLocale = new Locale(lenguaje);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
    }



}
