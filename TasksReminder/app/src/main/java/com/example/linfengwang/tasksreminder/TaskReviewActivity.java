package com.example.linfengwang.tasksreminder;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linfengwang.tasksreminder.Fragment.TaskFinishedFragment;
import com.example.linfengwang.tasksreminder.Fragment.TaskInboxFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class TaskReviewActivity extends AppCompatActivity {
    private static final int NUMBER_PAGES =2;
    private SimpleDraweeView taskPhoto;
    private ViewPager taskPager;
    private TabLayout tabLayout;

    private Typeface typefaceMedium;
    private Typeface typefaceRegular;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_review_layout);
        taskPhoto = findViewById(R.id.task_photo);
        taskPager = findViewById(R.id.task_category_pager);
        setUpViewPager(taskPager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(taskPager);
        taskPhoto.setImageURI(Uri.parse("https://images.chinahighlights.com/allpicture/2014/08/16f810e32fbd41a68de7eb57_298x207.jpg"));
        //back button;
        Toolbar toolbar = findViewById(R.id.resume_task_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            Drawable sourceImage = getResources().getDrawable(R.drawable.ic_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(sourceImage);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        //get typeface;
        typefaceMedium = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand);
        typefaceRegular = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand_medium);

        capitalizeTabTitleText();
        updateTabFont(0);

        tabLayout.addOnTabSelectedListener(new TabSelectedCallback());

    }

    private void capitalizeTabTitleText() {
        for (int i = 0; i < tabLayout.getTabCount(); ++i) {
            TextView tabTextView =
                    (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(
                            i)).getChildAt(1));
            tabTextView.setAllCaps(false);
        }
    }


    private void updateTabFont(final int tabPosition) {
        TextView tabTextViewFirst =
                (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(
                        0)).getChildAt(1));
        TextView tabTextViewSecond =
                (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(
                        1)).getChildAt(1));

        if (tabPosition == 0) {
            tabTextViewFirst.setTypeface(typefaceMedium);
            tabTextViewSecond.setTypeface(typefaceRegular);
        } else {
            tabTextViewSecond.setTypeface(typefaceMedium);
            tabTextViewFirst.setTypeface(typefaceRegular);
        }
    }

    public void setUpViewPager(ViewPager viewPager){
        TaskResumeAdapter pagerAdapter = new TaskResumeAdapter(getSupportFragmentManager());
        TaskFinishedFragment finishedFragment = new TaskFinishedFragment();
        pagerAdapter.loadFragment(finishedFragment,getResources().getString(R.string.task_finished));
        TaskInboxFragment unfinishedFragment = new TaskInboxFragment();
        pagerAdapter.loadFragment(unfinishedFragment,getResources().getString(R.string.task_inbox));
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, TaskActivity.class));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public class TaskResumeAdapter extends FragmentPagerAdapter {
         List<Fragment> fragmentList = new ArrayList<>();
         List<String> titleList = new ArrayList<>();
         TaskResumeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return NUMBER_PAGES;
        }
        @Override
        public CharSequence getPageTitle(int position){
            return titleList.get(position);
        }

        private void loadFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }
    }

    private class TabSelectedCallback implements TabLayout.OnTabSelectedListener {

        @Override public void onTabSelected(TabLayout.Tab tab) {
            updateTabFont(tab.getPosition());
        }

        @Override public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override public void onTabReselected(TabLayout.Tab tab) {
        }
    }
}
