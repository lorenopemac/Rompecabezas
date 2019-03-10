package labprog.rompecabezasandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import entidades.ConexionSQLiteHelper;

public class Juego extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,buttonActual, buttonCambio;   // BOTONES AUXILIARES
    Drawable imgAuxAct,imgAuxCam;                                   // PARA EL INTERCAMBIO DE IMAGENES
    int turnos=0,posicionActual,idImgAux=0;                         // TURNO ES EL CONTADOR DE PUNTOS,idImgAux usado para conocer los id de las imagenes de cada boton
    Button[] arrayBotones= new Button[9];                           // ARREGLO DE BOTONES DEL JUEGO
    int[] arrayImageGanador= new int[9];                            // ARRAY CON EL ORDEN DE IDS DE IMAGENES GANADORAS
    int[] arrayImage = new int[9];                                  // ARRAY DE IDS DE LAS IMAGENES
    String tipo, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Bundle extras = getIntent().getExtras();
        tipo = extras.getString("tipo");
        username= extras.getString("nom");
        //Toast.makeText(getApplicationContext(),"! "+extrasValue,Toast.LENGTH_SHORT).show();
        inicio();// METODO PRINCIPAL
    }



    /**
     *  VERIFICA SI SE GANO EL JUEGO PARA FINALIZARLO, EN CASO CONTRARIO SE SUMA 1 A TURNO
     */
    public void seGano(){
        if (verificarGanador()){
            /*
            *   En caso de ganar el juego
            */
            Toast.makeText(getApplicationContext(),"Felicitaciones !!!, lo lograste en: "+turnos+" turnos.",Toast.LENGTH_SHORT).show();

            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdU",null,1);//CONECTAR A LA BD
            SQLiteDatabase db= conn.getWritableDatabase();
            Cursor fila=null;
            if(tipo.toString().equals("marioparte")){
                fila = db.rawQuery("select puntosM from usuario where nombre='"+username+"'",null);//BUSCAR PUNTOS PARA MARIO
            }else if(tipo.toString().equals("paloma")){
                fila = db.rawQuery("select puntosP from usuario where nombre='"+username+"'",null);//BUSCAR PUNTOS PARA PALOMA
            }

            fila.moveToFirst();// MOVERSE AL PRIMER PUNTERO

            if (fila.getInt(0)>this.turnos || fila.getInt(0)==0){
                // SI LOGRO GANAR CON UNA MENOR CANTIDAD DE PUNTOS
                if(tipo.toString().equals("marioparte")){// SI SE ESTA JUGANDO MARIO
                    db.execSQL("update usuario set puntosM= "+this.turnos+" WHERE nombre ='"+username+"'");// ACTUALIZO LA PUNTUACION DE MARIO
                }else if(tipo.toString().equals("paloma")){// SI SE ESTA JUGANDO PALOMA
                    db.execSQL("update usuario set puntosP= "+this.turnos+" WHERE nombre ='"+username+"'");// ACTUALIZO LA PUNTUACION DE MARIO
                }
                db.close();
                this.turnos = 0;

                //REDIRECCIONAR AL RANKING
                Intent toys;
                toys = new Intent(Juego.this , rankings.class);
                startActivity(toys);
            }
        }else{
            //SUMAR UN TURNO
            this.turnos++;
        }

    }

    /**
     *  CHEQUEO DEL JUEGO DE CADA BOTON, PARA SABER SI TERMINA
     */
    public boolean verificarGanador(){
        boolean termina = true;
        int k = 0;

        while(termina && k<9){
            if(arrayImageGanador[k] != arrayImage[k]){//la imagen no esta en su lugar
                termina = false;
            }
            k++;
        }
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
            arrayGanador();//SE ESTABLECEN LAS POSICIONES QUE DEBE TENER EL JUEGO PARA GANAR
            turnos=0;//INICIA CON 0 TURNOS JUGADOS
            setContentView(R.layout.activity_juego);
            String buttonID = "",imagenID="";
            int resID;

            /*
            *   SE LE ASIGNAN LAS IMAGENES A LOS BOTONES
            */
            for(int i=1; i<10;i++){
                //RECUPERACION DE LOS BOTONES
                buttonID = "b" + i;
                resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                arrayBotones[i-1]=(Button) findViewById(resID);
                //ESTABLECER IMAGENES
                imagenID=tipo+i;
                resID = getResources().getIdentifier(imagenID, "drawable", getPackageName());
                arrayImage[i-1]=resID; // guardado de los ids de las imagenes para la verificacion del ganador
                //LIGAR IMAGENES CON LOS BOTONES
                arrayBotones[i-1].setBackgroundResource(resID);
            }

            arrayBotones[0].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 1
                @Override
                public void onClick(View v) {
                    if(buttonActual == null){// SI NO SE HA SELECCIONADO NINGUN BOTON, ESTE SERA EL ACTUAL
                        imgAuxAct=arrayBotones[0].getBackground();//GUARDO LA IMAGEN EN UN VARIABLE
                        buttonActual = arrayBotones[0];//GUARDO LA REFERENCIA DEL BOTON
                        posicionActual = 1;// POSICION DEL BOTON ACTUAL
                    }else{// SI YA SE HA SELECCIONADO UN BOTON
                        if(esValido(1)) {// SE VALIDA QUE SE PUEDA REALIZAR EL MOVIMIENTO
                            imgAuxCam = arrayBotones[0].getBackground();// ALMACENO LA REFERENCIA DE LA IMAGEN DEL BOTON
                            arrayBotones[0].setBackgroundDrawable(imgAuxAct);// CAMBIO LA IMAGEN DEL BOTON A LA DEL BUTTONACTUAL
                            buttonActual.setBackgroundDrawable(imgAuxCam);// CAMBIO LA IMAGEN DEL BUTTONACTUAL A LA DE ESTE BOTON
                            //REESTABLECER LAS VARIABLES DE INTERCAMBIO
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[0];//SE ALAMACENA EL ID DE LA IMAGEN, LUEGO SE UTILIZA PARA VERIFICAR GANADOR
                            arrayImage[0]=idImgAux;
                            //IDIMAGENES
                            posicionActual = 0;
                            seGano();//VERIFICAR SI TERMINO EL JUEGO
                        }
                    }

                }
            });
            arrayBotones[1].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 2
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[1];
                            arrayImage[1]=idImgAux;
                            //IDIMAGENES
                            posicionActual = 0;
                            seGano();
                        }
                    }


                }
            });
            arrayBotones[2].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 2
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[2];
                            arrayImage[2]=idImgAux;
                            //IDIMAGENES
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                            seGano();
                        }
                    }

                }

            });
            arrayBotones[3].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 3
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[3];
                            arrayImage[3]=idImgAux;
                            //IDIMAGENES
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                            seGano();
                        }
                    }
                }
            });
            arrayBotones[4].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 4
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
                        // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                        idImgAux = arrayImage[posicionActual-1];
                        arrayImage[posicionActual-1]= arrayImage[4];
                        arrayImage[4]=idImgAux;
                        //IDIMAGENES
                        buttonActual=null;
                        imgAuxCam=null;
                        imgAuxAct=null;
                        posicionActual=0;
                        seGano();
                    }
                }
            });
            arrayBotones[5].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 5
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[5];
                            arrayImage[5]=idImgAux;
                            //IDIMAGENES
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                            seGano();
                        }
                    }
                }
            });
            arrayBotones[6].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 6
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[6];
                            arrayImage[6]=idImgAux;
                            //IDIMAGENES
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                            seGano();
                        }
                    }
                }
            });
            arrayBotones[7].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 7
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[7];
                            arrayImage[7]=idImgAux;
                            //IDIMAGENES
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                            seGano();
                        }
                    }
                }
            });
            arrayBotones[8].setOnClickListener(new View.OnClickListener() {// SI SE PRESIONA EL BOTON EN LA POSICION 8
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
                            // PARA ALMACENAR EL ID DE LAS IMAGENES Y VERIFICAR AL FINAL
                            idImgAux = arrayImage[posicionActual-1];
                            arrayImage[posicionActual-1]= arrayImage[8];
                            arrayImage[8]=idImgAux;
                            //IDIMAGENES
                            buttonActual = null;
                            imgAuxCam = null;
                            imgAuxAct = null;
                            posicionActual = 0;
                            seGano();
                        }
                    }
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

    /**
     * ARMADO DE ARRAY CON IDS DE IMAGENES PARA SABER CUANDO SE TERMINA EL JUEGO
     */
    public void arrayGanador(){
        if(tipo.toString().equals("marioparte")){
            arrayImageGanador[0]=getResources().getIdentifier("marioparte1", "drawable", getPackageName());
            arrayImageGanador[1]=getResources().getIdentifier("marioparte4", "drawable", getPackageName());
            arrayImageGanador[2]=getResources().getIdentifier("marioparte7", "drawable", getPackageName());
            arrayImageGanador[3]=getResources().getIdentifier("marioparte2", "drawable", getPackageName());
            arrayImageGanador[4]=getResources().getIdentifier("marioparte5", "drawable", getPackageName());
            arrayImageGanador[5]=getResources().getIdentifier("marioparte8", "drawable", getPackageName());
            arrayImageGanador[6]=getResources().getIdentifier("marioparte3", "drawable", getPackageName());
            arrayImageGanador[7]=getResources().getIdentifier("marioparte6", "drawable", getPackageName());
            arrayImageGanador[8]=getResources().getIdentifier("marioparte9", "drawable", getPackageName());
        }
        if(tipo.toString().equals("paloma")){
            arrayImageGanador[0]=getResources().getIdentifier("paloma8", "drawable", getPackageName());
            arrayImageGanador[1]=getResources().getIdentifier("paloma9", "drawable", getPackageName());
            arrayImageGanador[2]=getResources().getIdentifier("paloma3", "drawable", getPackageName());
            arrayImageGanador[3]=getResources().getIdentifier("paloma6", "drawable", getPackageName());
            arrayImageGanador[4]=getResources().getIdentifier("paloma7", "drawable", getPackageName());
            arrayImageGanador[5]=getResources().getIdentifier("paloma4", "drawable", getPackageName());
            arrayImageGanador[6]=getResources().getIdentifier("paloma2", "drawable", getPackageName());
            arrayImageGanador[7]=getResources().getIdentifier("paloma1", "drawable", getPackageName());
            arrayImageGanador[8]=getResources().getIdentifier("paloma5", "drawable", getPackageName());
        }
    }

}
