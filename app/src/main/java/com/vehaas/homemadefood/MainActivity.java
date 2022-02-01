package com.vehaas.homemadefood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navlister);


        //setting Home fragment as a main fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HomeFragment()).commit();

    }
    //Listener Nav bar
    private BottomNavigationView.OnNavigationItemSelectedListener navlister=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment=new HomeFragment();
                    break;
                case R.id.nav_account:
                    selectedFragment=new AccountFragment();
                    break;
                case R.id.nav_cart:
                    selectedFragment =new CartFragment();
                    break;

            }
            //Begin Transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,selectedFragment).commit();
            return true;
        };
    };


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logging out the user
        startActivity(new Intent(getApplicationContext(),LoginFragment.class));
        finish();
    }
    public void login_txt(View view) {
        startActivity(new Intent(getApplicationContext(),LoginFragment.class));

    }
}