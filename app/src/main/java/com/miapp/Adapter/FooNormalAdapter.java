package com.miapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.miapp.Model.FooBean;
import com.miapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebang on 16-5-20.
 */
public class FooNormalAdapter extends BaseAdapter {

    private List<FooBean> datas = new ArrayList<>();
    private Context context;

    public FooNormalAdapter(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setDataSource(List<FooBean> fooBeen){
        setDataSource(fooBeen, true);
    }

    public void setDataSource(List<FooBean> fooBeen, boolean isClear){
        if (isClear) this.datas.clear();
        this.datas.addAll(fooBeen);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = View.inflate(getContext(), R.layout.foo_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        FooBean fb = (FooBean)getItem(position);
        viewHolder.mTextView.setText(fb.getTitle());
        viewHolder.mButton.setText(fb.getContent());

        return convertView;
    }

    public static class ViewHolder{
        private TextView mTextView;
        private Button mButton;

        public ViewHolder(View convertView){
            this.mTextView = (TextView)convertView.findViewById(R.id.txt);
            this.mButton = (Button)convertView.findViewById(R.id.btn);
        }
    }
}
