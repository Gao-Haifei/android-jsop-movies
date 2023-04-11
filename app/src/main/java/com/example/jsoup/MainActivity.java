 package com.example.jsoup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> html, name;
    MyList myList;
    _MyList _myList;
    ListView listView, _listView;
    TabLayout mytab;
    



    public void MyTab() {
        mytab.addTab(mytab.newTab().setText("电影"));
        mytab.addTab(mytab.newTab().setText("电视剧"));
        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("电影")) {
                    listView.setVisibility(View.VISIBLE);

                } else {
                    _listView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getText().equals("电影")) {
                    listView.setVisibility(View.GONE);
                } else {
                    _listView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        mytab = findViewById(R.id.mytable);
        listView = findViewById(R.id.listview);
        _listView = findViewById(R.id._listview);

        MyTab();
        _get_data();
        get_data();


    }

    public void get_data() {
        html = new ArrayList<>();
        name = new ArrayList<>();
        String uri = "http://10.0.0.50/%E7%94%B5%E5%BD%B1%E9%A2%91%E9%81%93/";
        try {
            Document document = Jsoup.connect(uri).get();
            Elements a = document.getElementsByTag("a");
            for (int i = 0; i < a.size(); i++) {

                Element element = a.get(i);
                String href = element.attr("href");
                String dd = element.text();
                String[] stringArray = getResources().getStringArray(R.array.titles);
                HashSet<String> set = new HashSet<>();
                set.addAll(Arrays.asList(stringArray));
                if (set.contains(dd)) {
                    html.add("http://10.0.0.50" + href);
                    name.add(dd);
                }
            }
            myList = new MyList(MainActivity.this, html, name);
            listView.setAdapter(myList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void _get_data() {
        html = new ArrayList<>();
        name = new ArrayList<>();
        String uri = "http://10.0.0.50/%E7%94%B5%E8%A7%86%E5%89%A7%E5%9C%BA/";
        try {
            Document document = Jsoup.connect(uri).get();
            Elements a = document.getElementsByTag("a");
            for (int i = 2; i < a.size(); i++) {

                Element element = a.get(i);
                String href = element.attr("href");
                String dd = element.text();

                html.add("http://10.0.0.50" + href);
                name.add(dd);
            }
            _myList = new _MyList(MainActivity.this, html, name);
            _listView.setAdapter(_myList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class _MyList extends BaseAdapter {
        ArrayList<String> name, html;
        Context context;
        LayoutInflater layoutInflater;

        public _MyList(Context context, ArrayList<String> html, ArrayList<String> name) {
            this.context = context;
            this.html = html;
            this.name = name;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return name.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.item, null);
            }


            TextView text = convertView.findViewById(R.id.text);

            text.setText(name.get(position));

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("value", name.get(position));
                    intent.putExtra("value", bundle);
                    startActivity(intent);

                }
            });


            return convertView;
        }
    }

    public class MyList extends BaseAdapter {
        ArrayList<String> name, html;
        Context context;
        LayoutInflater layoutInflater;

        public MyList(Context context, ArrayList<String> html, ArrayList<String> name) {
            this.context = context;
            this.html = html;
            this.name = name;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return name.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.item, null);
            }


            TextView text = convertView.findViewById(R.id.text);
            if (position < 11) {
                text.setText(name.get(position));

                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("value", name.get(position));
                        intent.putExtra("value", bundle);
                        startActivity(intent);

                    }
                });
            } else if (position < 14) {
                text.setText(name.get(position));

                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("value", name.get(position));
                        intent.putExtra("value", bundle);
                        startActivity(intent);

                    }
                });
            }
            else {
                text.setText(name.get(position));

                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("value", name.get(position));
                        intent.putExtra("value", bundle);
                        startActivity(intent);

                    }
                });
            }


            return convertView;
        }
    }

}