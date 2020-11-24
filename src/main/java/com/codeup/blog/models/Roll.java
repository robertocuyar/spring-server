package com.codeup.blog.models;

public class Roll {
    private String guess;
    private int actual;

    public Roll (String guess, int actual){
        this.guess = guess;
        this.actual = actual;
    }

    public String getGuess(){return guess; }

    public int getActual(){return actual; }

    public void setGuess(String guess) {
        this.guess = guess;
    }
    public void setActual(int actual){
        this.actual = actual;
    }
}
