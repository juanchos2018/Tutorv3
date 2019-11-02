package com.example.tutorv3.ClasesAdmin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tutorv3.R;

import java.util.List;

public class Listas extends ArrayAdapter<cursotutor> {
    private Activity context;
    List<cursotutor> tracks;

    public Listas(Activity context, List<cursotutor> tracks) {
        super(context, R.layout.layout_artist_list, tracks);
        this.context = context;
        this.tracks = tracks;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        cursotutor track = tracks.get(position);

        textViewName.setText(track.getCurso());


        return listViewItem;
    }
}
