package com.example.questionquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionquiz.Models.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui represente le Jeu
 */
public class jeu extends AppCompatActivity {
    private TextView question1;
    private TextView question2;
    private TextView nbrePts1;
    private TextView nbrePts2;
    private Button bt_vrai;
    private Button bt_faux;
    private TextView timer;

    Runnable questionRunnable = null;
    Handler handler;

    int tourJoueur = 0;

    int nbrePoint1 = 0;
    int nbrePoint2 = 0;

    QuestionManager quManager = new QuestionManager(this);

    //Va chercher la liste des questions
    List<Question> questionListe = quManager.getQuestionList();

    /**
     * Va chercher les objets et les stocker en variables
     *
     * @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);

        //on stocke les variables
        question1 = findViewById(R.id.text_viewQuestion_id);
        nbrePts1 = findViewById(R.id.nbre_point_id);
        bt_vrai = findViewById(R.id.ajoutPoint);
        bt_faux = findViewById(R.id.enleverPoint_id);
        question2 = findViewById(R.id.question_2id);
        nbrePts2 = findViewById(R.id.nbrepoint2_id);
        timer = findViewById(R.id.timer);
    }

    /**
     * Au démarrage du programme
     */
    @Override
    protected void onStart() {
        super.onStart();

        //Le compteur commence
        startCountDownTimer();

        bt_vrai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReponse(true);
            }
        });

        bt_faux.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReponse(false);
            }
        });
    }

    /**
     * Check si la reponse est vrai ou pas, si oui on gagne 1 points
     * On check aussi si c'est le Joueur 1 ou le Joueur 2
     *
     * @param reponse reponse donner par l'utilisateur (vrai ou faux)
     */
    private void checkReponse(boolean reponse) {
        boolean reponseJuste = questionListe.contains(1);

        //si la reponse est juste et que sa soit le joueur 1 qui a jouer, on lui attribue 1 points
        if (reponse == reponseJuste && tourJoueur == 1) {
            nbrePoint1++;
            nbrePts1.setText(nbrePoint1);
            // si c'est la joueur 2 qui a mis juste, on lui attribue 1 points
        } else if (reponse == reponseJuste && tourJoueur == 2) {
            nbrePoint2++;
            nbrePts2.setText(nbrePoint2);
        }
    }


    /**
     * Test qui a gagner en fonction du nombre de points
     *
     * @param nbrePoint1
     * @param nbrePoint2
     */
    private void testerGagnant(int nbrePoint1, int nbrePoint2) {
        if (nbrePoint1 > nbrePoint2) {
            question1.setText("Joueur 1 a gagner");
        } else if (nbrePoint2 > nbrePoint1) {
            question2.setText("Joueur 2 a gagner");
        }
    }

    /**
     * Déclenche la recherche de question/reponse
     */
    private void startQuestionIterative() {
        handler = new Handler();

        questionRunnable = new Runnable() {
            @Override
            public void run() {
                //va parcourir la liste des questions
                for (int i = 0; i <= questionListe.size(); i++) {
                    questionSuivant(i);
                }
                handler.postDelayed(this, 2000);

                //a la fin du jeu, on teste qui est le gagnant
                testerGagnant(nbrePoint1, nbrePoint2);

                //On retourne a la fenetre principale
                Intent retour = new Intent(jeu.this, MainActivity.class);
                startActivity(retour);
            }
        };

        handler.postDelayed(questionRunnable, 1000);
    }


    /**
     * Chronomètre
     */
    private void startCountDownTimer() {
        //temps de depart / valeur de décrement
        new CountDownTimer(6000, 1000) {
            public void onTick(long milisUntilFinished) {
                //Chronometre de départ
                milisUntilFinished = milisUntilFinished / 1000;
                timer.setText(Math.toIntExact(milisUntilFinished));
            }

            public void onFinish() {
                //Affiche les questions
                timer.setText("Le jeu a commencer ! ");
                startQuestionIterative();
            }
        }.start();
    }

    /**
     * On passe a la question suivante tant que l'index ne parcourt pas toute la liste des questions
     */
    private void questionSuivant(int questionIndex) {
        switch (questionIndex) {
            case 1:
                question1.setText("");
                question2.setText((CharSequence) questionListe);
                tourJoueur = 1;
            case 2:
                question2.setText((CharSequence) questionListe);
                question1.setText("");
                tourJoueur = 2;
            case 3:
                question1.setText("");
                question2.setText((CharSequence) questionListe);
                tourJoueur = 1;
            case 4:
                question2.setText((CharSequence) questionListe);
                question1.setText("");
                tourJoueur = 2;
            case 5:
                question1.setText("");
                question2.setText((CharSequence) questionListe);
                tourJoueur = 1;
            case 6:
                question2.setText((CharSequence) questionListe);
                question1.setText("");
                tourJoueur = 2;
        }
    }

}
