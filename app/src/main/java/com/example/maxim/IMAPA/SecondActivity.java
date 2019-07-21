package com.example.maxim.IMAPA;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.maxim.IMAPA.Fragments.BalanceFragment;
import com.example.maxim.IMAPA.Fragments.BillsFragment;
import com.example.maxim.IMAPA.Fragments.ProfileFragment;


public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NotificationCompat.Builder notification;
    private DrawerLayout drawer;
    private int menuItemID = 1;
    NavigationView navigationView;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                drawer.closeDrawers();
                break;
            case R.id.nav_balance:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BalanceFragment()).commit();
                drawer.closeDrawers();
                break;
            case R.id.nav_bills:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BillsFragment()).commit();
                drawer.closeDrawers();
                break;
            case R.id.nav_qr:
                Intent intent1 = new Intent(getApplicationContext(), BarcodeActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finishAffinity();
                startActivity(intent);

        }

        menuItemID = menuItem.getItemId();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BalanceFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_balance);
        }
        notification = new NotificationCompat.Builder(this, "");


    }

        @Override
        public void onBackPressed () {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else if (menuItemID == 1){
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Quiting");
                builder.setMessage("Do you want to exit?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                    }
                });
                builder.show();
            }
            else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BalanceFragment()).commit();
                menuItemID = 1;
                navigationView.getMenu().getItem(1).setChecked(true);
                drawer.closeDrawers();
            }
        }
}
