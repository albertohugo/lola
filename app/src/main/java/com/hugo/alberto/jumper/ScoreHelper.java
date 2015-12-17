package com.hugo.alberto.jumper;

import android.os.Bundle;
import android.widget.EditText;

import com.hugo.alberto.jumper.elements.Pontuacao;
import com.hugo.alberto.jumper.modelo.Score;

/**
 * Created by Alberto on 23/05/2015.
 */
public class ScoreHelper {
    private EditText editNome;
  // private EditText editScore;
    private Score score;
  //  private Pontuacao pontuacao;

    public ScoreHelper(SaveScore saveScore) {
        editNome = (EditText) saveScore.findViewById(R.id.nome);
      //  editScore = (EditText) saveScore.findViewById(R.id.score);
        score = new Score();
    }


    public Score pegaScoreDoFormulario(Long sScore) {
        score.setNome(editNome.getText().toString());
        score.setScore(sScore);
       // score.setScore(editScore.getText().toString());
        return score;

    }



}
