package com.example.ismaelnavbar;

import static nl.joery.animatedbottombar.AnimatedBottomBar.Tab;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ismaelnavbar.fragments.AccountFragment;
import com.example.ismaelnavbar.fragments.BookFragment;
import com.example.ismaelnavbar.fragments.HomeFragment;
import com.example.ismaelnavbar.fragments.NoticeFragment;
import com.example.ismaelnavbar.fragments.SettingsFragment;

import org.jetbrains.annotations.NotNull;

import nl.joery.animatedbottombar.AnimatedBottomBar;
import nl.joery.animatedbottombar.AnimatedBottomBar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Example 1");
        animatedBottomBar = findViewById(R.id.animatedBottomBar);

        if (savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        animatedBottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(int lastIndex, @Nullable Tab lastTab, int newIndex, @NotNull Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.notice:
                        fragment = new NoticeFragment();
                        break;
                    case R.id.faculty:
                        fragment = new AccountFragment();
                        break;
                    case R.id.gallery:
                        fragment = new BookFragment();
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error in creating Fragment");
                }
            }
        });
    }
}