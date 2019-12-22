package com.example.coccochometest.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.coccochometest.MainActivity;
import com.example.coccochometest.R;
import com.example.coccochometest.model.RssFeed;
import com.example.coccochometest.ui.adapter.RssFeedAdapter;
import com.example.coccochometest.ui.newdetail.NewDetailFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

public class HomeFragment extends MvpLceFragment<FrameLayout, RssFeed, HomeView, HomePresenter> implements HomeView {

    private RssFeedAdapter mAdapter;
    private SwipeRefreshLayout refreshLayout;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.home_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LayoutMarginDecoration(1, 16));
        mAdapter = new RssFeedAdapter(getContext(), item -> ((MainActivity) getActivity()).addFragment(NewDetailFragment.newInstance(item.getMlink()), HomeFragment.class.getSimpleName()));
        mRecyclerView.setAdapter(mAdapter);

        refreshLayout = view.findViewById(R.id.swipeToRefresh);
        refreshLayout.setOnRefreshListener(() -> loadData(true));

        loadData(false);
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }


    @Override
    public void setData(RssFeed data) {
        refreshLayout.setRefreshing(false);
        mAdapter.setItems(data.getmChannel().getFeedItems());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().fetchDataFromServer(pullToRefresh);
    }
}
