package com.hugo.alberto.jumper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.hugo.alberto.jumper.dao.ScoreDAO;
import com.hugo.alberto.jumper.modelo.Score;

/**
 * Created by Alberto on 24/05/2015.
 */
public class SaveScore extends Activity {
    private ScoreHelper helper;
    private Score score;
    ImageButton save;
    ImageButton cancel;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_score);
        addListenerOnButtonSave();
        addListenerOnButtonCancel();

        Intent intent = getIntent();
        final Score scoreParaSerAlterado = (Score) intent.getSerializableExtra("scoreSelecionado");

        helper = new ScoreHelper(this);

        Bundle b = getIntent().getExtras();
        if(b!=null) {
            value = b.getString("score");
        }
    }

    private void addListenerOnButtonCancel() {
        cancel = (ImageButton) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    private void addListenerOnButtonSave() {
        save = (ImageButton) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Score score = helper.pegaScoreDoFormulario(Long.valueOf(value));
                ScoreDAO dao = new ScoreDAO(getApplicationContext());

                dao.salva(score);
                dao.close();
                finish();

            }
        });
    }
}
