package com.example.examenpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Heroes extends AppCompatActivity {

    RequestQueue requestQueue;
    String busqueda = "";
    private static String url_adress = "https://superheroapi.com/api/";
    private String token = "3274762752579397/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        requestQueue = Volley.newRequestQueue(this);
        Intent principal = getIntent();
        this.busqueda = (String) principal.getExtras().get("id");
        buscandoheroes(busqueda);
    }

    public void buscandoheroes(String busqueda){
        String url_Busqueda = url_adress+token+"search/"+busqueda;
        final TextView txtResultados = (TextView) findViewById(R.id.txtViewResultados);
        Intent listado_heroes = new Intent(getBaseContext(),Heroes.class);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_Busqueda, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    txtResultados.setText(String.valueOf(response.getJSONArray("results").length()));
                    LinearLayout contHeroes = (LinearLayout) findViewById(R.id.listaHeroes);
                    System.out.println(response.getJSONArray("results"));
                    for (int i=0; i<response.getJSONArray("results").length();i++){
                        TextView nombreHeroes = new TextView(getApplicationContext());
                        final JSONObject jsonObject = (JSONObject) response.getJSONArray("results").get(i);
                        nombreHeroes.setTextSize(24);
                        System.out.println(jsonObject.getString("name").toString());
                        final String id = jsonObject.getString("id");
                        nombreHeroes.setText(jsonObject.getString("name").toString());
                        contHeroes.addView(nombreHeroes);
                        nombreHeroes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String url_power = url_adress + token + "/" + id + "/powerstats";
                                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url_power, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                            try {
                                                HashMap<String,String> mapeo_info =  new HashMap<String, String>();
                                                mapeo_info.put("name",(response.getString("name")));
                                                mapeo_info.put("intelligence",(response.getString("intelligence")));
                                                mapeo_info.put("strength",(response.getString("strength")));
                                                mapeo_info.put("speed",(response.getString("speed")));
                                                mapeo_info.put("durability",(response.getString("durability")));
                                                mapeo_info.put("power",(response.getString("power")));
                                                mapeo_info.put("combat",(response.getString("combat")));
                                                Intent informa = new Intent(Heroes.this, Informacion.class);
                                                informa.putExtra("map_info",mapeo_info);
                                                startActivity(informa);
                                                System.out.println(mapeo_info);

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, null);
                                    requestQueue.add(request2);

                                } finally {

                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        requestQueue.add(request);
    }
}