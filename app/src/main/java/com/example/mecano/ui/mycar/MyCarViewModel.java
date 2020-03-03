package com.example.mecano.ui.mycar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyCarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my car fragment");
    }

    public LiveData<String> getText() { return mText; }
}


