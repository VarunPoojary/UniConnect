package com.example.mitadttrial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mitadttrial.Adapters.FragmentsAdapter;
import com.example.mitadttrial.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth firebaseAuth;
    BottomNavigationView view;
    ViewPager viewPager;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("UniConnect");
        firebaseAuth = FirebaseAuth.getInstance();
        view = findViewById(R.id.bottomBar);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.message:
                        viewPager.setCurrentItem(1);
                        break;

                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.supportactionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                Intent intent1 = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent1);
                Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Intent intent3 = new Intent(MainActivity.this, AskQuestion.class);
                startActivity(intent3);
                Toast.makeText(MainActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this, "Re-Check Choice", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}