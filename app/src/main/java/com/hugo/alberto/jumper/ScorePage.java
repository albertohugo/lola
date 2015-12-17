package com.hugo.alberto.jumper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hugo.alberto.jumper.adapter.ListaScoresAdapter;
import com.hugo.alberto.jumper.dao.ScoreDAO;
import com.hugo.alberto.jumper.modelo.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alberto on 23/05/2015.
 */
public class ScorePage extends Activity {

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

    private ListView lista;
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ScoreDAO dao = new ScoreDAO(this);
        List<Score> scores = dao.getLista();
        dao.close();

        ListaScoresAdapter adapter = new ListaScoresAdapter(scores, this);

        lista = (ListView) findViewById(R.id.scoreListView);

        registerForContextMenu(lista);
        lista.setAdapter(adapter);


    }

}
