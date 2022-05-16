package com.a116042018022.iaccount;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Locale;

public class StatActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout llZhichu, llDetail, llAccount, llMine, llShf, llGz, llQt;
    private TextView dateRange, shf1, shf2, gz1, gz2, qt1, qt2;
    private String[] items = {"天", "周", "月", "季", "年"};
    private Calendar calBegin, calEnd;
    private long dateBeign, dateEnd;
    int shf, gz, qt;
    private MyDbHelper helper;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        context = this;
        helper = new MyDbHelper(context);
        initView();
        setDate(0);
    }

    private void initView() {
        llZhichu = findViewById(R.id.ll_zhichu);
        llDetail = findViewById(R.id.ll_detail);
        llAccount = findViewById(R.id.ll_account);
        llMine = findViewById(R.id.ll_mine);
        llShf = findViewById(R.id.ll_shf);
        llGz = findViewById(R.id.ll_gz);
        llQt = findViewById(R.id.ll_qt);
        dateRange = findViewById(R.id.date_range);
        shf1 = findViewById(R.id.tv_shf1);
        shf2 = findViewById(R.id.tv_shf2);
        gz1 = findViewById(R.id.tv_gz1);
        gz2 = findViewById(R.id.tv_gz2);
        qt1 = findViewById(R.id.tv_qt1);
        qt2 = findViewById(R.id.tv_qt2);
        llZhichu.setOnClickListener(this);
        llDetail.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llMine.setOnClickListener(this);
        llShf.setOnClickListener(this);
        llGz.setOnClickListener(this);
        llQt.setOnClickListener(this);
        dateRange.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zhichu:
                startActivity(new Intent(context, StatActivity_zhichu.class));
                finish();
                break;
            case R.id.ll_detail:
                startActivity(new Intent(context, MainActivity.class));
                finish();
                break;
            case R.id.ll_account:
                startActivity(new Intent(context, AccountActivity.class));
                finish();
                break;
            case R.id.ll_mine:
                startActivity(new Intent(context, MineActivity.class));
                finish();
                break;
            case R.id.date_range:
                new AlertDialog.Builder(context)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                setDate(i);
                            }
                        })
                        .show();
                break;
            case R.id.ll_shf:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "shouru");
                editor.putString("kind", "生活费");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
            case R.id.ll_gz:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "shouru");
                editor.putString("kind", "工资");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
            case R.id.ll_qt:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "shouru");
                editor.putString("kind", "其他");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
        }
    }

    private void setDate(int i) {
        calBegin = Calendar.getInstance();
        calEnd = Calendar.getInstance();
        switch (i) {
            case 0:
                calBegin.set(Calendar.HOUR_OF_DAY, 0);
                calBegin.set(Calendar.MINUTE, 0);
                calBegin.set(Calendar.SECOND, 0);
                calEnd.set(Calendar.MILLISECOND, 0);
                calEnd.set(Calendar.HOUR_OF_DAY, 23);
                calEnd.set(Calendar.MINUTE, 59);
                calEnd.set(Calendar.SECOND, 59);
                calEnd.set(Calendar.MILLISECOND, 999);
                dateRange.setText(new StringBuffer(String.valueOf(calBegin.get(Calendar.MONTH) + 1))
                        .append(".").append(calBegin.get(Calendar.DAY_OF_MONTH)));
                break;
            case 1:
                calBegin.setFirstDayOfWeek(Calendar.MONDAY);
                calBegin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calBegin.set(Calendar.HOUR_OF_DAY, 0);
                calBegin.set(Calendar.MINUTE, 0);
                calBegin.set(Calendar.SECOND, 0);
                calEnd.set(Calendar.MILLISECOND, 0);
                calEnd.setFirstDayOfWeek(Calendar.MONDAY);
                calEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                calEnd.set(Calendar.HOUR_OF_DAY, 23);
                calEnd.set(Calendar.MINUTE, 59);
                calEnd.set(Calendar.SECOND, 59);
                calEnd.set(Calendar.MILLISECOND, 999);
                dateRange.setText(new StringBuffer(String.valueOf(calBegin.get(Calendar.MONTH) + 1))
                        .append(".").append(calBegin.get(Calendar.DAY_OF_MONTH)).append(" ~ ")
                        .append(calEnd.get(Calendar.MONTH) + 1).append(".")
                        .append(calEnd.get(Calendar.DAY_OF_MONTH)));
                break;
            case 2:
                calBegin.set(Calendar.DAY_OF_MONTH, 1);
                calBegin.set(Calendar.HOUR_OF_DAY, 0);
                calBegin.set(Calendar.MINUTE, 0);
                calBegin.set(Calendar.SECOND, 0);
                calEnd.set(Calendar.MILLISECOND, 0);
                calEnd.set(Calendar.DAY_OF_MONTH, calBegin.getActualMaximum(Calendar.DAY_OF_MONTH));
                calEnd.set(Calendar.HOUR_OF_DAY, 23);
                calEnd.set(Calendar.MINUTE, 59);
                calEnd.set(Calendar.SECOND, 59);
                calEnd.set(Calendar.MILLISECOND, 999);
                dateRange.setText(new StringBuffer(String.valueOf(calBegin.get(Calendar.MONTH) + 1))
                        .append(".").append(calBegin.get(Calendar.DAY_OF_MONTH)).append(" ~ ")
                        .append(calEnd.get(Calendar.MONTH) + 1).append(".")
                        .append(calEnd.get(Calendar.DAY_OF_MONTH)));
                break;
            case 3:
                int currentMonth = calBegin.get(Calendar.MONTH) + 1;
                if (currentMonth >= 1 && currentMonth <=3)
                    calBegin.set(Calendar.MONTH, 0);
                else if (currentMonth >= 4 && currentMonth <=6)
                    calBegin.set(Calendar.MONTH, 3);
                else if (currentMonth >= 7 && currentMonth <=9)
                    calBegin.set(Calendar.MONTH, 6);
                else if (currentMonth >= 10 && currentMonth <=12)
                    calBegin.set(Calendar.MONTH, 9);
                calBegin.set(Calendar.DAY_OF_MONTH, 1);
                calBegin.set(Calendar.HOUR_OF_DAY, 0);
                calBegin.set(Calendar.MINUTE, 0);
                calBegin.set(Calendar.SECOND, 0);
                calEnd.set(Calendar.MILLISECOND, 0);
                calEnd.set(Calendar.MONTH, calBegin.get(Calendar.MONTH) + 2);
                calEnd.set(Calendar.DAY_OF_MONTH, calBegin.getActualMaximum(Calendar.DAY_OF_MONTH));
                calEnd.set(Calendar.HOUR_OF_DAY, 23);
                calEnd.set(Calendar.MINUTE, 59);
                calEnd.set(Calendar.SECOND, 59);
                calEnd.set(Calendar.MILLISECOND, 999);
                dateRange.setText(new StringBuffer(String.valueOf(calBegin.get(Calendar.MONTH) + 1))
                        .append(".").append(calBegin.get(Calendar.DAY_OF_MONTH)).append(" ~ ")
                        .append(calEnd.get(Calendar.MONTH) + 1).append(".")
                        .append(calEnd.get(Calendar.DAY_OF_MONTH)));
                break;
            case 4:
                calBegin.set(Calendar.DAY_OF_YEAR, 1);
                calBegin.set(Calendar.HOUR_OF_DAY, 0);
                calBegin.set(Calendar.MINUTE, 0);
                calBegin.set(Calendar.SECOND, 0);
                int currentYear = calEnd.get(Calendar.YEAR);
                calEnd.clear();
                calEnd.set(Calendar.YEAR, currentYear);
                calEnd.set(Calendar.MILLISECOND, 0);
                calEnd.set(Calendar.HOUR_OF_DAY, 23);
                calEnd.set(Calendar.MINUTE, 59);
                calEnd.set(Calendar.SECOND, 59);
                calEnd.set(Calendar.MILLISECOND, 999);
                calEnd.roll(Calendar.DAY_OF_YEAR, -1);
                dateRange.setText(new StringBuffer(String.valueOf(calBegin.get(Calendar.MONTH) + 1))
                        .append(".").append(calBegin.get(Calendar.DAY_OF_MONTH)).append(" ~ ")
                        .append(calEnd.get(Calendar.MONTH) + 1).append(".")
                        .append(calEnd.get(Calendar.DAY_OF_MONTH)));
                break;
        }
        dateBeign = calBegin.getTimeInMillis();
        dateEnd = calEnd.getTimeInMillis();
        BtFragment mBt_fragment = helper.queryDate("shouru", dateBeign, dateEnd);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mBt_fragment).commit();
        shf = mBt_fragment.getShf();
        gz = mBt_fragment.getGz();
        qt = mBt_fragment.getQt();
        long sum = shf + gz + qt;
        double a = (double) shf / sum;
        double b = (double) gz / sum;
        double c = (double) qt / sum;
        shf1.setText(String.format(Locale.CHINA, "%.1f%%", a * 100));
        gz1.setText(String.format(Locale.CHINA, "%.1f%%", b * 100));
        qt1.setText(String.format(Locale.CHINA, "%.1f%%", c * 100));
        shf2.setText(String.format(Locale.CHINA, "%.2f", (double) shf / 100));
        gz2.setText(String.format(Locale.CHINA, "%.2f", (double) gz / 100));
        qt2.setText(String.format(Locale.CHINA, "%.2f", (double) qt / 100));
    }
}









