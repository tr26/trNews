package com.example.trnews.Activities;

import android.content.Context;
import android.content.Intent;
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
import android.util.Log;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.trnews.Activities.SearchActivity;

import com.example.trnews.Adapters.PageAdapter;
import com.example.trnews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String API_KEY = "QAevoCdrGFGgi7mRDu8GBOME5RLn3veP";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_main_navigation_drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.activity_main_navView)
    NavigationView mNavigationView;
    @BindView(R.id.viewPagerActivityMain)
    ViewPager mViewPager;

    private TabLayout mTabLayout;

    private Button mSearchBtn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getBaseContext();

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        this.configureNavigationView();
        this.mSearchBtn = (Button) findViewById(R.id.search) ;
        this.configureDrawerLayout();
        this.configureViewPagerAndTabLayout();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    //CONFIGURATION DE NOTRE INTERFACE


    //2-DrawerLayout
    private void configureDrawerLayout(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
    }

    //3-Configure NavigationView
    private void configureNavigationView(){
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    //4-Configure ViewPager et TabLayout
    private void configureViewPagerAndTabLayout(){
        ViewPager mViewPager = (ViewPager)findViewById(R.id.viewPagerActivityMain);
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)));

        TabLayout tab = (TabLayout)findViewById(R.id.tabMainActivity);//tabLayout getted
        tab.setupWithViewPager(mViewPager);//linked to ViewPager
        tab.setTabMode(TabLayout.MODE_FIXED);//Same size for all buttons
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menu) {
        switch (menu.getItemId()){
            case R.id.search:
                Log.e("test", "test");
                return true;
                default:
                    return super.onOptionsItemSelected(menu);
        }
    }

    @Override
    public void onBackPressed(){
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void goToSearchActivity(MenuItem item){

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra( "title",  "searchActivity" );

        startActivity(intent);
    }

    public void goToNotificationsActivity(MenuItem item){

        Intent intent = new Intent(this, SearchActivity.class);

        intent.putExtra( "title",  "notificationsActivity" );
        startActivity(intent);
    }
}
