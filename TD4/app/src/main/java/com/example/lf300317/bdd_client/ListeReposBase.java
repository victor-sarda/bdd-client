package com.example.lf300317.bdd_client;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ListeReposBase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_repos_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ReposBase();
    }
    public void ReposBase(){
        Realm realm = Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        realm = Realm.getInstance(getApplicationContext());
        RealmQuery<RepoBase> query = realm.where(RepoBase.class);
        RealmResults<RepoBase> result = query.findAll();
        realm.commitTransaction();

        ArrayList listReposBase = new ArrayList<>();

        for (RepoBase u : result)
        {
            listReposBase.add(u.getName());
        }

        ListView liste2 = (ListView) findViewById(R.id.ListeHistorique);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, listReposBase);
        // On assigne l'adapter à notre listeView
        liste2.setAdapter(adapter);
    }
    // Effacement de l'ensemble de la base de realm
    public void delBase(View view){
        deleteAll();
    }

    public void deleteAll () {
        Realm realm = Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        // On recherche toutes les instances
        RealmQuery<RepoBase> query2 = realm.where(RepoBase.class);
        RealmResults<RepoBase> allReposBase = query2.findAll();
        // On les supprime
        allReposBase.clear();
        realm.commitTransaction();
        super.onBackPressed();
    }
}
