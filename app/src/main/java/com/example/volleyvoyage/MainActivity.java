package com.example.volleyvoyage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button benreg,blister,bmodifier,benlever,brechercher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajouterEvents();
    }

    public void ajouterEvents(){
        benreg = (Button) findViewById(R.id.benreg);
        benreg.setOnClickListener(this);
        blister = (Button) findViewById(R.id.blister);
        blister.setOnClickListener(this);
        bmodifier = (Button) findViewById(R.id.bmodifier);
        bmodifier.setOnClickListener(this);
        benlever = (Button) findViewById(R.id.benlever);
        benlever.setOnClickListener(this);
        brechercher = findViewById(R.id.brechercher);
        brechercher.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.benreg:
                enregistrer();
                break;
            case R.id.blister:
                lister();
                break;
            case R.id.bmodifier:
                modifier();
                break;
            case R.id.benlever:
                enlever();
                break;
            case R.id.brechercher:
                rechecher();
                break;
        }
    }

    public void enregistrer(){
        Intent IntEnreg = new Intent(MainActivity.this, EnregistrerActivity.class);
        startActivity(IntEnreg);
    }
    public void lister(){
        Intent IntLister = new Intent(MainActivity.this, ListerActivity.class);
        startActivity(IntLister);
    }
    public void modifier(){
        //Intent IntModif = new Intent(MainActivity.this, ModifierActivity.class);
       // startActivity(IntModif);
    }
    public void enlever(){
        //Intent IntEnlever = new Intent(MainActivity.this, EnleverActivity.class);
       // startActivity(IntEnlever);
    }

    public void rechecher(){
       // Intent IntRechercher = new Intent(MainActivity.this, RechercherActivity.class);
        //startActivity(IntRechercher);
    }

}
