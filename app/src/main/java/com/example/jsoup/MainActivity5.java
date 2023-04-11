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

public class MainActivity5 extends AppCompatActivity {
    ListView _2ListView;
    MyList_5 myList_5;
    ArrayList<String> html_5,name_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main5);

        _2ListView = findViewById(R.id._2list_view);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("value");
        String aa = bundle.getString("value");
        get_data(aa);
    }

    public void get_data(String ss){
        html_5 = new ArrayList<>();
        name_5 = new ArrayList<>();
        String uri = "http://10.0.0.50/%E7%94%B5%E8%A7%86%E5%89%A7%E5%9C%BA/"+ss;
        try {
            Document document = Jsoup.connect(uri).get();
            Elements a = document.getElementsByTag("a");
            for (int i = 0; i < a.size(); i++) {

                Element element = a.get(i);
                String href = element.attr("href");
                String dd = element.text();

                html_5.add("http://10.0.0.50/"+href);
                name_5.add(dd);
            }

            myList_5 = new MyList_5(MainActivity5.this,html_5,name_5);
            _2ListView.setAdapter(myList_5);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyList_5 extends BaseAdapter {
        ArrayList<String> name,html;
        Context context;
        LayoutInflater layoutInflater;

        public MyList_5(Context context,ArrayList<String> html,ArrayList<String> name){
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
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.item,null);
            }

            TextView text = convertView.findViewById(R.id.text);
            if (position!=0){

                text.setText(name.get(position));
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity5.this,MainActivity2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("value",html.get(position));
                        intent.putExtra("value",bundle);
                        startActivity(intent);

                    }
                });
            }

            else {
                text.setText("------------------------------------------");
            }





            return convertView;
        }
    }
}