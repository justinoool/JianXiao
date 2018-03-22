package com.example.vveng.jianxiao.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vveng.jianxiao.R;
import com.example.vveng.jianxiao.model.HomeItemBean;

import com.example.vveng.jianxiao.presenter.home.HomePresenter;
import com.example.vveng.jianxiao.presenter.home.IHomeFragment;
import com.example.vveng.jianxiao.view.adapter.home.HomeAdapter;

import com.example.vveng.jianxiao.view.customizeview.MaterialSearchView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeFragment {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.home_fab)
    FloatingActionButton homeFab;

    private final static String TAG = "HomeFragment";
    private DrawerLayout drawerLayout;
    private MaterialSearchView homeSearch;
    private HomeAdapter adapter;
    private HomePresenter presenter;
    private SmartRefreshLayout homerefreshLayout;
    private static boolean isFirstEnter = true;
    private ArrayList<HomeItemBean> arrayList = new ArrayList<>();

    public static HomeFragment newInstance(String s1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", s1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //只有加上这句话fragment中的onCreateOptionsMenu才会执行
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
         initSearch(view);

       //初始化fab
        initFab();

        //初始化recyclerview
        initRecyclerview();

        //初始话数据
        initdata();

        initSmartFresh(view);
        return view;
    }


    private void initFab() {
        homeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了fab", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSmartFresh(View view) {
        homerefreshLayout  = view.findViewById(R.id.home_srlayout);
        if (isFirstEnter) {
            isFirstEnter = false;
            //触发上拉加载
            homerefreshLayout.autoLoadMore();
        }
        homerefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                presenter.loaddata();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        homerefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
             //   Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_SHORT).show();
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });
    }


    private void initRecyclerview() {
        homeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(getActivity(),arrayList);
        homeRecyclerview.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initdata() {
        presenter = new HomePresenter(getActivity(), this);
        presenter.loaddata();

        Log.d(TAG,"initData:"+arrayList.size());

    }


    /**
     * 初始化搜索框
     */
    private void initSearch(View view) {
        homeSearch = view.findViewById(R.id.home_search);
        homeSearch.closeSearch();
        homeSearch.clearFocus();
        homeSearch.setVoiceSearch(false);
        homeSearch.setCursorDrawable(R.drawable.home_custom_cursor);
        homeSearch.setEllipsize(false);
        homeSearch.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        homeSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), "" + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    /**
     * 设置search的menu
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        homeSearch.setMenuItem(item);
    }


    /**
     * 处理搜索框的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
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

    @Override
    public void loaddata(ArrayList<HomeItemBean> arrayList) {
         adapter.setData(arrayList);
    }
}
