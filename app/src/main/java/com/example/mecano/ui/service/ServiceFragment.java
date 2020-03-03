package com.example.mecano.ui.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mecano.R;


public class ServiceFragment extends Fragment {

    private ServiceViewModel serviceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        serviceViewModel =
                ViewModelProviders.of(this).get(ServiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_service, container, false);
        final TextView textView = root.findViewById(R.id.text_service);
        serviceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
