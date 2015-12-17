package com.hugo.alberto.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.hugo.alberto.jumper.R;
import com.hugo.alberto.jumper.engine.Som;
import com.hugo.alberto.jumper.graphic.Cores;
import com.hugo.alberto.jumper.graphic.Tela;

/**
 * Created by Alberto on 13/05/2015.
 */
public class Passaro {
    public static final float X = 150; //default 100 g3 250
    public int RAIO = 50; //default 50 g3 100
    private static final Paint VERMELHO = Cores.getCorDoPassaro();
    private Bitmap passaro;
    private Tela tela;
    private Som som;
    private float altura;

    public Passaro(Tela tela, Context context, Som som){

        if(tela.getAltura()>2000){
            RAIO=RAIO*2;
        }
        this.tela = tela;
        this.som = som;
        this.altura = 100; //default 100 g3 200
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.baiacu);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO*2,RAIO*2, false);
    }

    public void desenhaNo(Canvas canvas){
        //canvas.drawCircle(X,altura,RAIO, VERMELHO);
        canvas.drawBitmap(passaro,X - RAIO, altura - RAIO, null);
    }

    public void cai() {
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        if(!chegouNoChao) {
            this.altura += 5; //default 5
        }

    }

    public void pula() {
        if(altura - RAIO >0) {
            som.toca(Som.PULO);
            this.altura -= 100; //deafult 100 g3 100
        }
    }

    public float getAltura() {
        return this.altura;
    }
}
