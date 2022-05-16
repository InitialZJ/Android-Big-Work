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

public class StatActivity_zhichu extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout llShouru, llDetail, llAccount, llMine, llY, llS, llZ, llX, llQt;
    private TextView dateRange, y1, y2, s1, s2, z1, z2, x1, x2, qt1, qt2;
    private String[] items = {"天", "周", "月", "季", "年"};
    private Calendar calBegin, calEnd;
    private long dateBeign, dateEnd;
    int y, s, z, x, qt;
    private MyDbHelper helper;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_zhichu);
        context = this;
        helper = new MyDbHelper(context);
        initView();
        setDate(0);
    }

    private void initView() {
        llShouru = findViewById(R.id.ll_shouru);
        llDetail = findViewById(R.id.ll_detail);
        llAccount = findViewById(R.id.ll_account);
        llMine = findViewById(R.id.ll_mine);
        llY = findViewById(R.id.ll_y);
        llS = findViewById(R.id.ll_s);
        llZ = findViewById(R.id.ll_z);
        llX = findViewById(R.id.ll_x);
        llQt = findViewById(R.id.ll_qt);
        dateRange = findViewById(R.id.date_range);
        y1 = findViewById(R.id.tv_y1);
        y2 = findViewById(R.id.tv_y2);
        s1 = findViewById(R.id.tv_s1);
        s2 = findViewById(R.id.tv_s2);
        z1 = findViewById(R.id.tv_z1);
        z2 = findViewById(R.id.tv_z2);
        x1 = findViewById(R.id.tv_x1);
        x2 = findViewById(R.id.tv_x2);
        qt1 = findViewById(R.id.tv_qt1);
        qt2 = findViewById(R.id.tv_qt2);
        llShouru.setOnClickListener(this);
        llDetail.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llMine.setOnClickListener(this);
        llY.setOnClickListener(this);
        llS.setOnClickListener(this);
        llZ.setOnClickListener(this);
        llX.setOnClickListener(this);
        llQt.setOnClickListener(this);
        dateRange.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shouru:
                startActivity(new Intent(context, StatActivity.class));
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
            case R.id.ll_y:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "zhichu");
                editor.putString("kind", "衣");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
            case R.id.ll_s:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "zhichu");
                editor.putString("kind", "食");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
            case R.id.ll_z:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "zhichu");
                editor.putString("kind", "住");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
            case R.id.ll_x:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "zhichu");
                editor.putString("kind", "行");
                editor.putLong("dateBegin", dateBeign);
                editor.putLong("dateEnd", dateEnd);
                editor.commit();
                break;
            case R.id.ll_qt:
                startActivity(new Intent(context, SingleListActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("table", "zhichu");
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
        BtFragment mBt_fragment = helper.queryDate("zhichu", dateBeign, dateEnd);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mBt_fragment).commit();
        y = mBt_fragment.getY();
        s = mBt_fragment.getS();
        z = mBt_fragment.getZ();
        x = mBt_fragment.getX();
        qt = mBt_fragment.getQt2();
        System.out.println(qt);
        int sum = y + s + z + x + qt;
        double a = (double) y / sum;
        double b = (double) s / sum;
        double c = (double) z / sum;
        double d = (double) x / sum;
        double e = (double) qt / sum;
        y1.setText(String.format(Locale.CHINA, "%.1f%%", a * 100));
        s1.setText(String.format(Locale.CHINA, "%.1f%%", b * 100));
        z1.setText(String.format(Locale.CHINA, "%.1f%%", c * 100));
        x1.setText(String.format(Locale.CHINA, "%.1f%%", d * 100));
        qt1.setText(String.format(Locale.CHINA, "%.1f%%", e * 100));
        y2.setText(String.format(Locale.CHINA, "%.2f", (double) y / 100));
        s2.setText(String.format(Locale.CHINA, "%.2f", (double) s / 100));
        z2.setText(String.format(Locale.CHINA, "%.2f", (double) z / 100));
        x2.setText(String.format(Locale.CHINA, "%.2f", (double) x / 100));
        qt2.setText(String.format(Locale.CHINA, "%.2f", (double) qt / 100));
    }
}









