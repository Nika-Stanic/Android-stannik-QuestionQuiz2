package com.example.questionquiz.Models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.questionquiz.About;
import com.example.questionquiz.R;
import com.example.questionquiz.jeu;

/**
 * Classe qui represente le Main
 */
public class MainActivity extends AppCompatActivity {
    private Button BT_jouer;
    private EditText ET_question1;
    private EditText ET_question2;

    /**
     * L'initialisation de la fenetre principale et recuperer les objets
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //stocker les variables
        BT_jouer = findViewById(R.id.outlinedButton);
        ET_question1 = findViewById(R.id.main_layout_j1);
        ET_question2 = findViewById(R.id.main_layout_j2);
    }

    /**
     * Creation du Menu
     *
     * @param menu menu passer en parametres
     * @return le Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.parametres, menu);
        return true;
    }

    /**
     * Choix de l'option du menu
     *
     * @param item L'option du menu
     * @return l'option choisi
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent quiz = new Intent(MainActivity.this, About.class);
                startActivity(quiz);

            case R.id.settings:


        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Code executer apres le chargements de la fenetres
     */
    @Override
    protected void onStart() {
        super.onStart();

        ET_question1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkData();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ET_question2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkData();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        BT_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quiz = new Intent(MainActivity.this, jeu.class);
                startActivity(quiz);
            }
        });

    }

    /**
     * Check si les deux champs de saisie sont rempli
     */
    private void checkData() {
        if (ET_question1.getText().toString().isEmpty() && ET_question2.getText().toString().isEmpty()) {
            BT_jouer.setEnabled(false);

        } else {
            BT_jouer.setEnabled(true);
        }
    }
}