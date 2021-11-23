package com.mbappsoftware.mobiledevtest.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mbappsoftware.mobiledevtest.data.repository.Repository;
import com.mbappsoftware.mobiledevtest.data.repository.RepositoryCallbacks;
import com.mbappsoftware.mobiledevtest.data.response.GitHubRepository;
import com.mbappsoftware.mobiledevtest.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    Repository repository;

    MutableLiveData<List<GitHubRepository>> gitHubRepositoryLiveData = new MutableLiveData<>();

    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getRepositories(String q, String sort, String page) {
        repository.getGitHubRepositories(q, sort, page, new RepositoryCallbacks() {
            @Override
            public void onSuccess(List<GitHubRepository> gitHubRepositories) {
                gitHubRepositoryLiveData.postValue(gitHubRepositories);
            }
        });
    }

    public MutableLiveData<List<GitHubRepository>> getGitHubRepositoryLiveData() {
        return gitHubRepositoryLiveData;
    }

    public void setGitHubRepositoryLiveData(List<GitHubRepository> gitHubRepositories) {
        this.gitHubRepositoryLiveData.postValue(gitHubRepositories);
    }
}
