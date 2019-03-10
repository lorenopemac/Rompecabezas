package labprog.rompecabezasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button butMario,butPaloma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        /**
         *  BOTON QUE REDIRECCIONA AL ROMPECABEZAS DE MARIO
         */
        butMario = (Button) findViewById(R.id.btnMario);
        butMario.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toys;
                Bundle extras = getIntent().getExtras();
                String username= extras.getString("nom");
                toys = new Intent(Menu.this , Juego.class);
                toys.putExtra("nom",username);                      //ENVIO DEL NOMBRE DE USR PARA EL GUARDADO DEL PUNTAJE
                toys.putExtra("tipo","marioparte");           //ENVIO EL TIPO PARA QUE LUEGO SE MUESTRE EN LA PANTALLA
                startActivity(toys);
            }
        });
        /**
         *  BOTON QUE REDIRECCIONA AL ROMPECABEZAS DE PALOMA
         */
        butPaloma = (Button) findViewById(R.id.btnPaloma);
        butPaloma.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toys;
                Bundle extras = getIntent().getExtras();
                String username= extras.getString("nom");
                toys = new Intent(Menu.this , Juego.class);
                toys.putExtra("nom",username);                      //ENVIO DEL NOMBRE DE USR PARA EL GUARDADO DEL PUNTAJE
                toys.putExtra("tipo","paloma");               //ENVIO EL TIPO PARA QUE LUEGO SE MUESTRE EN LA PANTALLA
                startActivity(toys);
            }
        });
    }
}
