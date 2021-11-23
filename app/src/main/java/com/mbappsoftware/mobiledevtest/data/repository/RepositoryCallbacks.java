package com.mbappsoftware.mobiledevtest.data.repository;

import com.mbappsoftware.mobiledevtest.data.response.GitHubRepository;

import java.util.List;

public interface RepositoryCallbacks {
    void onSuccess(List<GitHubRepository> gitHubRepositories);
}
