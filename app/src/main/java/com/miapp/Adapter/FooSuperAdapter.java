package com.miapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miapp.Model.FooBean;
import com.miapp.R;

/**
 * Created by lebang on 16-5-20.
 */
public class FooSuperAdapter extends LBaseAdapter<FooBean, LBaseAdapter.BaseViewHolder> {

    public FooSuperAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder createViewHolder(int position, ViewGroup parent) {
        return new BaseViewHolder(View.inflate(getContext(), R.layout.foo_item,null));
    }

    @Override
    protected void bindViewHolder(BaseViewHolder holder, int position, FooBean data) {
        TextView txtTitle = holder.getView(R.id.txt);
        Button txtContent = holder.getView(R.id.btn);

        txtTitle.setText(data.getTitle());
        txtContent.setText(data.getContent());
    }
}
