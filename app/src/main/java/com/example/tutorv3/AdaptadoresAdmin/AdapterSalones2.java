package com.example.tutorv3.AdaptadoresAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorv3.ClasesAdmin.ClsProfesores;
import com.example.tutorv3.R;

import java.util.ArrayList;

public class AdapterSalones2 extends RecyclerView.Adapter<AdapterSalones2.ViewHolderDatos>  implements View.OnClickListener {

    ArrayList<ClsProfesores> listaSalones;

    public AdapterSalones2(ArrayList<ClsProfesores> listaSalones) {
        this.listaSalones = listaSalones;
    }

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profesores2, parent, false);
        vista.setOnClickListener(this);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos) {
            final ViewHolderDatos datgolder = (ViewHolderDatos) holder;
            datgolder.tvnombresalon.setText(listaSalones.get(position).getNombresalon());
            datgolder.tvcorreprofe.setText(listaSalones.get(position).getCorreo());
            datgolder.tvsede.setText(listaSalones.get(position).getSede());
            datgolder.tvidprofe.setText(listaSalones.get(position).getIdprofe());
        }

    }

    @Override
    public int getItemCount() {
        return listaSalones.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvnombresalon, tvsede,tvidprofe,tvcorreprofe;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvnombresalon = itemView.findViewById(R.id.txtnombresalon1);
            tvidprofe = itemView.findViewById(R.id.idoprofesor1);//idcorreoprofesor tvsede
            tvcorreprofe = itemView.findViewById(R.id.idcorreoprofesor1);
            tvsede = itemView.findViewById(R.id.tvsede1);
            // tvnombre = itemView.findViewById(R.id.idnpmbreprofesor3);
        }
    }
}
