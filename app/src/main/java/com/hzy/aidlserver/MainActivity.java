package com.hzy.aidlserver;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzy.aidlserver.recyceradapter.CommonAdapter;
import com.hzy.aidlserver.recyceradapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    CommonAdapter<String> mAdpter;
    List<String> mTopicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTopicList.add("dgfsgs");
        mTopicList.add("dgfsgs");
        mTopicList.add("dgfsgs");
        recyclerView=findViewById(R.id.recyclerView);
        mAdpter = new CommonAdapter<String>(this, R.layout.sp_course_item, mTopicList) {
            @Override
            protected void convert(ViewHolder holder, String str, int position) {
            }
        };
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //调整RecyclerView的排列方向
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(mAdpter);
        //禁用滑动事件
        recyclerView.setNestedScrollingEnabled(false);
//        mAdpter.setOnItemClickListener(OnItemClickListener);
    }


    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerHolder> {
        private Context mContext;
        private List<String> dataList = new ArrayList<>();

        public MyRecyclerViewAdapter(RecyclerView recyclerView) {
            this.mContext = recyclerView.getContext();
        }

        public void setData(List<String> dataList) {
            if (null != dataList) {
                this.dataList.clear();
                this.dataList.addAll(dataList);
                notifyDataSetChanged();
            }
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.sp_course_item, parent, false);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.textView.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class RecyclerHolder extends RecyclerView.ViewHolder {
            TextView textView;

            private RecyclerHolder(View itemView) {
                super(itemView);
//                textView = (TextView) itemView.findViewById(R.id.tv__id_item_layout);
            }
        }
    }
}
