package com.example.volleyvoyage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EnregistrerActivity extends AppCompatActivity implements View.OnClickListener{
    Button benreg;
    EditText et_hdepart, et_depart, et_destination, et_transporteur, et_prix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer);

        et_hdepart = (EditText) findViewById(R.id.hdepart);
        et_depart = (EditText) findViewById(R.id.depart);
        et_destination = (EditText) findViewById(R.id.destination);
        et_transporteur = (EditText) findViewById(R.id.transporteur);
        et_prix = (EditText) findViewById(R.id.prix);

        ajouterEvents();
    }

    public void ajouterEvents(){
        benreg = (Button) findViewById(R.id.enregistrer);
        benreg.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enregistrer:
                requeteEnregistrer();
                break;
        }
    }

    public void requeteEnregistrer() {

        String url = "http://10.0.2.2:80/VolleyVoyagePHP/voyagesController.php";

        //Recuperer les donnees
        final String hdepart = et_hdepart.getText().toString();
        final String depart = et_depart.getText().toString();
        final String destination = et_destination.getText().toString();
        final String tranporteur = et_transporteur.getText().toString();
        final String prix = et_prix.getText().toString();


        if (!hdepart.equals("") && !depart.equals("") && !destination.equals("")) {
            //Envoyer données au serveur
            StringRequest requete = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("RESULTAT", response);
                                JSONObject jsonResponse = new JSONObject(response);
                                String etat = jsonResponse.getString("etat");
                                String msg = jsonResponse.getString("msg");
                                Log.d("RESULTAT2", etat);
                                if (etat.equals("OK")) {
                                    Toast.makeText(EnregistrerActivity.this, msg, Toast.LENGTH_SHORT).show();
                                    clearText();
                                } else
                                    Toast.makeText(EnregistrerActivity.this, "Probleme pour enregistrer", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EnregistrerActivity.this, "ERREUR", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {


                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    // Les parametres pour POST
                    params.put("action", "enregistrer");
                    params.put("hdepart", hdepart);
                    params.put("depart", depart);
                    params.put("destination", destination);
                    params.put("transporteur", tranporteur);
                    params.put("prix", prix);
                    return params;
                }
            };
            MySingleton.getInstance(EnregistrerActivity.this).addToRequestQue(requete);//Si Volley rouge clique Volley et choisir add dependency on module volley
        }//fin Enregistrer
        else{
            Toast.makeText(EnregistrerActivity.this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    /*
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //Obtenir l'image de la gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Placer image dans ImageView
                //image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    private void  clearText(){
        et_hdepart.setText("");
        et_depart.setText("");
        et_destination.setText("");
        et_transporteur.setText("");
        et_prix.setText("");
    }

}
