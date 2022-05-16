package com.a116042018022.iaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    private ArrayList<String> kinds = new ArrayList<String>();
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    private ArrayList<Long> times = new ArrayList<Long>();

    public MyDbHelper(@Nullable Context context) {
        super(context, "iAccount.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE shouru(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kind VARCHAR(10), number INTEGER(10), time INTEGER(20), beizhu VARCHAR(100))");
        sqLiteDatabase.execSQL("CREATE TABLE zhichu(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kind VARCHAR(10), number INTEGER(10), time INTEGER(20), beizhu VARCHAR(100))");
        sqLiteDatabase.execSQL("CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kind VARCHAR(10), number INTEGER(10))");
        sqLiteDatabase.execSQL("CREATE TABLE time_modify(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "time INTEGER(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(Context context, String tableName, String kind,
                           EditText number, long time, EditText et_beizhu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kind", kind);
        values.put("number", (int)(Float.parseFloat(number.getEditableText().toString().trim()) * 100));
        values.put("time", time);
        values.put("beizhu", et_beizhu.getText().toString().trim());
        long id = db.insert(tableName, null, values);
        if (id > 0) {
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public BtFragment queryDate(String tableName, long dateBegin, long dateEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        int number1 = 0, number2 = 0, number3 = 0, number4 = 0, number5 = 0;
        BtFragment mBt_fragment = new BtFragment();
        if (tableName.equals("shouru"))
        {
            cursor = db.rawQuery("select number from shouru where kind = '生活费' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number1 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            cursor = db.rawQuery("select number from shouru where kind = '工资' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number2 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            cursor = db.rawQuery("select number from shouru where kind = '其他' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number3 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            mBt_fragment.addShouruData(number1, number2, number3);
        }
        else if (tableName.equals("zhichu"))
        {
            cursor = db.rawQuery("select number from zhichu where kind = '衣' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number1 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            cursor = db.rawQuery("select number from zhichu where kind = '食' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number2 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            cursor = db.rawQuery("select number from zhichu where kind = '住' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number3 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            cursor = db.rawQuery("select number from zhichu where kind = '行' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number4 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            cursor = db.rawQuery("select number from zhichu where kind = '其他' and time >= ? AND time <= ?", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
            while (cursor.moveToNext()) {
                number5 += cursor.getInt(cursor.getColumnIndex("number"));
            }
            mBt_fragment.addZhichuData(number1, number2, number3, number4, number5);
        }
        cursor.close();
        db.close();
        return mBt_fragment;
    }

    public long queryAccount(String kind) {
        SQLiteDatabase db = this.getWritableDatabase();
        long number = 0;
        Cursor cursor = db.rawQuery("select number from account where kind = ?", new String[]{kind});
        while (cursor.moveToNext()) {
            number = cursor.getInt(cursor.getColumnIndex("number"));
        }
        return number;
    }

    public void updateAccount(int number, String kind) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", number);
        int rows = db.update("account", values, "kind=?", new String[]{kind});
        if (rows <= 0) {
            values.put("kind", kind);
            db.insert("account", null, values);
        }
        db.close();
    }

    public long queryTimeModify() {
        SQLiteDatabase db = this.getWritableDatabase();
        long time = 0;
        Cursor cursor = db.rawQuery("select time from time_modify", null);
        while (cursor.moveToNext()) {
            time = cursor.getLong(cursor.getColumnIndex("time"));
        }
        return time;
    }

    public void updateTimeModify(long time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", time);
        int rows = db.update("time_modify", values, null, null);
        if (rows <= 0) {
            db.insert("time_modify", null, values);
        }
        db.close();
    }

    public void queryList(String tableName, long dateBegin, long dateEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        kinds.clear();
        numbers.clear();
        times.clear();
        if (tableName.equals("shouru"))
            cursor = db.rawQuery("select * from shouru where time >= ? AND time <= ? order by time desc", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
        else if (tableName.equals("zhichu"))
            cursor = db.rawQuery("select * from zhichu where time >= ? AND time <= ? order by time desc", new String[]{String.valueOf(dateBegin), String.valueOf(dateEnd)});
        while (cursor.moveToNext()) {
            kinds.add(cursor.getString(cursor.getColumnIndex("kind")));
            numbers.add(cursor.getInt(cursor.getColumnIndex("number")));
            times.add(cursor.getLong(cursor.getColumnIndex("time")));
        }
        cursor.close();
        db.close();
    }

    public void querySingleList(String tableName, String kind, long dateBegin, long dateEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        kinds.clear();
        numbers.clear();
        times.clear();
        if (tableName.equals("shouru"))
            cursor = db.rawQuery("select * from shouru where kind = ? AND time >= ? AND time <= ? order by time desc", new String[]{kind, String.valueOf(dateBegin), String.valueOf(dateEnd)});
        else if (tableName.equals("zhichu"))
            cursor = db.rawQuery("select * from zhichu where kind = ? AND time >= ? AND time <= ? order by time desc", new String[]{kind, String.valueOf(dateBegin), String.valueOf(dateEnd)});
        while (cursor.moveToNext()) {
            kinds.add(cursor.getString(cursor.getColumnIndex("kind")));
            numbers.add(cursor.getInt(cursor.getColumnIndex("number")));
            times.add(cursor.getLong(cursor.getColumnIndex("time")));
        }
        cursor.close();
        db.close();
    }

    public ArrayList<String> getKinds() {
        return kinds;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public ArrayList<Long> getTimes() {
        return times;
    }
}
