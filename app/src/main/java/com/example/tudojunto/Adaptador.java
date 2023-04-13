package com.example.tudojunto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tudojunto.camera;
import com.example.tudojunto.joguinhoPoggers;

public class Adaptador extends FragmentStateAdapter {
    public Adaptador(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new camera();
            case 2:
                return new joguinhoPoggers();
        }
        return new calculadoraHoras();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}