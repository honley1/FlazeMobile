package com.honley.flazemobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.honley.flazemobile.bottom_nav.articles.ArticlesFragment;
import com.honley.flazemobile.databinding.ActivityMainBinding;
import com.honley.flazemobile.bottom_nav.profile.ProfileFragment;
import com.honley.flazemobile.bottom_nav.support.SupportFragment;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }
        catch (NullPointerException exception) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        getSupportFragmentManager().beginTransaction().
                replace(binding.fragmentContainer.getId(), new ArticlesFragment()).commit();
        binding.bottomNav.setSelectedItemId(R.id.articles);

        Map<Integer, Fragment> fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.articles, new ArticlesFragment());
        fragmentMap.put(R.id.support, new SupportFragment());
        fragmentMap.put(R.id.profile, new ProfileFragment());

        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());

            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragment).commit();

            return true;
        });
    }
}