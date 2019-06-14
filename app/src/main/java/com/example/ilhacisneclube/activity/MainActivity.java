package com.example.ilhacisneclube.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.fragments.CampeonatoFragment;
import com.example.ilhacisneclube.fragments.HomeFragment;
import com.example.ilhacisneclube.fragments.RankingFragment;
import com.example.ilhacisneclube.fragments.TabelaFragment;


public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        inicializarComponentes();
        infla(new HomeFragment(), "home");  //Após iniciar a tela ele ja inicia no fragmento home, por default;
    }


    //Crio uma nova instancia do botton navigation e passo pra ele o metodo itemselected para realizar meus cases
    public BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.navigation_campeonato:
                    infla(new CampeonatoFragment(), "Campeonato");
                    return true;
                case R.id.navigation_tabela:
                    infla(new TabelaFragment(), "Tabela");
                    return true;
                case R.id.navigation_home:
                    infla(new HomeFragment(), "Home");
                    return true;
                case R.id.navigation_ranking:
                    infla(new RankingFragment(), "Ranking");
                    return true;
            }

            return false;
        }
    };


    private void inicializarComponentes() {
        //BottomNavigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        frameLayout = findViewById(R.id.fragment_container);
    }

    public void infla(Fragment fragmento, String tag){ //Criei um metodo de inflar fragmentos na tela
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmento, tag).commit();
    }

}


