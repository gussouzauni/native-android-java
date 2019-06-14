package com.example.ilhacisneclube.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.adaptador.AdapterFeed;
import com.example.ilhacisneclube.model.Feed;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewFeed;
    private AdapterFeed adapterFeed;
    private ArrayList<Feed> feeds = new ArrayList<>();

    public HomeFragment() {
    }

    @Override  //Inicia a visão gráfica da tela aqui;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.home_fragment, container, false);


    }

    @Override //É chamado antes do onCreateView, faz todas as operacoes de back end da tela
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inicializarComponentes();
        inserindoFeed();

    }

    private void inicializarComponentes() {
        recyclerViewFeed = getView().findViewById(R.id.recycler_feed);
        recyclerViewFeed.setLayoutManager(new LinearLayoutManager(getContext()));
        //Adapter
        adapterFeed = new AdapterFeed(feeds);
        recyclerViewFeed.setAdapter(adapterFeed);
        Toolbar toolbar = getView().findViewById(R.id.toolbar_home_fragment);
        toolbar.setTitle("Feed");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void inserindoFeed() {
        Feed feed1 = new Feed();
        feed1.setNomeTitulo("Lazer e diversão para todos");
        feed1.setDescricao("Diversão em qualquer lugar...");
        feed1.setNomeAutor("Gustavo Souza");
        feed1.setData("20/04/2019");
        feed1.setNota(5);
        feed1.setImagemFeed("https://scontent.fitp2-1.fna.fbcdn.net/v/t1.0-9/51083572_354238292092123_2909024814649835520_o.jpg?_nc_cat=107&_nc_oc=AQnS1qcEQW7zdkOUynBry0mC91hFbTb1ncc43KZ95kZfXPplmqOZFYliTG97tqAAP3U&_nc_ht=scontent.fitp2-1.fna&oh=c5eb13727bdd683fb93a0e06d18a53df&oe=5D57513C");
        feeds.add(feed1);

        Feed feed2 = new Feed();
        feed2.setNomeTitulo("O futebol dos jovens e coroas");
        feed2.setDescricao("Onde tudo se resume em diversão");
        feed2.setNomeAutor("Gustavo Souza");
        feed2.setData("21/04/2019");
        feed2.setNota(5);
        feed2.setImagemFeed("https://scontent.fitp2-1.fna.fbcdn.net/v/t31.0-8/25311158_1915400471806904_8226743838306153106_o.jpg?_nc_cat=100&_nc_ht=scontent.fitp2-1.fna&oh=021b58fc27f9d3110b1626f3400ecf0f&oe=5D8C527E");
        feeds.add(feed2);

        Feed feed3 = new Feed();
        feed3.setNomeTitulo("Quando tudo começou...");
        feed3.setDescricao("O início de um grande clube social");
        feed3.setNomeAutor("Guilherme Souza");
        feed3.setData("31/05/2018");
        feed3.setNota(4);
        feed3.setImagemFeed("http://www.folha1.com.br/_midias/wp/blogs/nino/files/2016/08/ilha_cisne.jpg");
        feeds.add(feed3);

        Feed feed4 = new Feed();
        feed4.setNomeTitulo("O antes e o depois...");
        feed4.setDescricao("Lembrar também é viver o presente");
        feed4.setNomeAutor("Raphael Ramos");
        feed4.setData("21/04/2019");
        feed4.setNota(5);
        feed4.setImagemFeed("http://2.bp.blogspot.com/-VaB0KZ-zTKA/T3jZ5oheVlI/AAAAAAAAAHQ/GQn1t4uIaGU/s1600/icc.png");
        feeds.add(feed4);


    }
}



