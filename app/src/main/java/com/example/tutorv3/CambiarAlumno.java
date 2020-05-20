package com.example.tutorv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorv3.ClasesAdmin.ClsProfesores;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CambiarAlumno extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    private DatabaseReference reference;

    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_alumno);
        recycler=findViewById(R.id.idrecylersalones);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        reference= FirebaseDatabase.getInstance().getReference("Profesores");
    }

    @Override
    protected void onStart() {
        super.onStart();

          FirebaseRecyclerOptions<ClsProfesores> recyclerOptions = new FirebaseRecyclerOptions.Builder<ClsProfesores>()
                  .setQuery(reference.orderByChild("tipo").equalTo("profesor"), ClsProfesores.class).build();

          FirebaseRecyclerAdapter<ClsProfesores, ChatsVH> adapter2 = new FirebaseRecyclerAdapter<ClsProfesores, ChatsVH>(recyclerOptions) {
              @Override
              protected void onBindViewHolder(@NonNull final ChatsVH holder, int position, @NonNull ClsProfesores model) {

                  final String userID = getRef(position).getKey();
                  reference.child(userID).addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                          if (dataSnapshot.exists()){
                              final String idprofe = dataSnapshot.child("idprofe").getValue().toString();
                              final String nombresalon = dataSnapshot.child("nombresalon").getValue().toString();
                              final String coreoprofe = dataSnapshot.child("correo").getValue().toString();
                              final String sede = dataSnapshot.child("sede").getValue().toString();
                              holder.tvidprofe.setText(idprofe);
                              holder.tvnombresalon.setText(nombresalon);
                              holder.tvsede.setText(sede);
                              holder.tvcorreprofe.setText(coreoprofe);

                              holder.itemView.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent intent= new Intent(CambiarAlumno.this,Alumnos.class);
                                      Bundle bundle= new Bundle();
                                      bundle.putString("p1",idprofe);
                                      bundle.putString("sa",nombresalon);
                                      bundle.putString("se",sede);
                                      bundle.putString("co",coreoprofe);
                                      intent.putExtras(bundle);
                                      startActivity(intent);
                                  }
                              });

                          }
                          else{
                              Toast.makeText(CambiarAlumno.this, "", Toast.LENGTH_SHORT).show();
                          }
                      }
                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {

                      }
                  });
              }

              @NonNull
              @Override
              public ChatsVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                  View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profesores, viewGroup, false);
                  return new ChatsVH(view);
              }
          };
          recycler.setAdapter(adapter2);
          adapter2.startListening();
    }

    public static class ChatsVH extends RecyclerView.ViewHolder{
        TextView tvnombresalon, tvsede,tvidprofe,tvcorreprofe;

        public ChatsVH(View itemView) {
            super(itemView);
            tvnombresalon = itemView.findViewById(R.id.txtnombresalon);
            tvidprofe = itemView.findViewById(R.id.idoprofesor);//idcorreoprofesor tvsede
            tvcorreprofe = itemView.findViewById(R.id.idcorreoprofesor);
            tvsede = itemView.findViewById(R.id.tvsede);
        }

    }
}
