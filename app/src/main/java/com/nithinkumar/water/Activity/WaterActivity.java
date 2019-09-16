package com.nithinkumar.water.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nithinkumar.water.R;
import com.nithinkumar.water.WaterShared;
import com.nithinkumar.water.fragment.DetailsDialogFragment;
import com.nithinkumar.water.fragment.HomeFragment;
import com.nithinkumar.water.fragment.SettingsFragment;
import com.nithinkumar.water.fragment.WaterIntakeFragment;

public class WaterActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    WaterShared mWaterShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        mWaterShared = new WaterShared(this);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        if(mWaterShared.getFirstRun_app().equals(""))
        {
            DetailsDialogFragment ddf = new DetailsDialogFragment();
            ddf.show(fragmentManager,"ActivityDialog");

        }
        else{
            mWaterShared.setFirstRun_app("Done");
           //afterCheck();
        }

        afterCheck();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, HomeFragment.newInstance(R.layout.fragment_home),"1");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, WaterIntakeFragment.newInstance(),"2");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, SettingsFragment.newInstance(R.layout.fragment_settings),"3");
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };

    private void afterCheck()
    {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, HomeFragment.newInstance(R.layout.fragment_home), "1");
        fragmentTransaction.commit();
    }

}
