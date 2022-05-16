package com.a116042018022.iaccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.*;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout llDetail, llStat, llMine;
    private TextView tvLhq, tvFk, tvZfb, tvWx, tvQt, timeModify;
    private MyDbHelper helper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private long t;
    private Map<Integer, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        context = this;
        helper = new MyDbHelper(context);
        map.put(R.id.tv_lhq, "零花钱");
        map.put(R.id.tv_fk, "饭卡");
        map.put(R.id.tv_zfb, "支付宝");
        map.put(R.id.tv_wx, "微信");
        map.put(R.id.tv_qt, "其他");
        initView();
    }

    private void initView() {
        llDetail = findViewById(R.id.ll_detail);
        llStat = findViewById(R.id.ll_stat);
        llMine = findViewById(R.id.ll_mine);
        tvLhq = findViewById(R.id.tv_lhq);
        tvFk = findViewById(R.id.tv_fk);
        tvZfb = findViewById(R.id.tv_zfb);
        tvWx = findViewById(R.id.tv_wx);
        tvQt = findViewById(R.id.tv_qt);
        timeModify = findViewById(R.id.time_modify);
        llDetail.setOnClickListener(this);
        llStat.setOnClickListener(this);
        llMine.setOnClickListener(this);
        tvLhq.setOnClickListener(this);
        tvFk.setOnClickListener(this);
        tvZfb.setOnClickListener(this);
        tvWx.setOnClickListener(this);
        tvQt.setOnClickListener(this);
        tvLhq.setText(String.format(Locale.CHINA, "%.2f", (double) helper.queryAccount("零花钱") / 100));
        tvFk.setText(String.format(Locale.CHINA, "%.2f", (double) helper.queryAccount("饭卡") / 100));
        tvZfb.setText(String.format(Locale.CHINA, "%.2f", (double) helper.queryAccount("支付宝") / 100));
        tvWx.setText(String.format(Locale.CHINA, "%.2f", (double) helper.queryAccount("微信") / 100));
        tvQt.setText(String.format(Locale.CHINA, "%.2f", (double) helper.queryAccount("其他") / 100));
        t = helper.queryTimeModify();
        if (t != 0)
            timeModify.setText("上次修改时间：" + sdf.format(new Date(t)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_detail:
                startActivity(new Intent(context, MainActivity.class));
                finish();
                break;
            case R.id.ll_stat:
                startActivity(new Intent(context, StatActivity.class));
                finish();
                break;
            case R.id.ll_mine:
                startActivity(new Intent(context, MineActivity.class));
                finish();
                break;
            case R.id.tv_lhq:
            case R.id.tv_fk:
            case R.id.tv_zfb:
            case R.id.tv_wx:
            case R.id.tv_qt:
                showD(view.getId());
                break;
        }
    }

    public void showD(int id) {
        MyDialog dialog = new MyDialog(context, R.style.mdialog, new MyDialog.OncloseListener() {
            @Override
            public void onClick(boolean confirm) {
                if (confirm) {
                    SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                    int number = sp.getInt("number", 0);
                    helper.updateAccount(number, map.get(id));
                    TextView tv = findViewById(id);
                    tv.setText(String.format(Locale.CHINA, "%.2f", (double) number / 100));
                    Calendar ca = Calendar.getInstance();
                    timeModify.setText("上次修改时间：" + sdf.format(ca.getTime()));
                    helper.updateTimeModify(ca.getTimeInMillis());
                }
            }
        });
        dialog.show();
    }
}