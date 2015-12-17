package com.hugo.alberto.jumper.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hugo.alberto.jumper.engine.Som;
import com.hugo.alberto.jumper.graphic.Cores;

/**
 * Created by Alberto on 16/05/2015.
 */
public class Pontuacao {

    private static final Paint BRANCA = Cores.getCorDaPontuacao();
    private int pontos = 0;
    private Som som;

    public Pontuacao( Som som){
        this.som = som;
    }

    public void desenhaNo(Canvas canvas){

       canvas.drawText(String.valueOf(pontos), 20, 80, BRANCA);// G3 80x150 default 50x80
    }

    public void aumenta() {
        pontos++;
        som.toca(Som.PONTOS);
    }

    public int getPontos() {
        return pontos;
    }
}
