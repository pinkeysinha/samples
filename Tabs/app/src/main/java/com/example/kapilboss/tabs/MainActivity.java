package com.example.kapilboss.tabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    protected TabLayout tabLayout;
    private ViewPager viewPager;
    protected NewsTabAdapter tabAdapter;
    private int articleId;
    private int categoryId;
    private String newsCategories[]=new String[]{"All","India","Science"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout= (TabLayout) findViewById(R.id.feed_tab);
        articleId=11;
        categoryId=1;
       viewPager= (ViewPager) findViewById(R.id.feed_pager);
        tabAdapter=new NewsTabAdapter(getSupportFragmentManager(),newsCategories,articleId,categoryId);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        /*
        * set app:tabMode="scrollable" in layout to support scrolling
        * but remove aboveline if you dont want to scrolltabs and want
        * to divide the space equally
        * */
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setCurrentItem(1);

        if(tabLayout.getTabAt(0)!=null){
            View v = LayoutInflater.from(this).inflate(R.layout.message_tab, null);
            TextView tv = (TextView)v.findViewById(R.id.txt);
            tv.setText("Kapil");
            TextView msgCount = (TextView)v.findViewById(R.id.msg_count);
            msgCount.setText("4");
            TabLayout.Tab tab=tabLayout.getTabAt(0);
            tab.setCustomView(v);

        }
        if(tabLayout.getTabAt(1)!=null){
            View v = LayoutInflater.from(this).inflate(R.layout.message_tab, null);
            TextView tv = (TextView)v.findViewById(R.id.txt);
            tv.setText("Minal");
            TextView msgCount = (TextView)v.findViewById(R.id.msg_count);
            msgCount.setText("6");
            TabLayout.Tab tab=tabLayout.getTabAt(1);
            tab.setCustomView(v);

        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.d(TAG,"Tab position:"+tab.getPosition());
                if(tab.getPosition()<2) {
                    View v = tab.getCustomView();
                    TextView tv = (TextView)v.findViewById(R.id.txt);
                    tv.setSelected(true);
                    TextView msgCount = (TextView) v.findViewById(R.id.msg_count);
                   msgCount.setVisibility(View.INVISIBLE);
                    Log.d(TAG,"Hidden:"+tab.getPosition());
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
