package com.example.volleyvoyage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListerActivity extends AppCompatActivity {

    ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister);
        liste=(ListView) findViewById(R.id.liste);
        lister();
    }


    public void lister() {

        final ArrayList<HashMap<String, Object>> tabVoyages = new ArrayList<HashMap<String, Object>>();


        // String url = "http://10.103.41.165/livresControleur_Android/PHP/livresControleurJSON.php";
        //String url = "http://10.0.2.2:80/livresControleur_Android/PHP/livresControleurJSON.php";
        String url = "http://10.0.2.2:80/VolleyVoyagePHP/voyagesController.php";

        StringRequest requete = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("RESULTAT", response);
                            int i,j;
                            JSONArray jsonResponse = new JSONArray(response);
                            HashMap<String, Object> map;
                            //String msg = jsonResponse.getString(0);
                            // if(msg.equals("OK")){
                            JSONObject unVoyage;
                            for(i=0;i<jsonResponse.length();i++){
                                unVoyage=jsonResponse.getJSONObject(i);
                                map= new HashMap<String, Object>();

                                map.put("mcode", unVoyage.getString("code"));
                                map.put("mhdepart", unVoyage.getString("hdepart"));
                                map.put("mdepart", unVoyage.getString("depart"));
                                map.put("mdestination", unVoyage.getString("destination"));
                                map.put("mtransporteur", unVoyage.getString("transporteur"));
                                map.put("mprix", unVoyage.getString("prix"));
                                tabVoyages.add(map);
                            }

                            SimpleAdapter monAdapter = new SimpleAdapter (ListerActivity.this, tabVoyages, R.layout.lister_voyage_map,
                                    new String[] {"mcode", "mhdepart", "mdepart", "mdestination","mtransporteur", "mprix" },
                                    new int[] {R.id.mcode, R.id.mhdepart, R.id.mdepart, R.id.mdestination, R.id.mtransporteur, R.id.mprix});

                            liste.setAdapter(monAdapter);
                            // }
                            //    else{}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ListerActivity.this, "ERREUR ="+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // Les parametres pour POST
                params.put("action", "lister");
                return params;
            }
        };
        //Volley.newRequestQueue(this).add(requete);//Si Volley rouge clique Volley et choisir add dependency on module volley
        MySingleton.getInstance(ListerActivity.this).addToRequestQue(requete);
    }
}
