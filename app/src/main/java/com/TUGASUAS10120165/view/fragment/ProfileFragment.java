package com.TUGASUAS10120165.view.fragment;
// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.TUGASUAS10120165.R;
import com.TUGASUAS10120165.view.activity.MainActivity;

public class ProfileFragment extends Fragment {

    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().hide();
    }
}

// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4

