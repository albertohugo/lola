package com.hugo.alberto.jumper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hugo.alberto.jumper.modelo.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alberto on 23/05/2015.
 */
public class ScoreDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "Score";
    private static final int VERSAO = 1;

    public ScoreDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void salva(Score score) {
        ContentValues values = new ContentValues();

        values.put("nome", score.getNome());
        values.put("score", score.getScore());

        getWritableDatabase().insert("Scores", null, values);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE Scores (id INTEGER PRIMARY KEY, nome TEXT UNIQUE NOT NULL, Score INTEGER NOT NULL);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS Scores";
        db.execSQL(ddl);
        onCreate(db);
    }

    public List<Score> getLista() {
        String[] colunas = { "id", "nome", "score" };
            Cursor cursor = getWritableDatabase().query("Scores", colunas, null,
                null, null, null, "score DESC"+ " LIMIT 10");

        ArrayList<Score> scores = new ArrayList<Score>();
        while (cursor.moveToNext()) {
            Score score = new Score();

            score.setId(cursor.getLong(0));
            score.setNome(cursor.getString(1));
            score.setScore(cursor.getLong(2));

            scores.add(score);
        }

        return scores;
    }

    public void deletar(Score score) {
        String[] args = { score.getId().toString() };
        getWritableDatabase().delete("Scores", "id=?", args);
    }

 /*  public void altera(Score score) {
		ContentValues values = new ContentValues();

		values.put("nome", score.getNome());
		values.put("score", score.getScore());


		String[] args= {score.getId().toString()};
		getWritableDatabase().update("Scores", values, "id=?", args);
	}

    public boolean isScore(String score) {
        String[] args = {score};
        Cursor  cursor = getWritableDatabase().rawQuery("SELECT id FROM Alunos WHERE score=?", args);
        boolean existeUmPrimeiroAluno = cursor.moveToFirst();
        return existeUmPrimeiroScore;
    }*/
}
