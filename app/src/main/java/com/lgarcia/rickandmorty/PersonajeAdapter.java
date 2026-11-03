package com.lgarcia.rickandmorty;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PersonajeAdapter extends BaseAdapter {

    private ArrayList<Personaje> lista;
    private Activity activity;

    public PersonajeAdapter(ArrayList<Personaje> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)  activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.lista_personaje, null);

        Personaje p = lista.get(i);

        TextView txtInfo = v.findViewById(R.id.txtInfo);
        ImageView imgFoto = v.findViewById(R.id.imgFoto);

        String info = "Name: "+p.getName()+
                "\nStatus: "+p.getStatus()+
                "\nSpecie: "+p.getSpecie()+
                "\nStatus: "+p.getStatus()+
                "\nType: "+p.getType()+
                "\nGender: "+p.getGender()+
                "\nOrigin: "+p.getOriginName()+
                "\nLocation: "+p.getNameLocation();

        String url = p.getImageUrl();

        txtInfo.setText(info);

        Picasso.get().load(url).into(imgFoto);


        return v;
    }
}
