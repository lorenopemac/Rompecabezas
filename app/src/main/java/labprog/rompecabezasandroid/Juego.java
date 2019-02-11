package labprog.rompecabezasandroid;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        start();
    }


        Button b1,b2,b3,b4,b5,b6,b7,b8,b9,buttonActual, buttonCambio;
        int img1, img2, img3, img4, img5, img6, img7, img8, img9;
        Drawable imgAuxAct,imgAuxCam;
        int turn;
        public void checkGame(){
            checkWinner(b1,b2,b3);
            checkWinner(b4,b5,b6);
            checkWinner(b7,b8,b9);
            checkWinner(b1,b4,b7);
            checkWinner(b2,b5,b8);
            checkWinner(b3,b6,b9);
            checkWinner(b1,b5,b9);
            checkWinner(b3,b5,b7);
        }

        public void checkWinner(Button b1,Button b2,Button b3){
            if (b1.getText()==b2.getText().toString() && b2.getText().toString()==b3.getText().toString() && b3.getText().toString()=="X"){
                Toast.makeText(Juego.this,"player 1 is winner ",Toast.LENGTH_LONG).show();

            }
            if (b1.getText()==b2.getText().toString() && b2.getText().toString()==b3.getText().toString() && b3.getText().toString()=="O"){
                //player 2 wins
                Toast.makeText(Juego.this,"player 2 is winner ",Toast.LENGTH_LONG).show();
            }
        }
        public void reset(View view){
            turn =1;
            b1.setText("T");
            b2.setText("T");
            b4.setText("");
            b5.setText("");
            b6.setText("");
            b7.setText("");
            b8.setText("");
            b9.setText("");

        }
/*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.startup);
        }*/
        public void start(){
            setContentView(R.layout.activity_juego);
            b1=(Button) findViewById(R.id.b1);
            b2=(Button) findViewById(R.id.b2);
            b3=(Button) findViewById(R.id.b3);
            b4=(Button) findViewById(R.id.b4);
            b5=(Button) findViewById(R.id.b5);
            b6=(Button) findViewById(R.id.b6);
            b7=(Button) findViewById(R.id.b7);
            b8=(Button) findViewById(R.id.b8);
            b9=(Button) findViewById(R.id.b9);
            img1=R.drawable.pigeon_piece1;
            img2=R.drawable.pigeon_piece2;
            img3=R.drawable.pigeon_piece3;
            img4=R.drawable.pigeon_piece4;
            img5=R.drawable.pigeon_piece5;
            img6=R.drawable.pigeon_piece6;
            img7=R.drawable.pigeon_piece7;
            img8=R.drawable.pigeon_piece8;
            img9=R.drawable.pigeon_piece9;
            b1.setBackgroundResource(img1);
            b2.setBackgroundResource(img2);
            b3.setBackgroundResource(img3);
            b4.setBackgroundResource(img4);
            b5.setBackgroundResource(img5);
            b6.setBackgroundResource(img6);
            b7.setBackgroundResource(img7);
            b8.setBackgroundResource(img8);
            b9.setBackgroundResource(img9);


            turn=1;
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(Juego.this, "Datos Incorrectos!", Toast.LENGTH_SHORT).show();
                    if(buttonActual == null){
                        imgAuxAct=b1.getBackground();
                        Toast.makeText(Juego.this, "ENTRO!", Toast.LENGTH_SHORT).show();
                        buttonActual = b1;
                        b5.setBackgroundDrawable(imgAuxAct);
                    }else{

                    }
                    checkGame();
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();

                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    checkGame();
                }

            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkGame();
                }
            });
        }


}
