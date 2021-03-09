package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtest.DatabaseHelper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<DataEntry> dataEntryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        db = new DatabaseHelper(this);
        dataEntryArrayList = db.getAllData();

        ListView listView = (ListView) findViewById(R.id.listView);
        final CustomAdaptor customAdaptor = new CustomAdaptor();
        listView.setAdapter(customAdaptor);

    }

    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return dataEntryArrayList.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_list,null);

            TextView tvFname = (TextView) convertView.findViewById(R.id.etfName);
            TextView tvLname = (TextView) convertView.findViewById(R.id.etlName);
            TextView tvEmail = (TextView) convertView.findViewById(R.id.etemail);
            TextView tvPhoneno = (TextView) convertView.findViewById(R.id.etphoneno);

            tvFname.setText(dataEntryArrayList.get(position).getFname());
            tvLname.setText(dataEntryArrayList.get(position).getLname());
            tvEmail.setText(dataEntryArrayList.get(position).getEmail());
            tvPhoneno.setText(dataEntryArrayList.get(position).getPhoneno());

            return convertView;
        }
    }
}

