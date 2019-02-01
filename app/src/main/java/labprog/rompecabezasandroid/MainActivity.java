package labprog.rompecabezasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    public Button but1;

    public void init(){
        but1 = (Button) findViewById(R.id.button1);
        but1.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent toy;
                toy = new Intent(MainActivity.this , Login.class);
                startActivity(toy);
            }
        });
    }



}
