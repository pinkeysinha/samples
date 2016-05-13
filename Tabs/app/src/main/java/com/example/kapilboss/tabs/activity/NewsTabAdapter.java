package com.example.kapilboss.tabs.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kapilboss on 08/04/16.
 */
public class NewsTabAdapter extends FragmentPagerAdapter {
    private String[] newsCategories;
    private NewsListFragment[] newsListFragments;


    public NewsTabAdapter(FragmentManager fm,String[] newsCategories, int clickedArticleId,int clickedArticleCategoryId) {
        super(fm);
        this.newsCategories=newsCategories;
        newsListFragments=new NewsListFragment[newsCategories.length];
        for(int i=0;i<newsCategories.length;i++){
            if(clickedArticleCategoryId==1){
                newsListFragments[i]=NewsListFragment.newInstance(clickedArticleId,clickedArticleCategoryId);
            }else{
                newsListFragments[i]=NewsListFragment.newInstance(i);
            }
        }

    }

    @Override
    public Fragment getItem(int position) {
        return newsListFragments[position];
    }

    @Override
    public int getCount() {
        return newsCategories==null? 0 : newsCategories.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsCategories[position];
    }
}
