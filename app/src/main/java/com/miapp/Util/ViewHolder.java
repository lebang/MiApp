package com.miapp.Util;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by lebang on 16-7-15.
 * 
 * @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_test, null);
        }
        TextView tv_name = ViewHolder.get(convertView , R.id.tv_name);
        tv_name.setText(listBeans.get(position));
        return convertView;
    }
 */
public class ViewHolder {
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
