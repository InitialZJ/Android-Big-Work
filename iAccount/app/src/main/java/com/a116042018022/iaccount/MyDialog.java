package com.a116042018022.iaccount;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDialog extends Dialog implements View.OnClickListener {

    private TextView mTitle, mContent;
    private TextView mConfirm, mCancel;

    private Context mContext;
    private String content;
    private OncloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private EditText et;

    public MyDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyDialog(@NonNull Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public MyDialog(@NonNull Context context, int themeResId, OncloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
    }

    public MyDialog(@NonNull Context context, int themeResId, String content, OncloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    /**
     * 设置弹框标题
     * @param title 标题内容
     * @return
     */
    public MyDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置弹框的提示内容
     * @param content 弹框的提示内容
     * @return
     */
    public MyDialog setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 设置弹框确认键的内容
     * @param name 确认键显示内容
     * @return
     */
    public MyDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    /**
     * 设置弹框取消键的内容
     * @param name 取消键显示内容
     * @return
     */
    public MyDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);
        setCanceledOnTouchOutside(false);
        mTitle = findViewById(R.id.tv_title);
        mContent = findViewById(R.id.shouru_number);
        mConfirm = findViewById(R.id.btn_confirm);
        mCancel = findViewById(R.id.btn_cancel);
        et = findViewById(R.id.shouru_number);

        mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                SharedPreferences sp = this.mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                if (!et.getEditableText().toString().trim().equals(""))
                {
                    editor.putInt("number", (int)(Float.parseFloat(et.getEditableText().toString().trim()) * 100));
                } else {
                    editor.putInt("number", 0);
                }
                editor.commit();
                if (listener != null) {
                    listener.onClick(true);
                }
                this.dismiss();
                break;
            case R.id.btn_cancel:
                if (listener != null) {
                    listener.onClick(false);
                }
                this.dismiss();
                break;
        }
    }

    public interface OncloseListener {
        void onClick(boolean confirm);
    }
}
