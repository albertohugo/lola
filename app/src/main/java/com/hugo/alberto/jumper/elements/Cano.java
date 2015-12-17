package com.hugo.alberto.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.hugo.alberto.jumper.R;
import com.hugo.alberto.jumper.graphic.Cores;
import com.hugo.alberto.jumper.graphic.Tela;

/**
 * Created by Alberto on 15/05/2015.
 */
public class Cano {

    private Tela tela;

    private int posicao;
    private int alturaDoCanoInferior;
    private int TAMANHO_DO_CANO = 150; //default 150 g3 350
    private int LARGURA_DO_CANO = 100; //default 100 g3 250
    private int alturaDoCanoSuperior;
    private  Bitmap canoInferior;
    private Bitmap canoSuperior;

    int velocidade = 5;

    private static final Paint VERDE = Cores.getCorDoCano();


    public Cano (Tela tela, int posicao, Context context){
        if(tela.getAltura()>2000){
            TAMANHO_DO_CANO=TAMANHO_DO_CANO*2;
            LARGURA_DO_CANO = LARGURA_DO_CANO*2;
        }
        this.tela = tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior = tela.getAltura()- TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false);
        canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }

    private int valorAleatorio() {
        if(tela.getAltura()>=2000){
            return (int) (Math.random() * 700);
        }
        if(tela.getAltura()>1000&&tela.getAltura()<2000){
            return (int) (Math.random() * 500);
        }
        else {
            return (int) (Math.random() * 100);
        }
    }//default 100 g3 600


    public void desenhaNo(Canvas canvas){
        desenhaCanoSuperior(canvas);
        desenhaCanoInferior(canvas);
    }

    private void desenhaCanoSuperior(Canvas canvas) {
        //canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, VERDE);
        canvas.drawBitmap(canoSuperior,posicao, 0,null);
    }

    private void desenhaCanoInferior(Canvas canvas) {
        //canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE);
        canvas.drawBitmap(canoInferior,posicao, alturaDoCanoInferior,null);
    }

    public void move(int count) {

        this.posicao -=velocidade+count; //VELOCIDADE

    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao < passaro.X + passaro.RAIO;
    }

    public boolean temColisaVerticalCom(Passaro passaro) {
        return passaro.getAltura() - passaro.RAIO  < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }
}
