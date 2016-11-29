package com.bawei.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.test.R;
import com.bawei.test.bean.DataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by asus on 2016/11/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DataBean.Data> mDatas;
    private Context context;

    public MyAdapter(ArrayList<DataBean.Data> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }
    public  interface OnRecyclerViewItemClickListener {
        public void onItemClick(View view , int possion);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.recy_item, null);
        //将创建的View注册点击事件

        return new MyViewHolder(v,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position).efficacy);
        ImageLoader.getInstance().displayImage(mDatas.get(position).goods_img,
                holder.iv);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv;
        ImageView iv;
        OnRecyclerViewItemClickListener mOnItemClickListener;
        public MyViewHolder(View view,OnRecyclerViewItemClickListener mOnItemClickListener) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recy);
            iv = (ImageView) view.findViewById(R.id.iv_recy);
            this.mOnItemClickListener=mOnItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view, getPosition());
        }
    }

}
