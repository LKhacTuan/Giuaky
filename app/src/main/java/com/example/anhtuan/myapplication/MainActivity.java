package com.example.anhtuan.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.anhtuan.myapplication.fragment.Calendar_fragment;
import com.example.anhtuan.myapplication.fragment.Todolist_fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.ln_todolist)
    LinearLayout lnTodolist;
    @BindView(R.id.ln_celendar)
    LinearLayout lnCelendar;
    @BindView(R.id.fl_add)
    FloatingActionButton flAdd;
    @BindView(R.id.img_tabmenu)
    ImageView imgTabmenu;
    @BindView(R.id.ln_title)
    LinearLayout lnTitle;
    @BindView(R.id.rl_fragment)
    RelativeLayout rlFragment;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lnTodolist.setOnClickListener(this);
        lnCelendar.setOnClickListener(this);
        flAdd.setOnClickListener(this);
        imgTabmenu.setOnClickListener(this);
        navView.setNavigationItemSelectedListener(this);
        replaceFragmentContent(new Todolist_fragment());
    }

    private void replaceFragmentContent(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == lnTodolist) {
            replaceFragmentContent(new Todolist_fragment());
        } else if (v == lnCelendar) {
            replaceFragmentContent(new Calendar_fragment());
        } else if (v == flAdd) {
            Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
        } else if (v == imgTabmenu) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
