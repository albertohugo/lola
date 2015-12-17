package com.hugo.alberto.jumper.elements;

import android.content.Context;
import android.graphics.Canvas;

import com.hugo.alberto.jumper.graphic.Tela;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Alberto on 15/05/2015.
 */
public class Canos {

    private final List<Cano> canos = new ArrayList<Cano>();
    private int DISTANCIA_ENTRE_CANOS = 300; //g3 600 default 300
    private static final int QUANTIDADE_DE_CANOS = 5;
    private Context context;
    private Tela tela;
    private Pontuacao pontuacao;
    int count = 1;
    int num_canos=3;

    public Canos(Tela tela, Pontuacao pontuacao, Context context){
        if(tela.getAltura()>2000){
            DISTANCIA_ENTRE_CANOS=DISTANCIA_ENTRE_CANOS *2;
        }
        this.tela = tela;
        this.pontuacao = pontuacao;
        this.context = context;
        int posicao = 900; // g3 900

        for(int i =0; i<QUANTIDADE_DE_CANOS; i++){
            posicao += DISTANCIA_ENTRE_CANOS;
            Cano cano = new Cano(tela,posicao, context);
            canos.add(cano);
        }

    }

    public void desenhaNo(Canvas canvas) {
        for(Cano cano : canos){
            cano.desenhaNo(canvas);
        }
    }

    public void move() {
        ListIterator<Cano> iterator = canos.listIterator();
        while(iterator.hasNext()){
            Cano cano = iterator.next();

            if (pontuacao.getPontos()>num_canos){
                count = count+1;
                num_canos = num_canos+1;
            }


            cano.move(count);

                if(cano.saiuDaTela()){
                    pontuacao.aumenta();
                    iterator.remove();
                    Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context);
                    iterator.add(outroCano);
                }
        }
    }

    private int getMaximo() {
        int maximo = 0;
        for(Cano cano : canos ){
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }

    public boolean temColisaoCom(Passaro passaro) {
        for(Cano cano : canos){
            if(cano.temColisaoHorizontalCom(passaro)
                    &&cano.temColisaVerticalCom(passaro)){
                return true;
            }
        }
        return false;
    }
}
