package com.example.questionquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.questionquiz.Models.SpeedQuizSQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui represente le question manager
 */
public class QuestionManager extends ArrayList<String> {

    private List<Question> questionList;

    public QuestionManager(Context context) {
        questionList = initQuestionList(context);
    }


    /**
     * Charge une lsite de question depuis la DB
     * PARAM : la contexte de lâpplication pour passer la query
     * Return une arraylist charger de question
     */
    private List<Question> initQuestionList(Context context) {
        //permet de creer une arraylist de question
        ArrayList<Question> initQuestion = new ArrayList<>();

        //on va chercher la base de donnee
        SpeedQuizSQLite helper = new SpeedQuizSQLite(context);

        //ouvrir le canal de connexion, accès en lecture
        SQLiteDatabase db = helper.getReadableDatabase();

        // Les curseur c'est ce qui permet de creer des listes en memoires,
        Cursor cursor = db.query(true, "quiz", new String[]{"idQuiz", "question", "reponse"}, null, null, null, null, "idQuiz", null);

        while (cursor.moveToNext()) {
            initQuestion.add(new Question(cursor));
        }

        cursor.close();
        db.close();

        return initQuestion;


    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
