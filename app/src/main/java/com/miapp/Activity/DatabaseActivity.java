package com.miapp.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.miapp.Database.MyDatabaseHelper;
import com.miapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lebang on 16-5-12.
 */
public class DatabaseActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DatabaseActivity";

    private MyDatabaseHelper mDatabaseHelper;
    private Button mCreateBtn, mAddBtn, mUpdateBtn, mDeleteBtn, mQueryBtn, mReplaceBtn;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);

        mCreateBtn = (Button)findViewById(R.id.create_database);
        mCreateBtn.setOnClickListener(this);
        mAddBtn = (Button)findViewById(R.id.add_data);
        mAddBtn.setOnClickListener(this);
        mUpdateBtn = (Button)findViewById(R.id.update_data);
        mUpdateBtn.setOnClickListener(this);
        mDeleteBtn = (Button)findViewById(R.id.delete_data);
        mDeleteBtn.setOnClickListener(this);
        mQueryBtn = (Button)findViewById(R.id.query_data);
        mQueryBtn.setOnClickListener(this);
        mReplaceBtn = (Button)findViewById(R.id.replace_data);
        mReplaceBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_database:
                mDatabaseHelper.getWritableDatabase();
                break;
            case R.id.add_data:
                db = mDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "HTML");
                values.put("author", "CSS");
                values.put("pages", 300);
                values.put("price", 11);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);
                break;
            case R.id.update_data:
                db = mDatabaseHelper.getWritableDatabase();
                ContentValues mval = new ContentValues();
                mval.put("price", 12);
                db.update("Book", mval, "name=?", new String[]{"HTML"});
                break;
            case R.id.delete_data:
                db = mDatabaseHelper.getWritableDatabase();
                db.delete("Book", "page>?", new String[]{"300"});
                break;
            case R.id.query_data:
                db = mDatabaseHelper.getWritableDatabase();
                Cursor msor = db.query("Book",null,null,null,null,null,null);
                if (msor.moveToFirst()){
                    do{
                        String name = msor.getString(msor.getColumnIndex("name"));
                        String author = msor.getString(msor.getColumnIndex("author"));
                        int pages = msor.getInt(msor.getColumnIndex("pages"));
                        double price = msor.getDouble(msor.getColumnIndex("price"));
                        Log.d(TAG, "onClick: "+name+"/"+author+"/"+pages+"/"+price);
                    } while (msor.moveToNext());
                }
                msor.close();
                break;
            case R.id.replace_data:
                db = mDatabaseHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    db.delete("Book", null, null);
//					if (true) {
//						throw new NullPointerException();
//					}
                    ContentValues rvalues = new ContentValues();
                    rvalues.put("name", "Game of Thrones");
                    rvalues.put("author", "George Martin");
                    rvalues.put("pages", 720);
                    rvalues.put("price", 20.85);
                    db.insert("Book", null, rvalues);
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            default:
                break;
        }
    }
}
