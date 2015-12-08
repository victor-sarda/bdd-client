package com.example.lf300317.bdd_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getUsername();
        repoSearch(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Interface gitHub
    public interface GitHubService {
        @GET("/users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }

    // Récupération du nom d'utilisateur si enregistré
    public String getUsername(){
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        String mValue = sharedPref.getString("Repo", "");
        EditText username = (EditText) findViewById(R.id.InputUsername);
        username.setText(mValue);
        return mValue;
    }

    // Sauvegarde du nom utilisateur
    public void SaveUsername(View view){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        EditText username = (EditText) findViewById(R.id.InputUsername);
        editor.putString("Repo", username.getText().toString());
        editor.commit();
        Toast.makeText(MainActivity.this, String.format("Le nom d'utilisateur à été enregistré !"), Toast.LENGTH_SHORT).show();
    }
    // Efface le nom utilisateur
    public void DelUsername(View view){
        EditText username = (EditText) findViewById(R.id.InputUsername);
        username.setText("");
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Repo", "");
        editor.commit();
        Toast.makeText(MainActivity.this, String.format("Le nom d'utilisateur enregistré à été supprimé !"), Toast.LENGTH_SHORT).show();
    }

    // Fonction qui recherche des repository
    public void repoSearch(final String username){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        if (username != "" && !username.isEmpty()) {
            Call<List<Repo>> repos = service.listRepos(username);

            repos.enqueue(new Callback<List<Repo>>() {
                              @Override
                              public void onResponse(Response<List<Repo>> response, Retrofit retrofit) {
                                  // Assignation des informations récupérées
                                  final List<Repo> allRepos = response.body();

                                  // Creation de l'array pour l'afficher dans le listview
                                  ListView liste = (ListView) findViewById(R.id.ListeRepos);
                                  String[] repos = new String[allRepos.size()];
                                  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, repos);
                                  // On assigne l'adapter à notre listeView
                                  liste.setAdapter(adapter);

                                  // Si on recupere des informations
                                  if (allRepos.size() >= 1) {
                                      // Pour chaque repository trouvé, on l'ajoute à la liste
                                      for (int i = 0; i < allRepos.size(); i++) {
                                          repos[i] = allRepos.get(i).name.toString();
                                          String name = allRepos.get(i).name.toString();
                                          String description = allRepos.get(i).description.toString();
                                          String url = allRepos.get(i).html_url.toString();
                                          String date = allRepos.get(i).created_at.toString();
                                          int issues = allRepos.get(i).open_issues_count;
                                          RepoBase reposBase = new RepoBase(name, description, url, date, issues);
                                          Realm realm = Realm.getInstance(getApplicationContext());
                                          realm.beginTransaction();
                                          RepoBase realmUser = realm.copyToRealm(reposBase);
                                          realm.commitTransaction();
                                      }
                                      // Sinon affichage d'un message d'erreur
                                  } else {
                                      repos[0] = "Aucun repository trouvé pour l'utilisateur " + username + " ! ";
                                  }
                                  // On notifie la listeView du changement
                                  liste.deferNotifyDataSetChanged();

                                  liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      public void onItemClick(AdapterView parent, View v, int position, long id) {
                                          String message = "Nom de repository : " + allRepos.get(position).name.toString() + "\n";
                                          message = message + "Description : " + allRepos.get(position).description.toString() + "\n";
                                          message = message + "Url : " + allRepos.get(position).html_url.toString()+ "\n";
                                          message = message + "Date de création : " + allRepos.get(position).created_at.toString() + "\n";
                                          message = message + "Nombre d'issues : " + allRepos.get(position).open_issues_count;

                                          Toast.makeText(MainActivity.this, message , Toast.LENGTH_SHORT).show();


                                      }
                                  });
                              }

                              @Override
                              public void onFailure(Throwable t) {
                                  Toast.makeText(MainActivity.this, String.format("Erreur de contact de l'API Github"), Toast.LENGTH_SHORT).show();
                              }
            });
        }
    }

    // Onclick du bouton rechercher
    public void Search(View view){
        EditText username = (EditText) findViewById(R.id.InputUsername);
        repoSearch(username.getText().toString());
    }

    public void SeeBase(View view){
        Intent intent = new Intent(this, ListeReposBase.class);
        MainActivity.this.startActivity(intent);
    }
}
