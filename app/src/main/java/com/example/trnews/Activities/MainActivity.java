package com.example.trnews.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.trnews.Adapters.PageAdapter;
import com.example.trnews.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        this.configureToolbar();
        this.configureNavigationView();
        this.configureDrawerLayout();
        this.configureViewPagerAndTabLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //CONFIGURATION DE NOTRE INTERFACE

    //1-TOOLBAR
    private void configureToolbar(){
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    //2-DrawerLayout
    private void configureDrawerLayout(){
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_navigation_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
    }

    //3-Configure NavigationView
    private void configureNavigationView(){
        this.mNavigationView = (NavigationView) findViewById(R.id.activity_main_navView);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    //4-Configure ViewPager et TabLayout
    private void configureViewPagerAndTabLayout(){
        ViewPager pager = (ViewPager)findViewById(R.id.viewPagerActivityMain);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)));

        TabLayout tab = (TabLayout)findViewById(R.id.tabMainActivity);//tabLayout getted
        tab.setupWithViewPager(pager);//linked to ViewPager
        tab.setTabMode(TabLayout.MODE_FIXED);//Same size for all buttons
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.activity_main_drawer_news:
                break;
            case R.id.activity_main_drawer_profil:
                break;
            case R.id.activity_main_drawer_settings:
                break;
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
