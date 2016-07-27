package com.miapp.Adapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.miapp.Model.TopStory;
import com.miapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TopStoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<TopStory> mTopStories;
    private ImageLoader imageLoader;

    public TopStoryAdapter(Context context,List<TopStory> objects) {
        this.mContext= context;
        this.mTopStories = objects;
    }

    @Override
    public int getCount() {
        return mTopStories.size();
    }

    @Override
    public Object getItem(int position) {
        return mTopStories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopStory topStory = mTopStories.get(position);
        final View view;
        view = LayoutInflater.from(mContext).inflate( R.layout.top_story_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.top_story_img);
        TextView title = (TextView) view.findViewById(R.id.top_story_title);
        Glide.with(mContext).load(topStory.getImage()).into(img);
        title.setText(topStory.getTitle());
        return view;
    }
}
