package com.dramascript.dlibs.view.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.LogUtils;
import com.dramascript.dlibrary.base.DInject;
import com.dramascript.dlibrary.utils.StatusBarUtils;
import com.dramascript.dlibs.R;
import com.dramascript.dlibs.imp.ImpBaseActivity;
import com.dramascript.dlibs.view.fragment.ImageStatebarFragment;
import com.dramascript.dlibs.view.fragment.NormalStatebarFragment;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

@DInject(
        contentViewId = R.layout.ac_fginac
)
public class StateInFragmentActivity extends ImpBaseActivity {

    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void init() {
        super.init();
        if (StatusBarUtils.isFlyme()&& Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT){ // 魅族pro  4.4.4  直接调用
            LogUtils.e("----------我是pro4");
            StatusBarUtils.setWhiteStateBar(StateInFragmentActivity.this);
        }

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_favorite, "One"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_gavel, "Two"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_grade, "Three"))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mVpHome.setCurrentItem(position);
                if (!(StatusBarUtils.isFlyme()&& Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT)){
                    if (position==2){
                        StatusBarUtils.setWhiteStateBar(StateInFragmentActivity.this);
                    }else {
                        StatusBarUtil.setTranslucentForImageViewInFragment(StateInFragmentActivity.this, null);
                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        mFragmentList.add(new ImageStatebarFragment());
        mFragmentList.add(NormalStatebarFragment.newInstance(0));
        mFragmentList.add(NormalStatebarFragment.newInstance(1));

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
                switch (position) {
                    case 0:
                        break;
                    default:
                        /*Random random = new Random();
                        int color = 0xff000000 | random.nextInt(0xffffff);
                        if (mFragmentList.get(position) instanceof SimpleFragment) {
                            ((SimpleFragment) mFragmentList.get(position)).setTvTitleBackgroundColor(color);
                        }*/
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

}
