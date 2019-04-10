package com.miage.weatherapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miage.weatherapp.DTO.List_cities;
import com.miage.weatherapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int GOOGLE_SIGN_IN = 123;
    static FirebaseAuth mAuth;
    static DatabaseReference mDatabase;
    static GoogleSignInClient mGoogleSignInClient;

    static TextView nom;
    static Button sign_out_button;
    static SignInButton sign_in_button;
    static List_cities cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        cities = new List_cities();
        jsonParse("https://www.prevision-meteo.ch/services/json/list-cities");
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nom = navigationView.getHeaderView(0).findViewById(R.id.nom);
        sign_out_button = navigationView.getHeaderView(0).findViewById(R.id.sign_out_button);
        sign_in_button = navigationView.getHeaderView(0).findViewById(R.id.sign_in_button);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signInGoogle();
            }
        });
        sign_out_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signOutGoogle();
            }
        });

        if (MainActivity.mAuth.getCurrentUser() != null) {
            FirebaseUser user = MainActivity.mAuth.getCurrentUser();
            updateUIAndDatabase(user);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new CityWeather());
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    private void jsonParse(String url){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("I", "onResponse: "+response);
                        try {
                            cities = new List_cities(response);
                           // adapter = new ListCityAdapter(this,0,cities.getCitys());
                            //v.setAdapter(adapter);
                            //adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // call to method

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", "Error occurred ", error);
                    }
                });

        requestQueue.add(jsonObject);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.menu_favoris) {
            fragment = new LoginFragment();
        } else if (id == R.id.menu_meteo) {
            fragment = new CityWeather();
        } else if (id == R.id.menu_recherche) {
            fragment = new RechercheFragment();
        }

        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.content_frame, fragment);

            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void signInGoogle() {
        Intent signInIntent = MainActivity.mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, MainActivity.GOOGLE_SIGN_IN);
    }

    private void signOutGoogle() {
        FirebaseAuth.getInstance().signOut();
        MainActivity.mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUIAndDatabase(null);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        MainActivity.mAuth.signInWithCredential(credential)
                .addOnCompleteListener (this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete (@NonNull Task <AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = MainActivity.mAuth.getCurrentUser();

                            updateUIAndDatabase(user);
                        } else {

                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    public static void updateUIAndDatabase(FirebaseUser user) {
        if (user != null) {
            MainActivity.mDatabase = FirebaseDatabase.getInstance().getReference(MainActivity.mAuth.getUid());
            FavorisFragment.updateListeFavoris();

            nom.setText(user.getDisplayName());
            nom.setVisibility(View.VISIBLE);
            sign_out_button.setVisibility(View.VISIBLE);
            sign_in_button.setVisibility(View.INVISIBLE);
        } else {
            sign_out_button.setVisibility(View.INVISIBLE);
            sign_in_button.setVisibility(View.VISIBLE);
            nom.setVisibility(View.INVISIBLE);
        }
    }
}
