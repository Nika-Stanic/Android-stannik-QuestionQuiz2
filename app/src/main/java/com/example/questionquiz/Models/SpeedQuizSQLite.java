package com.example.questionquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLite extends SQLiteOpenHelper {

    //Nom de base de donnee
    static String DB_NAME = "QuestionQuiz.db";
    static int DB_VERSION = 1;

    /*Besoin pour faire fonctionner la BD, c'est les parametres qui tournent autour de l'app(Environnement, memoire, elements d'application externe)
    Pour construire la base de donnee
     */
    public SpeedQuizSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    /**
     * OnCreate va contenir tout ce qu'on veut executer au niveau de la base de donnee la premiere fois qu'on lance sur l'application
     * Si on relance l'application une 2eme fois, il va regarder sur OnUpgrade
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatabase = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY, question TEXT, response INTEGER);";
        db.execSQL(sqlCreateDatabase);
        db.execSQL("INSERT INTO quiz VALUES(1, \"la capitale de l'Australie est Sidney\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(2, \"Le capitale de la Suisse est Berne\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(3, \"1 octet vaut 7 bit\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(4, \"Le plus grand pays du monde est la Chine\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(5, \"Il est impossible d'éternuer en ouvrant les Yeux\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(6, \"La norme IEEE 802.11 correspond aux réseaux sans fil (WI-FI)\", 1)");

    }

    /**
     * Tout les modification on doit mettre la, il va aller detecter si il y a des nouveau version sur le numero de Version Code(Build gradle)
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
