package com.example.laundryshopmanagercapstone.ui.productList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProductListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is product list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}