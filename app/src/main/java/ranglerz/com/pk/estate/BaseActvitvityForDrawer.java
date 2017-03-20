package ranglerz.com.pk.estate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class BaseActvitvityForDrawer extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    MenuItem navLoginRegister;
    MenuItem navUsername;
    MenuItem navChosePlane;


    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_actvity_for_drawer);
        view = new View(this);


        /**
         *Setup the DrawerLayout and NavigationView
         */




        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        // get menu from navigationView
        Menu menu = mNavigationView.getMenu();

        // find MenuItem you want to change
        navUsername = menu.findItem(R.id.nav_item_name);
        navLoginRegister = menu.findItem(R.id.nav_item_login_registe);
        navChosePlane = menu.findItem(R.id.nav_item_chose_plain);






        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
        Log.e("TAG", "SharePreference " + sharedPreferences);
        if (sharedPreferences!=null){

            String name = sharedPreferences.getString("name", null);

            if (name!=null) {
                Log.e("TAG", "SharePreference 11 " + name);

                // set new title to the MenuItem
                navUsername.setTitle(name);
                navUsername.setIcon(R.drawable.home_image_view);
                navLoginRegister.setTitle("Log Out");

                navChosePlane.setTitle("Chose Plane");
            }

        }




        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                if (menuItem.getItemId() == R.id.nav_item_home) {
                    //home activity
                    Toast.makeText(getApplicationContext(), "Home Activity", Toast.LENGTH_SHORT).show();
                    Intent mainActvity = new Intent(BaseActvitvityForDrawer.this, Home.class);
                    startActivity(mainActvity);
                    finish();
                }

                if (menuItem.getItemId() == R.id.nav_item_about) {
                    //map activity
                    Intent aboutUs = new Intent(BaseActvitvityForDrawer.this, AboutUs.class);
                    startActivity(aboutUs);
                }

                if (menuItem.getItemId() == R.id.nav_item_chose_plain){

                    if (navChosePlane.getTitle().toString().equals("Chose Plane")) {

                        Intent mapActivity = new Intent(BaseActvitvityForDrawer.this, ChosePlain.class);
                        startActivity(mapActivity);
                    }
                }

                if (menuItem.getItemId()==R.id.nav_item_login_registe){


                    String title =  menuItem.getTitle().toString();
                    Log.e("TAG", "Menu TAG " + title);


                    if (title.equals("Login/Register")) {

                        Intent mapActivity = new Intent(BaseActvitvityForDrawer.this, LoginOrRegister.class);
                        startActivity(mapActivity);
                        //finish();
                    }


                    if (title.equals("Log Out")){

                        //do logout Function here
                        final SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        finish();
                        Intent mapActivity = new Intent(BaseActvitvityForDrawer.this, Home.class);
                        startActivity(mapActivity);

                    }

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();



    }//end on Create

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
        Log.e("TAG", "SharePreference " + sharedPreferences);
        if (sharedPreferences!=null){

            String name = sharedPreferences.getString("name", null);

            if (name!=null) {
                Log.e("TAG", "SharePreference 11 " + name);

                // set new title to the MenuItem
                navUsername.setTitle(name);
                navUsername.setIcon(R.drawable.home_image_view);
                navLoginRegister.setTitle("Log Out");
                navChosePlane.setTitle("Chose Plane");
                navChosePlane.setIcon(R.drawable.home_image_view);
            }
        }
    }

}
