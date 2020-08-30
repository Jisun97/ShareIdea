package com.example.shareidea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.shareidea.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

            binding.bottomMain.setOnNavigationItemSelectedListener(item -> {

                switch (item.getItemId()) {
                    case R.id.action_1:
                        break;
                    case R.id.action_2:
                        break;
                    case R.id.action_3:
                        break;
                    case R.id.action_4:
                        break;
                }

                return true;
            });
        }

        private void switchFragment(Fragment fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_main, fragment);
            transaction.commit();
        }
    }

}