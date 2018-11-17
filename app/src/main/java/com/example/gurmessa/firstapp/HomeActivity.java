package com.example.gurmessa.firstapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void onClickHomeButtons(View view){
        switch (view.getId()){
            case R.id.buttonCheck:
                Intent intent1=new Intent(HomeActivity.this,CheckSymptomsActivity.class);
                startActivity(intent1);
                break;
            case R.id.buttonAnalyse:
                Intent intent2=new Intent(HomeActivity.this,AnalyzeDataActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //analyze data
            Intent intent=new Intent(HomeActivity.this,AnalyzeDataActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            //check symptom
            Intent intent=new Intent(HomeActivity.this,CheckSymptomsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            //prevention
            Intent intent=new Intent(HomeActivity.this,PreventionActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            //Treatment
            Intent intent=new Intent(HomeActivity.this,RiskFactorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
