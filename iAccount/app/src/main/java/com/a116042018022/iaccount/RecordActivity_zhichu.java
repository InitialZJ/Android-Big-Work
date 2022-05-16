package com.a116042018022.iaccount;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatSpinner;

import java.util.Calendar;

public class RecordActivity_zhichu extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private Context context;
    private ImageView ivBack;
    private LinearLayout llComplete, llShouru;
    private EditText zhichuNumber, etBeizhu;
    private AppCompatSpinner zhichuSpinner;
    private TextView tvDate, tvTime;
    private Button btnSave;
    private StringBuffer date, time;
    private int year, month, day, hour, minute;
    private Calendar ca;
    MyDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_zhichu);
        context = this;
        ca = Calendar.getInstance();
        date = new StringBuffer();
        time = new StringBuffer();
        initDateTime();
        initView();
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        llComplete = findViewById(R.id.ll_complete);
        llShouru = findViewById(R.id.ll_shouru);
        zhichuNumber = findViewById(R.id.zhichu_number);
        etBeizhu = findViewById(R.id.et_beizhu);
        zhichuSpinner = findViewById(R.id.zhichu_spinner);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        btnSave = findViewById(R.id.btn_save);
        ivBack.setOnClickListener(this);
        llComplete.setOnClickListener(this);
        llShouru.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvDate.setText(date.append(String.valueOf(year)).append("年").append(String.valueOf(month + 1)).append("月").append(day).append("日"));
        tvTime.setText(time.append(String.valueOf(hour)).append("时").append(String.valueOf(minute)).append("分"));
        helper = new MyDbHelper(context);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (date.length() > 0) { //清除上次记录的日期
                            date.delete(0, date.length());
                        }
                        tvDate.setText(date.append(String.valueOf(year)).append("年").append(String.valueOf(month + 1)).append("月").append(day).append("日"));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(context, R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);

                dialog.setTitle("设置日期");
                dialog.setView(dialogView);
                dialog.show();
                //初始化日期监听事件
                datePicker.init(year, month, day, this);
                break;
            case R.id.tv_time:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                builder2.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (time.length() > 0) { //清除上次记录的日期
                            time.delete(0, time.length());
                        }
                        tvTime.setText(time.append(String.valueOf(hour)).append("时").append(String.valueOf(minute)).append("分"));
                        dialog.dismiss();
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog2 = builder2.create();
                View dialogView2 = View.inflate(context, R.layout.dialog_time, null);
                TimePicker timePicker = (TimePicker) dialogView2.findViewById(R.id.timePicker);
                timePicker.setHour(hour);
                timePicker.setMinute(minute);
                timePicker.setIs24HourView(true); //设置24小时制
                timePicker.setOnTimeChangedListener(this);
                dialog2.setTitle("设置时间");
                dialog2.setView(dialogView2);
                dialog2.show();
                break;
            case R.id.iv_back:
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
                break;
            case R.id.ll_complete:
            case R.id.btn_save:
                ca.set(Calendar.YEAR, this.year);
                ca.set(Calendar.MONTH, this.month);
                ca.set(Calendar.DAY_OF_MONTH, this.day);
                ca.set(Calendar.HOUR_OF_DAY, this.hour);
                ca.set(Calendar.MINUTE, this.minute);
                ca.set(Calendar.SECOND, 0);
                ca.set(Calendar.MILLISECOND, 0);
                if (!zhichuNumber.getEditableText().toString().trim().equals(""))
                {
                    helper.insertData(context, "zhichu", zhichuSpinner.getSelectedItem().toString(),
                            zhichuNumber, ca.getTimeInMillis(), etBeizhu);
                    Intent intent1 = new Intent();
                    setResult(1, intent1);
                    finish();
                } else {
                    Toast.makeText(context, "请输入金额", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_shouru:
                startActivity(new Intent(context, RecordActivity.class));
                finish();
                break;
        }
    }

    private void initDateTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
    }
}