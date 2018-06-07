package com.apiit.izzath.wmad2.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Fragments.AddCart;
import com.apiit.izzath.wmad2.Fragments.Checkout;
import com.apiit.izzath.wmad2.Fragments.ManageAccounts;
import com.apiit.izzath.wmad2.Fragments.Profile;
import com.apiit.izzath.wmad2.Fragments.PurchaseHistory;
import com.apiit.izzath.wmad2.Fragments.default_home;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList<String> productlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Fragment fragment=new default_home();
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.aaa,fragment).commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void home(){
        Fragment fragment=new default_home();
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.aaa,fragment).commit();


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if( fragment instanceof default_home){
                super.onBackPressed();
            }
            else {
               home();
            }

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.search,menu);





        return true;



        }
    private List<Product> filter(List<Product> p1,String query){
    query=query.toLowerCase();
    List<Product> filtermodel=new ArrayList<>();
        for (Product pp:
           p1  ) {
            String text=pp.getName().toLowerCase();
            if(text.startsWith(query)){
                filtermodel.add(pp);

            }
        }
        return filtermodel;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        int id = item.getItemId();
        if (id == R.id.home) {
            fragment= new default_home();
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.account) {
            fragment= new Profile();
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.addcart) {
            fragment= new AddCart();
            Toast.makeText(this, "Cart Details", Toast.LENGTH_SHORT).show();


        }
        else if (id == R.id.inquire) {
                 fragment=new Checkout();


        }
        else if (id == R.id.checkout) {
            fragment=new Checkout();
            setTitle("Checkout");

        }
        else if (id == R.id.purchase) {
                fragment=new PurchaseHistory();
                setTitle("Purchase History");
                Toast.makeText(this, "Purchase history", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.logout) {
            SharedPreferences sp =this.getApplicationContext().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            finish();
           // Toast.makeText(this, "x=dilrukshi", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.settings) {
            fragment=new ManageAccounts();
            setTitle("Settings");
        }
        if(fragment!=null){

            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.aaa,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
