package com.hugo.alberto.jumper.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.hugo.alberto.jumper.R;
import com.hugo.alberto.jumper.SaveScore;
import com.hugo.alberto.jumper.elements.Canos;
import com.hugo.alberto.jumper.elements.GameOver;
import com.hugo.alberto.jumper.elements.Passaro;
import com.hugo.alberto.jumper.elements.Pontuacao;
import com.hugo.alberto.jumper.graphic.Tela;


public class Game  extends SurfaceView implements Runnable, OnTouchListener {

    private Som som;
    public boolean isRunning= true;
    private SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;
    private Canos canos;
    private Pontuacao pontuacao;
    private Context context;


    public Game(Context context) {
            super(context);
            this.context=context;
        tela = new Tela(context);
        som = new Som(context);
        inicializaElementos();
        setOnTouchListener(this);
    }

    private void inicializaElementos() {
        
        passaro = new Passaro(tela, context, som);
        pontuacao = new Pontuacao(som);
        canos = new Canos(tela, pontuacao, context);

        //cano = new Cano(tela,400);//um cano
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(back, back.getWidth(),tela.getAltura(),false);
    }

    @Override
    public void run() {
        while(isRunning){
            if(!holder.getSurface().isValid()) continue;
            Canvas canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);
            passaro.desenhaNo(canvas);
            passaro.cai();

            canos.desenhaNo(canvas);
            canos.move();



            pontuacao.desenhaNo(canvas);

            if(new VerificadorDeColisao(passaro, canos).temColisao()){
                new GameOver(tela).desenhaNo(canvas);
                som.toca(Som.COLISAO);
                gameover();
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void gameover() {
        isRunning = false; // TO DO

        if (pontuacao.getPontos()>0) {
            Intent intent = new Intent(getContext(), SaveScore.class);
            Bundle b = new Bundle();
            b.putString("score", String.valueOf(pontuacao.getPontos()));
            intent.putExtras(b);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        ((Activity) context).finish();
    }

    public void inicia(){
        isRunning = true;
    }

    public void pausa(){
        isRunning = false;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(isRunning==true){
            passaro.pula();
        }
        return false;
    }
}
