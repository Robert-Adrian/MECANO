package com.example.mecano.ui.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServiceViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ServiceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is search service fragment");
    }

    public LiveData<String> getText() { return mText; }

}
