package com.example.jsoup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> html, name;
    MyList myList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar statusBar = new StatusBar(MainActivity.this);
        statusBar.setColor(R.color.transparent);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        listView = findViewById(R.id.listview);
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

                switch (dd) {
                    case "21日播放":
                        break;
                    case "免责声明.txt":
                        break;
                    case "web.config":
                        break;
                    case "分类电影":
                        break;
                    case "印度电影":
                        break;
                    case "奥斯卡系列":
                        break;
                    case "梦工厂":
                        break;
                    case "第89届奥斯卡金像奖系列":
                        break;
                    case "经典系列":
                        break;
                    case "革命电影":
                        break;
                    case "高清影片":
                        break;
                    case "[转到父目录]":
                        break;
                    default:
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
            text.setText(name.get(position));

            switch (name.get(position)) {
                case "2009年":
                case "2010年":
                case "2011年":
                case "2012年":
                case "2013年":
                case "2014年":
                case "2015年":
                case "2016年":
                case "2017年":
                case "2018年":
                case "2019年":
                case "1080P":
                case "4k":
                case "豆瓣电影Top250":
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, movei_list.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("value", name.get(position));
                            intent.putExtra("value", bundle);
                            startActivity(intent);

                        }
                    });
                    break;
                default:
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent intent = new Intent(MainActivity.this, year_month.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("value", name.get(position));
                            intent.putExtra("value", bundle);
                            startActivity(intent);

                        }
                    });
                    break;
            }


            return convertView;
        }
    }

}