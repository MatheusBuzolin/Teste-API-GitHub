package com.mbappsoftware.mobiledevtest.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mbappsoftware.mobiledevtest.data.repository.Repository;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public MainActivityViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class))
            return (T) new MainActivityViewModel(repository);
        throw new IllegalArgumentException("Classe desconhecida ViewModel");
    }
}
