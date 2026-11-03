package com.lgarcia.rickandmorty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre;
    Button btnBuscar;
    ListView listInfo;

    ArrayList<Personaje> listado;
    PersonajeAdapter adapter;

    private String name;
    private String status;
    private String specie;
    private String type;
    private String gender;
    private String originName;
    private String nameLocation;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNombre = findViewById(R.id.edtNombre);
        btnBuscar = findViewById(R.id.btnBuscar);
        listInfo = findViewById(R.id.listInfo);

        listado = new ArrayList<>();
        adapter = new PersonajeAdapter(listado, this);
        listInfo.setAdapter(adapter);



    }

    public void buscarInfo(View v){
        listado.clear();
        String nombre = edtNombre.getText().toString();
        String url = "https://rickandmortyapi.com/api/character/?name="+nombre;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray listaPersonaje = jsonObject.getJSONArray("results");

                    for (int i = 0; i<listaPersonaje.length(); i++){
                        JSONObject info = listaPersonaje.getJSONObject(i);
                        name = info.getString("name");
                        status = info.getString("status");
                        specie = info.getString("species");
                        type = info.getString("type");
                        gender = info.getString("gender");
                        originName = info.getJSONObject("origin").getString("name");
                        nameLocation = info.getJSONObject("location").getString("name");
                        imageUrl = info.getString("image");
                        listado.add(new Personaje((i+1),name,status,specie,type,gender,originName,nameLocation,imageUrl));
                        adapter.notifyDataSetChanged();
                    }


                }catch (JSONException e){
                    Log.e("Api Info Pokemon", "Error parsing JSON", e);
                    Toast.makeText(MainActivity.this, "Pokemon no encontrado", Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }
}