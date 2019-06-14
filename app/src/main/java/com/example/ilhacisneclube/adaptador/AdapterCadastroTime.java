package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Time;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AdapterCadastroTime extends RecyclerView.Adapter<AdapterCadastroTime.ViewHolderTime> {

    private ArrayList<Time> times;
    DatabaseReference databaseReference;
    FirebaseDatabase mFirebasedatabase;

    public AdapterCadastroTime(ArrayList<Time> times) {
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolderTime onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_time,viewGroup,false);
        return new ViewHolderTime(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTime viewHolderTime, int posicao) {
            //Firebase
            mFirebasedatabase = FirebaseDatabase.getInstance();
            databaseReference = mFirebasedatabase.getReference();
            Time time = times.get(posicao);
            viewHolderTime.nomeTime.setText(time.getNomeTime());


            /*
            viewHolderTime.botaoExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseReference.child("Time").child(usuarioLogado()).child(IDCampeonato).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            String key = dataSnapshot.getKey();
                            Log.v("Key: ", key);
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });
            */
    }

    @Override
    public int getItemCount() {
        return times.size();
    }


    public class ViewHolderTime extends RecyclerView.ViewHolder {

        private TextView nomeTime;
        private ImageButton botaoExcluir;


        public ViewHolderTime(@NonNull View itemView) {
            super(itemView);
            nomeTime = itemView.findViewById(R.id.text_view_nome_time);
            botaoExcluir = itemView.findViewById(R.id.image_button_excluir_time);
        }
    }
}
