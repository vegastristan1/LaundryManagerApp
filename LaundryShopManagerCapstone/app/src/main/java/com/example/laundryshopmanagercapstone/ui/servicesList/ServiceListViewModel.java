package com.example.laundryshopmanagercapstone.ui.servicesList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServiceListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ServiceListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is product list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}