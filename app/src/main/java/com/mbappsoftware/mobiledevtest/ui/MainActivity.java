package com.mbappsoftware.mobiledevtest.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mbappsoftware.mobiledevtest.R;
import com.mbappsoftware.mobiledevtest.data.repository.Repository;
import com.mbappsoftware.mobiledevtest.data.response.GitHubRepository;
import com.mbappsoftware.mobiledevtest.ui.adapter.RepositoryAdapter;
import com.mbappsoftware.mobiledevtest.ui.viewmodel.MainActivityViewModel;
import com.mbappsoftware.mobiledevtest.ui.viewmodel.MainActivityViewModelFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvGitHub;
    private MainActivityViewModel viewModel;
    private List<GitHubRepository> repositories;
    private Toolbar toolbar;
    private Button btnSearch;
    private EditText etSearch;
    private TextView tvNonItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGitHub = findViewById(R.id.rv_github);
        toolbar = findViewById(R.id.toolbar);
        btnSearch = findViewById(R.id.btn_search);
        etSearch = findViewById(R.id.et_search);
        tvNonItems = findViewById(R.id.tv_non_items);

        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        viewModel =
                new ViewModelProvider(this,
                        new MainActivityViewModelFactory(
                                new Repository())).get(MainActivityViewModel.class);

        viewModel.getGitHubRepositoryLiveData().observe(this, new Observer() {
            @SuppressWarnings("unchecked")
            @Override
            public void onChanged(Object o) {
                repositories = (List<GitHubRepository>) o;
                callAdapter(repositories);
            }
        });

        viewModel.getRepositories("Java", "stars", "1");

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFilteredRepositories(etSearch.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        tvNonItems.setVisibility(View.GONE);
        rvGitHub.setVisibility(View.VISIBLE);
        switch (item.getItemId()) {
            case R.id.btn_sort_name:
                viewModel.getRepositories("Java", "name", "1");
                break;
            case R.id.btn_sort_star:
                viewModel.getRepositories("Java", "stars", "1");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void callAdapter(List<GitHubRepository> repositories) {
        RepositoryAdapter adapter = new RepositoryAdapter(this.repositories);
        rvGitHub.setHasFixedSize(true);
        rvGitHub.setLayoutManager(new LinearLayoutManager(this));
        rvGitHub.setAdapter(adapter);
    }

    private void getFilteredRepositories(String filterText) {
        List<GitHubRepository> gitHubRepositories = new ArrayList<>();

        for (GitHubRepository gitHubRepository : repositories) {
            if (gitHubRepository.getName().contains(filterText.trim())
                    || gitHubRepository.getOwner().getUserName().contains(filterText.trim())) {
                gitHubRepositories.add(gitHubRepository);
            }
        }

        if (gitHubRepositories.isEmpty() || filterText.equals("")) {
            tvNonItems.setVisibility(View.VISIBLE);
            rvGitHub.setVisibility(View.GONE);
            gitHubRepositories.clear();
            repositories.clear();
        }

        viewModel.setGitHubRepositoryLiveData(gitHubRepositories);
    }
}