package com.bawei.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.adapter.MyAdapter;
import com.bawei.test.bean.DataBean;
import com.bawei.test.okhttputils.OkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;


public class Myfragment extends Fragment{


    private MyAdapter adapter;
    private String url;
    private RecyclerView mRecyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment1, null);

        //获得子控件
        mRecyclerView = (RecyclerView) view.findViewById(R.id.lv);

        //acti传递的参数
        fragmentbundle();
        //设置布局管理
        //listview展示
        LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
        mgr.setOrientation(LinearLayoutManager.VERTICAL);//方向（纵、横）
        mRecyclerView.setLayoutManager(mgr);

        //GridView展示
        GridLayoutManager mGrid = new GridLayoutManager(getActivity(), 3);//
        //mRecyclerView.setLayoutManager(mGrid);


//        //设置动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //okhttp请求网络
        httpjson();

        return view;
    }

    private void fragmentbundle() {
        //acti传递的参数
        Bundle bundle = getArguments();
        url = bundle.getString("urls");
    }

    private void httpjson() {
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {


            @Override
            public void requestFailure(Request request, IOException e) {
                System.out.print("数据加载失败。。。。。。。。。。。。。。。。。。。。。。。。。。。");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                String json = result;
                Gson gson = new Gson();
                DataBean db = gson.fromJson(json, DataBean.class);
                ArrayList<DataBean.Data> mDatas = db.data;
                adapter =   new MyAdapter(mDatas, getActivity());
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int possion) {
                        Toast.makeText(getActivity(), "dgfdgdfhb"+possion, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }







}
