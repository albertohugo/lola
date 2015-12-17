package com.hugo.alberto.jumper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.hugo.alberto.jumper.graphic.Tela;

/**
 * Created by Alberto on 23/05/2015.
 */
public class MainPage extends Activity {

    ImageButton play;
    ImageButton score;
    private Bitmap background;
    private Tela tela;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonPlay();
        addListenerOnButtonScore();
    }



    private void addListenerOnButtonPlay() {
        play = (ImageButton) findViewById(R.id.imagePlay);
        play.setImageResource(R.drawable.play);

        play.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    play.setImageResource(R.drawable.play_clicked);
                    return true;
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){

                    Intent intent = new Intent
                            (getApplicationContext(), GamePage.class);
                    startActivity(intent);
                    play.setImageResource(R.drawable.play);
                    return true;
                }
                else
                    return false;



            }
        });


        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent
                        (getApplicationContext(), GamePage.class);
                startActivity(intent);
            }
        });
    }
    private void addListenerOnButtonScore() {
        score = (ImageButton) findViewById(R.id.imageScore);
        score.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent
                        (getApplicationContext(), ScorePage.class);
                startActivity(intent);
            }
        });
    }


}
