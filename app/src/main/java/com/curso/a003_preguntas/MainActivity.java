package com.curso.a003_preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Pregunta[] preguntas;
    private Button btnVerdadero;
    private Button btnFalso;
    private Button btnBack;
    private Button btnForward;
    private TextView textViewPregunta;
    private TextView avisoView;
    private int idPreguntaActual=0;
    private int sumatorio=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnFalso = findViewById(R.id.btn_falso);
        this.btnVerdadero = findViewById(R.id.btn_verdadero);
        this.btnBack=findViewById(R.id.btnBack);
        this.btnBack.setEnabled(false);
        this.btnForward=findViewById(R.id.btnForward);
        this.textViewPregunta = findViewById(R.id.textViewPregunta);
        this.avisoView=findViewById(R.id.avisoView);

        this.btnVerdadero.setOnClickListener(this);
        this.btnFalso.setOnClickListener(this);



        preguntas = new Pregunta[]{
                new Pregunta(getResources().getString(R.string.pregunta1), false, getResources().getString(R.string.explicacion1)),
                new Pregunta(getResources().getString(R.string.pregunta2), false, getResources().getString(R.string.explicacion2)),
                new Pregunta("China es una democracia",false,  "En serio?????"),
                new Pregunta("El titanio es peor que el acero", false, "Mas maleable, mejor resistencia a la temperatura"),
                new Pregunta("Andorra es un pariso fiscal", false, "Tiene bajos impuestos y pertenece al FMI"),
        };
        cargarPregunta();
    }
    private void cargarPregunta(){
        this.textViewPregunta.setText(preguntas[idPreguntaActual].getPregunta());

    }
    @Override
    public void onClick(View v){
        Log.i("trivial","id boton es "+v.getId());
        boolean respuesta=false;
        switch(v.getId()){
            case R.id.btn_falso:
                respuesta=false;
                break;
            case R.id.btn_verdadero:
                respuesta=true;
        }
        String res;
        String expl="";
        if(preguntas[idPreguntaActual].isRespuesta()==respuesta){
            Log.i("trivial","acertó");
            res="Has acertado";
            sumatorio++;
        }else{
            Log.i("trivial","fallo");
            res="Fallastes";
            expl=preguntas[idPreguntaActual].getExplicacion();
        }
        this.avisoView.setText("ACIERTOS  "+(avisoView.getText().toString()));
        Intent intent=new Intent (this, Mensaje.class);
        intent.putExtra("dato",res);
        intent.putExtra("explicacion",expl);
        startActivityForResult(intent,1);
    }
    protected void onActivityResult(int requesCode, int resultCode,Intent data){
        super.onActivityResult(requesCode, resultCode, data);
        Log.d("en main","resultado "+resultCode);
        if (resultCode==RESULT_OK){
            int numero=data.getIntExtra("resultado",0);
            if(idPreguntaActual==this.preguntas.length-1){
                this.idPreguntaActual=0;
                this.sumatorio=0;
                this.btnForward.setEnabled(true);
            }else{
                this.idPreguntaActual++;
                this.btnBack.setEnabled(true);

            }
            cargarPregunta();
            Log.i("main","resultado es"+ numero);
        }
    }
    public void onClickBack(View v){
        if (idPreguntaActual==0){
            this.btnBack.setEnabled(false);
        }else{
            idPreguntaActual--;
            this.btnForward.setEnabled(true);
            cargarPregunta();
        }
    }
    public void onClickForward(View V){
        if (idPreguntaActual==preguntas.length-1){
            this.btnForward.setEnabled(false);

        }else{
            idPreguntaActual++;
            this.btnBack.setEnabled(true);
            cargarPregunta();
        }

    }


}