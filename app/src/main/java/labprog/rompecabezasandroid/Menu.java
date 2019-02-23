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
        butMario = (Button) findViewById(R.id.btnMario);
        butMario.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toys;
                Bundle extras = getIntent().getExtras();
                String username= extras.getString("nom");
                toys = new Intent(Menu.this , Juego.class);
                toys.putExtra("nom",username);
                toys.putExtra("tipo","marioparte");
                startActivity(toys);
            }
        });
        butPaloma = (Button) findViewById(R.id.btnPaloma);
        butPaloma.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toys;
                Bundle extras = getIntent().getExtras();
                String username= extras.getString("nom");
                toys = new Intent(Menu.this , Juego.class);
                toys.putExtra("nom",username);
                toys.putExtra("tipo","paloma");
                startActivity(toys);
            }
        });
    }
}
