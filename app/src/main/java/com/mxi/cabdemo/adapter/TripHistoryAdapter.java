package com.mxi.cabdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mxi.cabdemo.R;
import com.mxi.cabdemo.model.TripHistory;

import java.util.ArrayList;

/**
 * Created by mxicoders on 7/6/17.
 */

public class TripHistoryAdapter extends RecyclerView.Adapter<TripHistoryAdapter.DataObjectHolder> {

private static String LOG_TAG = "MyRecyclerViewAdapter";
private ArrayList<TripHistory> mDataset;
private static MyClickListener myClickListener;

public static class DataObjectHolder extends RecyclerView.ViewHolder
        implements View
        .OnClickListener {
//    TextView label;
//    TextView dateTime;

    public DataObjectHolder(View itemView) {
        super(itemView);
      /*  label = (TextView) itemView.findViewById(R.id.textView);
        dateTime = (TextView) itemView.findViewById(R.id.textView2);*/
        Log.i(LOG_TAG, "Adding Listener");
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myClickListener.onItemClick(getPosition(), v);
    }
}

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public TripHistoryAdapter(ArrayList<TripHistory> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_trip_history_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
       /* holder.label.setText(mDataset.get(position).getmText1());
        holder.dateTime.setText(mDataset.get(position).getmText2());*/
    }

    public void addItem(TripHistory dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

public interface MyClickListener {
    public void onItemClick(int position, View v);
}
}
