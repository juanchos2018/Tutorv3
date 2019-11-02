package com.example.tutorv3.ClasesAdmin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tutorv3.R;

import java.util.List;

public class Lista2 extends ArrayAdapter<Alumnos> {

    private Activity context;
    List<Alumnos> tracks;

    public Lista2(Activity context, List<Alumnos> tracks) {
        super(context, R.layout.layout_artist_list2, tracks);
        this.context = context;
        this.tracks = tracks;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list2, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName1);

        Alumnos track = tracks.get(position);

        textViewName.setText(track.getNombre());


        return listViewItem;
    }
}
