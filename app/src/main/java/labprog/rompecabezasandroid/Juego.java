package labprog.rompecabezasandroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inicio();
    }


    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,buttonActual, buttonCambio;   // BOTONES AUXILIARES
    Drawable imgAuxAct,imgAuxCam;                                   // PARA EL INTERCAMBIO DE IMAGENES
    int turnos=0,posicionActual;                                    // TURNO ES EL CONTADOR DE PUNTOS
    Button[] arrayBotones= new Button[9];                           // ARREGLO DE BOTONES DEL JUEGO

    /**
     *  VERIFICA SI SE GANO EL JUEGO PARA FINALIZARLO, EN CASO CONTRARIO SE SUMA 1 A TURNO
     */
    public void seGano(){
        if (verificarGanador()){
            Toast.makeText(getApplicationContext(),"Feliciataciones, lo lograste en: "+turnos+" turnos.",Toast.LENGTH_SHORT).show();
            this.turnos = 0;
            //REDIRECCIONAR AL RANKING
            Intent toys;
            toys = new Intent(Juego.this , Ranking.class);
            startActivity(toys);
        }else{
            //SUMAR UN TURNO
            this.turnos++;
        }

    }

    /**
     *  CHEQUEO DEL JUEGO DE CADA BOTON, PARA SABER SI TERMINA
     */
    public boolean verificarGanador(){
        boolean termina = false;

        return termina;
    }

    /**
     * REINICIO DEL JUEGO
     */
    public void reset(View view){
            inicio();
        }

    /**
     *  METODO INICIAL DEL JUEGO
     */
    public void inicio(){

            //int random =1;// new Random().nextInt(1) + 9;
            turnos=0;
            setContentView(R.layout.activity_juego);
            String buttonID = "",imagenID="";
            int resID;

            for(int i=1; i<10;i++){
                //RECUPERACION DE LOS BOTONES
                buttonID = "b" + i;
                resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                arrayBotones[i-1]=(Button) findViewById(resID);
                //ESTABLECER IMAGENES
                imagenID="marioparte"+i;
                resID = getResources().getIdentifier(imagenID, "drawable", getPackageName());
                //LIGAR IMAGENES CON LOS BOTONES
                arrayBotones[i-1].setBackgroundResource(resID);
            }

            arrayBotones[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[0].getBackground();
                        buttonActual = arrayBotones[0];
                        posicionActual = 1;
                    }else{
                        if(esValido(1)) {
                            imgAuxCam = arrayBotones[0].getBackground();
                            arrayBotones[0].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();//VERIFICAR SI TERMINO EL JUEGO
                }
            });
            arrayBotones[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[1].getBackground();
                        buttonActual = arrayBotones[1];
                        posicionActual =2;
                    }else{
                        if(esValido(2)) {
                            imgAuxCam = arrayBotones[1].getBackground();
                            arrayBotones[1].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();

                }
            });
            arrayBotones[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[2].getBackground();
                        buttonActual = arrayBotones[2];
                        posicionActual=3;
                    }else{
                        if(esValido(3)) {
                            imgAuxCam = arrayBotones[2].getBackground();
                            arrayBotones[2].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();
                }

            });
            arrayBotones[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[3].getBackground();
                        buttonActual = arrayBotones[3];
                        posicionActual=4;
                    }else{
                        if(esValido(4)) {
                            imgAuxCam = arrayBotones[3].getBackground();
                            arrayBotones[3].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();
                }
            });
            arrayBotones[4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[4].getBackground();
                        buttonActual = arrayBotones[4];
                        posicionActual=5;
                    }else{
                        imgAuxCam=arrayBotones[4].getBackground();
                        arrayBotones[4].setBackgroundDrawable(imgAuxAct);
                        buttonActual.setBackgroundDrawable(imgAuxCam);
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                        posicionActual=0;
                    }
                    seGano();
                }
            });
            arrayBotones[5].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[5].getBackground();
                        buttonActual = arrayBotones[5];
                        posicionActual=6;
                    }else{
                        if(esValido(6)) {
                            imgAuxCam = arrayBotones[5].getBackground();
                            arrayBotones[5].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();
                }
            });
            arrayBotones[6].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[6].getBackground();
                        buttonActual = arrayBotones[6];
                        posicionActual=7;
                    }else{
                        if(esValido(7)) {
                            imgAuxCam = arrayBotones[6].getBackground();
                            arrayBotones[6].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();
                }
            });
            arrayBotones[7].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[7].getBackground();
                        buttonActual = arrayBotones[7];
                        posicionActual=8;
                    }else{
                        if(esValido(8)) {
                            imgAuxCam = arrayBotones[7].getBackground();
                            arrayBotones[7].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();
                }
            });
            arrayBotones[8].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){
                        imgAuxAct=arrayBotones[8].getBackground();
                        buttonActual = arrayBotones[8];
                        posicionActual=9;
                    }else{
                        if(esValido(9)) {
                            imgAuxCam = arrayBotones[8].getBackground();
                            arrayBotones[8].setBackgroundDrawable(imgAuxAct);
                            buttonActual.setBackgroundDrawable(imgAuxCam);
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                        }
                    }
                    seGano();
                }
            });
        }

    /**
     *  VERIRIFCACION DE MOVIVIEMTOS VALIDOS
     */
    public boolean esValido(int posicion){
            Boolean resultado=false;
            switch(posicion){
                case 1:{
                        if(posicionActual==2 || posicionActual==4 || posicionActual==5){resultado=true;}
                    break;
                }
                case 2:{
                        if(posicionActual==1 || posicionActual==3 || posicionActual==4 || posicionActual==5|| posicionActual==6){resultado=true;}
                    break;
                }
                case 3:{
                        if(posicionActual==2 || posicionActual==5 || posicionActual==6){resultado=true;}
                    break;
                }case 4:{
                        if(posicionActual==1 || posicionActual==2 || posicionActual==5 || posicionActual==7|| posicionActual==8){resultado=true;}
                    break;
                }case 6:{
                        if(posicionActual==3 || posicionActual==2 || posicionActual==5 || posicionActual==8|| posicionActual==9){resultado=true;}
                    break;
                }case 7:{
                        if(posicionActual==4 || posicionActual==5 || posicionActual==8){resultado=true;}
                    break;
                }case 8:{
                        if(posicionActual==7 || posicionActual==4 || posicionActual==5 || posicionActual==6|| posicionActual==9){resultado=true;}
                    break;
                }case 9:{
                        if(posicionActual==8 || posicionActual==5 || posicionActual==6){resultado=true;}
                    break;
                }
            }
            return resultado;
        }


}
