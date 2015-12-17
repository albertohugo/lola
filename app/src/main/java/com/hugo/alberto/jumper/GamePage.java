package com.hugo.alberto.jumper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.hugo.alberto.jumper.elements.Pontuacao;
import com.hugo.alberto.jumper.engine.Game;


public class GamePage extends Activity {

    private Game game;
    private EditText nome_score;
    private Pontuacao pontuacao;
    private ImageButton pause;
    private boolean paused= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FrameLayout container = (FrameLayout) findViewById(R.id.container);

        game =  new Game(this);
        container.addView(game);
        addListenerOnButtonPause();

    }

    private void addListenerOnButtonPause() {
        pause = (ImageButton) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(game.isRunning=true && paused ==false){
                    //Toast.makeText(getBaseContext(),"Pause", Toast.LENGTH_SHORT).show();
                    onPause();
                    paused=true;
                    return;
                }
                if(paused==true) {
                    //Toast.makeText(getBaseContext(),"Run", Toast.LENGTH_SHORT).show();
                    onResume();
                    paused=false;
                    return;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.inicia();
        new Thread(game).start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        game.pausa();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
