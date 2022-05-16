package com.a116042018022.iaccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btnRecord;
    private LinearLayout llAccount, llStat, llMine, today, week, month;
    private TextView shouruMonth, zhichuMonth, chaeMonth, shouruToday, zhichuToday, shouruWeek, zhichuWeek, shouruMonth2, zhichuMonth2;
    private Calendar calBegin, calEnd;
    private long dateBeignToday, dateEndToday, dateBeignWeek, dateEndWeek, dateBeignMonth, dateEndMonth;
    int shf, gz, qt, y, s, z, x, qt2;
    private MyDbHelper helper;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        helper = new MyDbHelper(context);
        initView();
        updateView();
    }

    private void initView() {
        btnRecord = findViewById(R.id.btn_record);
        llAccount = findViewById(R.id.ll_account);
        llStat = findViewById(R.id.ll_stat);
        llMine = findViewById(R.id.ll_mine);
        today = findViewById(R.id.today);
        week = findViewById(R.id.week);
        month = findViewById(R.id.month);
        shouruMonth = findViewById(R.id.shouru_month);
        zhichuMonth = findViewById(R.id.zhichu_month);
        shouruToday = findViewById(R.id.shouru_today);
        zhichuToday = findViewById(R.id.zhichu_today);
        shouruWeek = findViewById(R.id.shouru_week);
        zhichuWeek = findViewById(R.id.zhichu_week);
        shouruMonth2 = findViewById(R.id.shouru_month2);
        zhichuMonth2 = findViewById(R.id.zhichu_month2);
        chaeMonth = findViewById(R.id.chae_month);
        btnRecord.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llStat.setOnClickListener(this);
        llMine.setOnClickListener(this);
        today.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);
    }

    private void updateView() {
        calBegin = Calendar.getInstance();
        calEnd = Calendar.getInstance();
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
        dateBeignMonth = calBegin.getTimeInMillis();
        dateEndMonth = calEnd.getTimeInMillis();
        BtFragment mBt_fragment = helper.queryDate("shouru", dateBeignMonth, dateEndMonth);
        shf = mBt_fragment.getShf();
        gz = mBt_fragment.getGz();
        qt = mBt_fragment.getQt();
        int shouruSum = shf + gz + qt;
        mBt_fragment = helper.queryDate("zhichu", dateBeignMonth, dateEndMonth);
        y = mBt_fragment.getY();
        s = mBt_fragment.getS();
        z = mBt_fragment.getZ();
        x = mBt_fragment.getX();
        qt2 = mBt_fragment.getQt2();
        int zhichuSum = y + s + z + x + qt2;
        shouruMonth.setText(String.format(Locale.CHINA, "%.2f", (double) shouruSum / 100));
        zhichuMonth.setText(String.format(Locale.CHINA, "%.2f", (double) zhichuSum / 100));
        chaeMonth.setText(String.format(Locale.CHINA, "%.2f", (double) (shouruSum - zhichuSum) / 100));
        shouruMonth2.setText(String.format(Locale.CHINA, "%.2f", (double) shouruSum / 100));
        zhichuMonth2.setText(String.format(Locale.CHINA, "%.2f", (double) zhichuSum / 100));

        calBegin = Calendar.getInstance();
        calEnd = Calendar.getInstance();
        calBegin.set(Calendar.HOUR_OF_DAY, 0);
        calBegin.set(Calendar.MINUTE, 0);
        calBegin.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        calEnd.set(Calendar.HOUR_OF_DAY, 23);
        calEnd.set(Calendar.MINUTE, 59);
        calEnd.set(Calendar.SECOND, 59);
        calEnd.set(Calendar.MILLISECOND, 999);
        dateBeignToday = calBegin.getTimeInMillis();
        dateEndToday = calEnd.getTimeInMillis();
        mBt_fragment = helper.queryDate("shouru", dateBeignToday, dateEndToday);
        shf = mBt_fragment.getShf();
        gz = mBt_fragment.getGz();
        qt = mBt_fragment.getQt();
        shouruSum = shf + gz + qt;
        mBt_fragment = helper.queryDate("zhichu", dateBeignToday, dateEndToday);
        y = mBt_fragment.getY();
        s = mBt_fragment.getS();
        z = mBt_fragment.getZ();
        x = mBt_fragment.getX();
        qt2 = mBt_fragment.getQt2();
        zhichuSum = y + s + z + x + qt2;
        shouruToday.setText(String.format(Locale.CHINA, "%.2f", (double) shouruSum / 100));
        zhichuToday.setText(String.format(Locale.CHINA, "%.2f", (double) zhichuSum / 100));

        calBegin = Calendar.getInstance();
        calEnd = Calendar.getInstance();
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
        dateBeignWeek = calBegin.getTimeInMillis();
        dateEndWeek = calEnd.getTimeInMillis();
        mBt_fragment = helper.queryDate("shouru", dateBeignWeek, dateEndWeek);
        shf = mBt_fragment.getShf();
        gz = mBt_fragment.getGz();
        qt = mBt_fragment.getQt();
        shouruSum = shf + gz + qt;
        mBt_fragment = helper.queryDate("zhichu", dateBeignWeek, dateEndWeek);
        y = mBt_fragment.getY();
        s = mBt_fragment.getS();
        z = mBt_fragment.getZ();
        x = mBt_fragment.getX();
        qt2 = mBt_fragment.getQt2();
        zhichuSum = y + s + z + x + qt2;
        shouruWeek.setText(String.format(Locale.CHINA, "%.2f", (double) shouruSum / 100));
        zhichuWeek.setText(String.format(Locale.CHINA, "%.2f", (double) zhichuSum / 100));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_record:
                startActivityForResult(new Intent(context, RecordActivity.class), 1);
                break;
            case R.id.ll_account:
                startActivity(new Intent(context, AccountActivity.class));
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
            case R.id.today:
                startActivity(new Intent(context, AllListsActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putLong("dateBegin", dateBeignToday);
                editor.putLong("dateEnd", dateEndToday);
                editor.commit();
                break;
            case R.id.week:
                startActivity(new Intent(context, AllListsActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putLong("dateBegin", dateBeignWeek);
                editor.putLong("dateEnd", dateEndWeek);
                editor.commit();
                break;
            case R.id.month:
                startActivity(new Intent(context, AllListsActivity.class));
                sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putLong("dateBegin", dateBeignMonth);
                editor.putLong("dateEnd", dateEndMonth);
                editor.commit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1) {
                if (resultCode == 1) {
                    updateView();
                }
            }
        }
    }
}