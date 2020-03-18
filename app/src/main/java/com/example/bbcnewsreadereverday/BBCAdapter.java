package com.example.bbcnewsreadereverday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

class BBCNewsAdapter extends BaseAdapter {
    private List<BBCNews> newsList;
    private Context ctx;

    public BBCNewsAdapter(Context ctx, List<BBCNews> list) {
        setNewsList(list);
        this.ctx = ctx;
    }


    public void setNewsList(List<BBCNews> newsList) {
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public BBCNews getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Long res = newsList.get(position).getId();
        return res == null ? 0 : res;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.ctx);
            convertView = inflater.inflate(R.layout.layout_bbc_item, parent, false);
        }

        TextView titleView = convertView.findViewById(R.id.titleBBCNews);
        TextView descView = convertView.findViewById(R.id.descriptionBBCNews);
        TextView dateView = convertView.findViewById(R.id.dateBBCNews);

        BBCNews news = newsList.get(position);
        titleView.setText(news.getTitle());
        descView.setText(news.getData());
        dateView.setText(news.getDate());

        return convertView;
    }
}

