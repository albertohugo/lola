package com.hugo.alberto.jumper.modelo;

import java.io.Serializable;

/**
 * Created by Alberto on 23/05/2015.
 */
public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private long score;

    @Override
    public String toString() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }



}
