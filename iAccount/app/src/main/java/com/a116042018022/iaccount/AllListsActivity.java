package com.a116042018022.iaccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AllListsActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private ListView mListView;
    private ImageView ivBack;
    private long dateBeign, dateEnd;
    private ArrayList<String> kinds;
    private ArrayList<Integer> numbers;
    private ArrayList<Long> times;
    private MyDbHelper helper;
    private LinearLayout llZhichu;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lists);
        context = this;
        helper = new MyDbHelper(context);
        initView();
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        dateBeign = sp.getLong("dateBegin", 0);
        dateEnd = sp.getLong("dateEnd", 0);
        helper.queryList("shouru", dateBeign, dateEnd);
        kinds = helper.getKinds();
        numbers = helper.getNumbers();
        times = helper.getTimes();
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
    }

    private void initView() {
        mListView = findViewById(R.id.lv);
        ivBack = findViewById(R.id.iv_back);
        llZhichu = findViewById(R.id.ll_zhichu);
        ivBack.setOnClickListener(this);
        llZhichu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_zhichu:
                startActivity(new Intent(context, AllListsActivity_zhichu.class));
                finish();
                break;
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return kinds.size();
        }

        @Override
        public Object getItem(int position) {
            return kinds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.list_item, null);
            ImageView ivIcon = view.findViewById(R.id.iv_icon);
            ivIcon.setBackgroundResource(R.drawable.icon_shouru_type_gongzi);
            TextView tvKind = view.findViewById(R.id.tv_kind);
            tvKind.setText(kinds.get(position));
            TextView tvNumber = view.findViewById(R.id.tv_number);
            tvNumber.setText(String.format(Locale.CHINA, "%.2f", (double) numbers.get(position) / 100));
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd HH:mm");
            TextView tvTime = view.findViewById(R.id.tv_time);
            tvTime.setText(sdf.format(new Date(times.get(position))));
            return view;
        }
    }
}