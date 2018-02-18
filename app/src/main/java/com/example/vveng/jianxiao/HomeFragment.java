package com.example.vveng.jianxiao;


import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vveng.jianxiao.view.customizeview.MaterialSearchView;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;

    @BindView(R.id.home_cdlayout)
    CoordinatorLayout homeCdlayout;
    Unbinder unbinder;
    @BindView(R.id.home_superrefresh)
    SuperSwipeRefreshLayout homeSuperrefresh;
    @BindView(R.id.home_fab)
    FloatingActionButton homeFab;

    DrawerLayout drawerLayout;
    @BindView(R.id.home_search)
    MaterialSearchView homeSearch;

    public static HomeFragment newInstance(String s1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", s1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        drawerLayout = getActivity().findViewById(R.id.main_drawerLayout);
        unbinder = ButterKnife.bind(this, view);
        //初始化标题栏
        initToolbar();

        //初始化搜索框
        initSearch();
        return view;
    }

    private void initSearch() {
        homeSearch.setVoiceSearch(false);
        homeSearch.setCursorDrawable(R.drawable.home_custom_cursor);
        homeSearch.setEllipsize(true);
        homeSearch.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        homeSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), ""+query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        homeSearch.setMenuItem(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    homeSearch.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        homeToolbar.setTitle(" ");
        ((AppCompatActivity) getActivity()).setSupportActionBar(homeToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_format_back1);
        }
    }

    /**
     * 首页标题栏左上角菜单栏点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
