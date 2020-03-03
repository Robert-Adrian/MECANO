package com.example.mecano.ui.mycar;

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

public class MyCarFragment extends Fragment {

    private MyCarViewModel mycarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mycarViewModel =
                ViewModelProviders.of(this).get(MyCarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mycar, container, false);
        final TextView textView = root.findViewById(R.id.text_mycar);
        mycarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}

