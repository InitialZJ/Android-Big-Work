package com.a116042018022.iaccount;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MineActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout llDetail, llAccount, llStat, touxiang, settings, remind, share, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        context = this;
        initView();
    }

    private void initView() {
        llDetail = findViewById(R.id.ll_detail);
        llAccount  = findViewById(R.id.ll_account);
        llStat = findViewById(R.id.ll_stat);
        touxiang = findViewById(R.id.touxiang);
        settings = findViewById(R.id.settings);
        remind = findViewById(R.id.remind);
        share = findViewById(R.id.share);
        about = findViewById(R.id.about);
        llDetail.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llStat.setOnClickListener(this);
        touxiang.setOnClickListener(this);
        settings.setOnClickListener(this);
        remind.setOnClickListener(this);
        share.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_detail:
                startActivity(new Intent(context, MainActivity.class));
                finish();
                break;
            case R.id.ll_account:
                startActivity(new Intent(context, AccountActivity.class));
                finish();
                break;
            case R.id.ll_stat:
                startActivity(new Intent(context, StatActivity.class));
                finish();
                break;
            case R.id.touxiang:
            case R.id.settings:
            case R.id.remind:
            case R.id.share:
            case R.id.about:
                Toast.makeText(context, "此功能待完善", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}