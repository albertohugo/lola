package com.hugo.alberto.jumper.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hugo.alberto.jumper.R;
import com.hugo.alberto.jumper.modelo.Score;

import java.util.Collections;
import java.util.List;

/**
 * Created by Alberto on 23/05/2015.
 */
public class ListaScoresAdapter extends BaseAdapter {
    private List<Score> scores;
    private Activity activity;

    public ListaScoresAdapter(List<Score> scores, Activity activity) {
        this.scores = scores;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        Score score = scores.get(position);
        return score.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Score score = scores.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.score_row, null);

        TextView nome = (TextView) linha.findViewById(R.id.rowTextView);
        nome.setText(score.getNome() + " - " + score.getScore());

        return linha;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
