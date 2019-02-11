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
        inicio();
    }


        Button b1,b2,b3,b4,b5,b6,b7,b8,b9,buttonActual, buttonCambio;
        int img1, img2, img3, img4, img5, img6, img7, img8, img9;
        Drawable imgAuxAct,imgAuxCam;
        int turnos=0;
        public void seGano(){
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
            turnos =0;
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
        public void inicio(){
            setContentView(R.layout.activity_juego);
            //RECUPERACION DE LOS BOTONES
            b1=(Button) findViewById(R.id.b1);
            b2=(Button) findViewById(R.id.b2);
            b3=(Button) findViewById(R.id.b3);
            b4=(Button) findViewById(R.id.b4);
            b5=(Button) findViewById(R.id.b5);
            b6=(Button) findViewById(R.id.b6);
            b7=(Button) findViewById(R.id.b7);
            b8=(Button) findViewById(R.id.b8);
            b9=(Button) findViewById(R.id.b9);
            //ESTABLECER IMAGENES
            img1=R.drawable.marioparte1;
            img2=R.drawable.marioparte2;
            img3=R.drawable.marioparte3;
            img4=R.drawable.marioparte4;
            img5=R.drawable.marioparte5;
            img6=R.drawable.marioparte6;
            img7=R.drawable.marioparte7;
            img8=R.drawable.marioparte8;
            img9=R.drawable.marioparte9;
            //LIGAR IMAGENES CON LOS BOTONES
            b1.setBackgroundResource(img1);
            b2.setBackgroundResource(img2);
            b3.setBackgroundResource(img3);
            b4.setBackgroundResource(img4);
            b5.setBackgroundResource(img5);
            b6.setBackgroundResource(img6);
            b7.setBackgroundResource(img7);
            b8.setBackgroundResource(img8);
            b9.setBackgroundResource(img9);


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b1.getBackground();
                        Toast.makeText(Juego.this, "ENTRO!", Toast.LENGTH_SHORT).show();
                        buttonActual = b1;
                    }else{
                        imgAuxCam=b1.getBackground();
                        b1.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();//VERIFICAR SI TERMINO EL JUEGO
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b2.getBackground();
                        buttonActual = b2;
                    }else{
                        imgAuxCam=b2.getBackground();
                        b2.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();

                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b3.getBackground();
                        buttonActual = b3;
                    }else{
                        imgAuxCam=b3.getBackground();
                        b3.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }

            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b4.getBackground();
                        buttonActual = b4;
                    }else{
                        imgAuxCam=b4.getBackground();
                        b4.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b5.getBackground();
                        buttonActual = b5;
                    }else{
                        imgAuxCam=b5.getBackground();
                        b5.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b6.getBackground();
                        buttonActual = b6;
                    }else{
                        imgAuxCam=b6.getBackground();
                        b6.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b7.getBackground();
                        buttonActual = b7;
                    }else{
                        imgAuxCam=b7.getBackground();
                        b7.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b8.getBackground();
                        buttonActual = b8;
                    }else{
                        imgAuxCam=b8.getBackground();
                        b8.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=b9.getBackground();
                        buttonActual = b9;
                    }else{
                        imgAuxCam=b9.getBackground();
                        b9.setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                    }
                    seGano();
                }
            });
        }


}
