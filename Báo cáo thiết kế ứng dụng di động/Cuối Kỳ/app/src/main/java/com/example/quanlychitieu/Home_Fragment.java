package com.example.quanlychitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.quanlychitieu.DAO.TKChiTieu;
import com.example.quanlychitieu.ThemXoaSua_LietKeThongKe.ThemLoaiChiTieu;

import java.util.ArrayList;



public class Home_Fragment extends Fragment {
    static String arr[]={"Nhận lương","Đi chợ","Bán đồ phế thải",
            "Tiền học","Tiền Trợ cấp","Tiền tình yêu"};
    public static final int ThemChiTieu=0;
    public static final int LuuThemChiTieu=1;
    DataBase ddBase;
    //ipmport dataBase
    //SQLiteDatabase  database=SQLiteDatabase.openDatabase("hgfs",null,SQLiteDatabase.CREATE_IF_NECESSARY);
    GridView grList;
    EditText txtLayDl,txt;
    Button btnThongKe,btnThemMoiLoaiChiTieu;
    ArrayList<TenLoaiChiTieu> arrayList=new ArrayList<>();
    ArrayList<String> arrayListTen=new ArrayList<>();
    ArrayAdapter<TenLCT_AdpTer> arrayAdapter;
    ArrayAdapter<String> arrayAdapterTen=null;
    TenLCT_AdpTer adpTer;
    String adpTerTen;
    View v;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dx:{
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xác nhận");
                builder.setMessage("có chắc đăng xuất");
                builder.setPositiveButton("đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent=new
                    }
                });
                builder.setNegativeButton("thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                   builder.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.context_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);

    }
    public void Add(View view){
        grList=(GridView) view.findViewById(R.id.grList_main);
    }
    public  void BanDau(View view){
        arrayAdapter=null;
        Add(view);
        ddBase = new DataBase(view.getContext(),"DanhSachTenChiTieu.sqlite",null,1);
        ddBase.QueryData("CREATE TABLE IF NOT EXISTS ChiTiet(Id integer primary key AUTOINCREMENT,IdTenLoaiChiTieu integer,TenCTCT Text ,NgayThanhLap Text, TienChi REAL )");
        ddBase.QueryData("CREATE TABLE IF NOT EXISTS TenChiTieu(Id integer primary key AUTOINCREMENT,LoaiChiTieu integer,TenChiTieu Text)");
        Cursor dd = ddBase.GetData("select * from TenChiTieu ");
        if(dd.getCount() <2){
            for(int i=0;i<arr.length;i++){
                arrayListTen.add("áhadb");
                if(i%2==0){
                    ddBase.QueryData("INSERT into TenChiTieu VALUES(NULL,1,'"+arr[i].toString()+"')");
                }
                else{
                    ddBase.QueryData("INSERT into TenChiTieu VALUES(NULL,0,'"+arr[i].toString()+"')");
                }
            }
        }
        dd = ddBase.GetData("select * from TenChiTieu ");
        while(dd.moveToNext()){
            String ten=dd.getString(2);
            int id=dd.getInt(0);
            int idLCT=dd.getInt(1);
            arrayList.add(new TenLoaiChiTieu(ten,id,idLCT));
        }
        dd = ddBase.GetData("select * from ChiTiet ");
        if(dd.getCount()<1){
            ddBase.QueryData("INSERT into ChiTiet VALUES (NULL ,1,'Lương cứng','17/03/2021',4000000)");
            ddBase.QueryData("INSERT into ChiTiet VALUES (NULL ,1,'Lương trợ cấp','17/03/2021',600000)");
            ddBase.QueryData("INSERT into ChiTiet VALUES (NULL ,1,'Lương tăng ca','17/03/2021',400000)");
            ddBase.QueryData("INSERT into ChiTiet VALUES (NULL ,2,'Mua thịt','17/03/2021',600000)");
            ddBase.QueryData("INSERT into ChiTiet VALUES (NULL ,3,'Bán nhôm nhựa','17/02/2021',600000)");
            ddBase.QueryData("INSERT into ChiTiet VALUES (NULL ,4,'Học phí chính quy','12/05/2021',3000000)");
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_main, container, false);
        v=view;
        getActivity().setTitle("Trang chủ");
        Add(view);
        BanDau(view);
        adpTer=new TenLCT_AdpTer(view.getContext(),R.layout.dongtenchitieu,arrayList);
        grList.setAdapter(adpTer);
        registerForContextMenu(grList);
        return view ;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Home_Fragment.ThemChiTieu){
            if(resultCode==Home_Fragment.LuuThemChiTieu){
                Toast.makeText(v.getContext(),"Thêm thành công", Toast.LENGTH_LONG).show();
                Bundle bundle=data.getBundleExtra("data");
                String ten=bundle.getString("TenChiTieu");
                int LCT=bundle.getInt("LoaiCT");
                String sql="insert into TenChiTieu VALUES(Null,"+LCT+",'"+ten+"')";
                ddBase.QueryData(sql);
                Cursor dd = ddBase.GetData("select * from TenChiTieu ");
                if(dd.moveToLast()){
                    String tenn=dd.getString(2);
                    int id=dd.getInt(0);
                    int idLCT=dd.getInt(1);
                    arrayList.add(new TenLoaiChiTieu(tenn,id,idLCT));
                }
                adpTer.notifyDataSetChanged();
            }
        }
    }

}