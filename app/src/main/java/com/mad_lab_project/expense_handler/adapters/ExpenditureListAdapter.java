package com.mad_lab_project.expense_handler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad_lab_project.expense_handler.R;

import java.util.ArrayList;

public class ExpenditureListAdapter extends BaseAdapter {
    ArrayList<String> categoriesList;
    ArrayList<String> amtList;
    LayoutInflater mInflator;

    public ExpenditureListAdapter(Context c,ArrayList<String> cL,ArrayList<String> aL) {
        categoriesList = cL;
        amtList = aL;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return amtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.expenditures_list_details_activity,null);
        TextView tvAmount = (TextView) v.findViewById(R.id.textViewAmount);
        TextView tvCategory = (TextView) v.findViewById(R.id.textViewCategory);

        tvAmount.setText(amtList.get(position));
        tvCategory.setText(categoriesList.get(position));
        return v;
    }
}
