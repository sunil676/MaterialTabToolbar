package com.sunil.material.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunil.materialtabtoolbar.ActressDetailActivity;
import com.sunil.materialtabtoolbar.MainActivity;
import com.sunil.materialtabtoolbar.R;
import com.sunil.matrial.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luv_Gupta on 28-Jun-15.
 */
public class ActressListFragment extends Fragment{


    ArrayList<UserInfo> listOfusers=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_actress_list, container, false);
        listOfusers= MainActivity.list;
        // reference load list
        setupRecyclerView(rv);

        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),listOfusers));
    }


    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<UserInfo> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;
            public String mBoundStringUrl;
            public final View mView;
            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public UserInfo getValueAt(int position) {
            return mValues.get(position);
        }

        public SimpleStringRecyclerViewAdapter(Context context, ArrayList<UserInfo> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            //mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mBoundString = mValues.get(position).getName();
            holder.mTextView.setText(mValues.get(position).getName());
            holder.mBoundStringUrl = mValues.get(position).getUrl();

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ActressDetailActivity.class);
                    intent.putExtra("name", holder.mBoundString);
                    intent.putExtra("url", holder.mBoundStringUrl);
                    context.startActivity(intent);
                }
            });

            Log.v("", "url is: "+mValues.get(position).getUrl());
            Glide.with(holder.mImageView.getContext())
                    .load(mValues.get(position).getUrl())
                    .fitCenter()
                    .into(holder.mImageView);

           /* Picasso.with(holder.mImageView.getContext())
                    .load(mValues.get(position).getUrl())
                    .centerCrop()
                    .resize(400, 400)
                    .into(holder.mImageView);*/
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
