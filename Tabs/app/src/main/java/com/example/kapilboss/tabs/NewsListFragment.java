package com.example.kapilboss.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kapilboss on 08/04/16.
 */
public class NewsListFragment extends Fragment {
    private static String CATEGORY_ID="category_id";
    private static String ARTICLE_ID="article_id";
    private TextView tv;

    public static NewsListFragment newInstance(int articleId, int categoryId){
        NewsListFragment newsListFragment=new NewsListFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(CATEGORY_ID,categoryId);
        bundle.putInt(ARTICLE_ID,articleId);
        newsListFragment.setArguments(bundle);
        return newsListFragment;

    }

    public static NewsListFragment newInstance(int categoryId){
        NewsListFragment newsListFragment=new NewsListFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(CATEGORY_ID,categoryId);
        newsListFragment.setArguments(bundle);
        return newsListFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.news_fragment, container,false);
        tv= (TextView) v.findViewById(R.id.fragment_txt);
        int catid=getArguments().getInt(CATEGORY_ID,-1);
        tv.setText(""+catid);


        return v;
    }
}
