package com.example.vveng.jianxiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.vveng.jianxiao.utils.GlideImageLoder;
import com.example.vveng.jianxiao.chat.ChatFragment;
import com.example.vveng.jianxiao.home.view.HomeFragment;
import com.example.vveng.jianxiao.raiders.RaidersFragment;
import com.example.vveng.jianxiao.sell.SellRoommateFragment;
import com.jaeger.library.StatusBarUtil;
import com.lzy.ninegrid.NineGridView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.main_replace)
    FrameLayout mainReplace;
    @BindView(R.id.main_bottom_navigation_bar)
    BottomNavigationBar mainBottomNavigationBar;
    @BindView(R.id.main_nav_view)
    NavigationView mainNavView;
    @BindView(R.id.main_drawerLayout)
    DrawerLayout mainDrawerLayout;

    private HomeFragment homeFragment;
    private RaidersFragment raidersFragment;
    private ChatFragment chatFragment;
    private SellRoommateFragment sellRoommateFragment;
    public static Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NineGridView.setImageLoader(new GlideImageLoder());

        StatusBarUtil.setColorForDrawerLayout(this, mainDrawerLayout, 0, 0);
        Initbottombar(); //初始化底部导航栏
        NavigationViewListenter(); //底部导航栏的监听
    }

    /**
     * 底部导航栏的监听
     */
    private void NavigationViewListenter() {
        mainNavView.setItemIconTintList(null);//设置后icon的颜色就是默认的了
        mainNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_collection:
                        Toast.makeText(MainActivity.this, "实在不好意思尚未开发，你点设置试试", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_food:
                        Toast.makeText(MainActivity.this, "实在不好意思尚未开发，你点设置试试", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_my:
                        Toast.makeText(MainActivity.this, "实在不好意思尚，你点设置试试", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(MainActivity.this, "实在不好意思尚，你点设置试试", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });
    }

    /**
     * 左上角的侧滑菜单按钮的监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mainDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
                break;
        }
        return true;
    }

    /**
     * 初始化底部导航栏
     */
    private void Initbottombar() {
        mainBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mainBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        // mainBottomNavigationBar.setBarBackgroundColor(R.color.lncRed);

        mainBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, "首页").setActiveColorResource(R.color.lncRed))
                .addItem(new BottomNavigationItem(R.mipmap.ic_raiders, "攻略").setActiveColorResource(R.color.lncRed))
                .addItem(new BottomNavigationItem(R.mipmap.ic_chat, "消息").setActiveColorResource(R.color.lncRed))
                .addItem(new BottomNavigationItem(R.mipmap.ic_hot, "看点").setActiveColorResource(R.color.lncRed))
                .setFirstSelectedPosition(0)
                .initialise();
        mainBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();

    }

    /**
     * 初始化首页
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        homeFragment = homeFragment.newInstance(getString(R.string.home));
        transaction.replace(R.id.main_replace, homeFragment);
        transaction.commit();
        mCurrentFragment = homeFragment;
    }


    /**
     * 底部导航栏选中
     *
     * @param position 注意：transaction.add 和 replace
     *                 add 是把一个fragment添加到一个容器 container 里。
     *                 replace 是先remove掉相同id的所有fragment，然后在add当前的这个fragment。
     */
    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideAllFragment(transaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance(getString(R.string.home));
                    transaction.add(R.id.main_replace, homeFragment);
                    mCurrentFragment = homeFragment;
                } else {
                    mCurrentFragment = homeFragment;
                    transaction.show(homeFragment);
                }
                transaction.addToBackStack(null);
                break;
            case 1:
                if (raidersFragment == null) {
                    raidersFragment = RaidersFragment.newInstance(getString(R.string.raiders));
                    transaction.add(R.id.main_replace, raidersFragment);
                    mCurrentFragment = raidersFragment;
                } else {
                    mCurrentFragment = raidersFragment;
                    transaction.show(raidersFragment);
                }
                transaction.addToBackStack(null);
                break;
            case 2:
                if (chatFragment == null) {
                    chatFragment = ChatFragment.newInstance(getString(R.string.chat));
                    transaction.add(R.id.main_replace, chatFragment);
                    mCurrentFragment = chatFragment;
                } else {
                    mCurrentFragment = chatFragment;
                    transaction.show(chatFragment);
                }
                transaction.addToBackStack(null);
                break;
            case 3:
                if (sellRoommateFragment == null) {
                    sellRoommateFragment = SellRoommateFragment.newInstance(getString(R.string.hot));
                    transaction.add(R.id.main_replace, sellRoommateFragment);
                    mCurrentFragment = sellRoommateFragment;
                } else {
                    mCurrentFragment = sellRoommateFragment;
                    transaction.show(sellRoommateFragment);
                }
                transaction.addToBackStack(null);
                break;
            default:
                if (homeFragment == null) {
                    homeFragment = homeFragment.newInstance(getString(R.string.home));
                    transaction.add(R.id.main_replace, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) fragmentTransaction.hide(homeFragment);
        if (raidersFragment != null) fragmentTransaction.hide(raidersFragment);
        if (chatFragment != null) fragmentTransaction.hide(chatFragment);
        if (sellRoommateFragment != null) fragmentTransaction.hide(sellRoommateFragment);
    }

    /**
     * 底部导航栏未选中
     *
     * @param position
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 底部导航栏重新选中
     *
     * @param position
     */
    @Override
    public void onTabReselected(int position) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    //重写onBackPressed实现按下物理back键退出程序切换后台但是不杀死程序
    @Override
    public void onBackPressed() {

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }
}
