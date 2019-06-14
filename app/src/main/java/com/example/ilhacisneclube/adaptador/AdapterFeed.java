package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Feed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.ViewHolderFeed> {

    private ArrayList<Feed> feeds;

    public AdapterFeed(ArrayList<Feed> feeds) {
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public ViewHolderFeed onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_feed, viewGroup, false);
        return new ViewHolderFeed(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFeed viewHolderFeed, int posicao) {
        Feed feed = feeds.get(posicao);
        viewHolderFeed.nomeTitulo.setText(feed.getNomeTitulo());
        viewHolderFeed.descricao.setText(feed.getDescricao());
        viewHolderFeed.nomeAutor.setText(feed.getNomeAutor());
        viewHolderFeed.data.setText(feed.getData());
        viewHolderFeed.nota.setRating(feed.getNota());
        Picasso.get().load(feed.getImagemFeed()).into(viewHolderFeed.imagemFeed);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }


    public class ViewHolderFeed extends RecyclerView.ViewHolder {

        private TextView nomeTitulo, nomeAutor, data, descricao;
        private RatingBar nota;
        private ImageView imagemFeed;

        public ViewHolderFeed(@NonNull View itemView) {
            super(itemView);
            nomeTitulo = itemView.findViewById(R.id.text_view_nome_campeonato_adapter_ranking);
            nomeAutor = itemView.findViewById(R.id.text_view_autor_feed);
            descricao = itemView.findViewById(R.id.text_view_modalidade_campeonato_adapter_ranking);
            data = itemView.findViewById(R.id.text_view_data_publicacao_feed);
            nota = itemView.findViewById(R.id.rating_nota);
            imagemFeed = itemView.findViewById(R.id.image_view_feed_adapter);
        }
    }
}
