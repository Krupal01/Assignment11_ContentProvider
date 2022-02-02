package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ViewPagerAdapter extends FragmentPagerAdapter  {

    public List<Fragment> fragments = new ArrayList<>();
    public List<String > fragmentsName = new ArrayList<>();

    public void AddPage(Fragment fragment , String name){
        fragments.add(fragment);
        fragmentsName.add(name);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsName.get(position);
    }
}
