package com.example.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TenLCT_AdpTer extends BaseAdapter {
    Context context;
    private int layout;
    ArrayList<TenLoaiChiTieu> list;

    public TenLCT_AdpTer(Context context, int layout, ArrayList<TenLoaiChiTieu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class  ViewHolder{
        TextView txtname;
        TextView txtid;
        TextView txtidLCT;
        ImageView imgview;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtname=(TextView)view.findViewById(R.id.txtTenLoaiChiTieu_dongTCT);
            holder.txtid=(TextView)view.findViewById(R.id.txtidTeLCT_dongTCT);
            holder.txtidLCT=(TextView)view.findViewById(R.id.txtidLoaiCT_dongTCT);
            holder.imgview=(ImageView)view.findViewById(R.id.imTheLoai_dongTenCT);
            holder.txtidLCT.setVisibility(View.GONE);
            holder.txtid.setVisibility(View.GONE);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        TenLoaiChiTieu t=list.get(i);
        holder.txtname.setText(t.getName());
        holder.txtid.setText(t.getId()+"");
        holder.txtidLCT.setText(t.getIdLoaiCT()+"");
        if(t.getIdLoaiCT()==1){
            holder.imgview.setImageResource(R.drawable.chitieu);
        }
        else{
            holder.imgview.setImageResource(R.drawable.loinhaun);
        }
        holder.txtname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),DanhSachCTCT.class);
                Bundle bundle=new Bundle();
                bundle.putInt("idMaLoaiChiTieu",t.getId());
                intent.putExtra("idMaLoaiCT",bundle);
               context.startActivity(intent);
            }
        });
        return view;
    }
}