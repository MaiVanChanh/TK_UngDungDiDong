package com.example.quanlychitieu;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTieuThuChi_ApdTer extends BaseAdapter {
    private Context mContext;
    private int layout;
    private List<ChiTieuThuChi> list;

    public ChiTieuThuChi_ApdTer(Context mContext, int layout, List<ChiTieuThuChi> list) {
        this.mContext = mContext;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);
        TextView txtTenLCT = (TextView) view.findViewById(R.id.txtTenLoaiCHiTieu_dongCT);
        TextView txtidCTCT = (TextView) view.findViewById(R.id.txtidTenChiTieuChiTiet_dongCT);
        TextView txtTenCTCT = (TextView) view.findViewById(R.id.txtTenCHiTieuChiTiet_dongCT);
        TextView txtSoTien = (TextView) view.findViewById(R.id.txtSoTien_DongChiTiet);
        TextView txtNgay = (TextView) view.findViewById(R.id.txtNgayChiTieu_dongchitieu);
        TextView txtidLoaiCT = (TextView) view.findViewById(R.id.txtidLoaiChiTieu_DongCHiTieu);

        ChiTieuThuChi ct = list.get(i);
        txtTenLCT.setText("Nhóm CT :"+ct.getTenLoaiChiTieu());
        txtidCTCT.setText(ct.getIdTenChiTieuChiTiet() + "");
        txtTenCTCT.setText("Chi tiết :"+ct.getTenChiTieu());

        //CHuyển tiền
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        double tien=ct.getSoTien();
        String usd=formatter.format(tien);
        //
        txtSoTien.setText(usd);
        txtNgay.setText(ct.getDate());
        txtidLoaiCT.setText(ct.getIdLoaiCT() +"");
        txtidCTCT.setVisibility(View.GONE);
        txtidLoaiCT.setVisibility(View.GONE);
        int cl=mContext.getResources().getColor(R.color.maudo);
        int clX=mContext.getResources().getColor(R.color.mauxanh);
        if (ct.getIdLoaiCT() == 1) {
               txtSoTien.setTextColor(clX);
            }
        else{
            txtSoTien.setTextColor(cl);
        }
       // registerForContextMenu(lvCongViec);
            return view;
        }
}
