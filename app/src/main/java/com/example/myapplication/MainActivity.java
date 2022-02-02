package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.ContactScreen.ContectFragment;
import com.example.myapplication.MusicScreen.MusicFragment;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ContectFragment contectFragment = new ContectFragment();
    MusicFragment musicFragment = new MusicFragment();
    public static int REQUEST_MULTIPLE = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddPage(contectFragment,"Contact");
        adapter.AddPage(musicFragment,"Music");

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ArrayList<String > permissions = new ArrayList<>();
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissions.add(Manifest.permission.READ_CONTACTS);

            ArrayList<String> notGrantedPermission = new ArrayList<>();

            for (String i:permissions){
                if (ActivityCompat.checkSelfPermission(MainActivity.this,i)!= PackageManager.PERMISSION_GRANTED){
                    notGrantedPermission.add(i);
                }
            }
            if(!notGrantedPermission.isEmpty()){
                ActivityCompat.requestPermissions(this,notGrantedPermission.toArray(new String[notGrantedPermission.size()]),REQUEST_MULTIPLE);
            }
        }else {
            contectFragment.setRecycler();
            musicFragment.setMusic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_MULTIPLE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                musicFragment.setMusic();
            }
            if (grantResults.length>0 && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                contectFragment.setRecycler();
            }
        }
    }
}