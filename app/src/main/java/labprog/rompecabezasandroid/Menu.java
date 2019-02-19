package labprog.rompecabezasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    Button butMario,butPaloma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        butMario = (Button) findViewById(R.id.btnMario);
        butMario.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toys;
                toys = new Intent(menu.this , Juego.class);
                startActivity(toys);
            }
        });
    }
}
