package com.example.questionquiz;

import android.database.Cursor;


/**
 * Classe qui represente la question
 */
public class Question {
    private String question;
    private int reponse;


    public Question(String question, int reponse) {
        this.question = question;
        this.reponse = reponse;

    }

    public Question(Cursor cursor) {
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));

    }

    public int IsReponseOui() {
        if (reponse == 1) {
            return 1;
        } else {
            return 0;
        }
    }


}
