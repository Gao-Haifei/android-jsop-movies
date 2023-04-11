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

public class MainActivity3 extends AppCompatActivity {
    ListView listView_2;
    MyList_2 myList_2;
    ArrayList<String> name_2, html_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main3);

        listView_2 = findViewById(R.id.list_view_2);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("value");
        String aa = bundle.getString("value");
        get_data(aa);
    }


    public void get_data(String ss) {
        html_2 = new ArrayList<>();
        name_2 = new ArrayList<>();
        String uri = "http://10.0.0.50/%E7%94%B5%E5%BD%B1%E9%A2%91%E9%81%93/" + ss;
        try {
            Document document = Jsoup.connect(uri).get();
            Elements a = document.getElementsByTag("a");
            for (int i = 1; i < a.size(); i++) {

                Element element = a.get(i);
                String href = element.attr("href");
                String dd = element.text();

                html_2.add("http://10.0.0.50/" + href);
                name_2.add(dd);
            }

            myList_2 = new MyList_2(MainActivity3.this, html_2, name_2);
            listView_2.setAdapter(myList_2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyList_2 extends BaseAdapter {
        ArrayList<String> name, html;
        Context context;
        LayoutInflater layoutInflater;

        public MyList_2(Context context, ArrayList<String> html, ArrayList<String> name) {
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
                    Intent intent = new Intent();
                    intent.setClass(MainActivity3.this, MainActivity2.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("value", html.get(position));
                    intent.putExtra("value", bundle);
                    startActivity(intent);

                }
            });


            return convertView;
        }
    }

}