package com.example.quanlychitieu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlychitieu.DAO.TKChiTieu;
import com.example.quanlychitieu.ThemXoaSua_LietKeThongKe.ThemChiTietChiTieu;

import java.util.ArrayList;

public class DanhSachCTCT extends AppCompatActivity {
    public  static final int sua=1;
    public  static final int luusua=2;
    public  static final int ThemMoi=3;
    public  static final int LuuThemMoi=4;
    public  static final int Xem=5;
    public  static final int TroVe=6;
    static  int idLCT,Vitri;
    int idTenCTCT=-1;
    ListView lv;
    Button btnThemMoi,btnThongKe;
    EditText txtidTenLCT,txtidViTR;
    ArrayList<ChiTieuThuChi> arrayList=new ArrayList<>();
    ArrayAdapter<ChiTieuThuChi_ApdTer> arrayAdapter=null;
    ChiTieuThuChi_ApdTer apdTer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_c_t_c_t);
        add();
        BanDau();
        ax();
        registerForContextMenu(lv);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DanhSachCTCT.this, ThemChiTietChiTieu.class);
                Bundle bundle=new Bundle();
                bundle.putInt("idTenNhomLCT",idLCT);
                intent.putExtra("data",bundle);
                bundle.putInt("TT",2);
                startActivityForResult(intent,DanhSachCTCT.ThemMoi);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DanhSachCTCT.this,TKChiTieu.class);
                Bundle bundle=new Bundle();
                bundle.putInt("idLCT",idLCT);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
    }
    public void ax() {
        apdTer= new ChiTieuThuChi_ApdTer(DanhSachCTCT.this,R.layout.dongchitieuchitiet,arrayList);
        lv.setAdapter(apdTer);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTieuThuChi ct=arrayList.get(i);
                idTenCTCT=ct.getIdTenChiTieuChiTiet();
                Vitri=i;
                return false;
            }
        });
        apdTer.notifyDataSetChanged();
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent;
        Bundle bundle;
        switch (item.getItemId()){
            case R.id.viewmnu :
                intent =new Intent(DanhSachCTCT.this,ThemChiTietChiTieu.class);
                 bundle=new Bundle();
                bundle.putInt("idTenNhomLCT",idLCT);
                bundle.putInt("TT",0);
                bundle.putInt("idTenCTCT",idTenCTCT);
                intent.putExtra("data",bundle);
                startActivityForResult(intent,DanhSachCTCT.Xem);
                break;
            case R.id.editmnu :
                 intent=new Intent(DanhSachCTCT.this,ThemChiTietChiTieu.class);
                 bundle=new Bundle();
                 bundle.putInt("idTenNhomLCT",idLCT);
                 bundle.putInt("idTenCTCT",idTenCTCT);
                bundle.putInt("TT",1);
                 intent.putExtra("data",bundle);
                 startActivityForResult(intent,DanhSachCTCT.sua);
                break;
            case R.id.deletemnu:
                AlertDialog.Builder builder=new AlertDialog.Builder(DanhSachCTCT.this);
                builder.setTitle("xác nhận");
                builder.setMessage("Có chắc xóa ?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChiTieuThuChi ct=arrayList.get(Vitri);
                        String sql="delete from ChiTiet where Id="+ct.getIdTenChiTieuChiTiet()+"";
                        DataBase ddBase = new DataBase(DanhSachCTCT.this,"DanhSachTenChiTieu.sqlite",null,1);
                        ddBase.QueryData(sql);
                        arrayList.remove(Vitri);
                        apdTer.notifyDataSetChanged();
                    }

                });
                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option_menu,menu);
    }
    public void add(){
        btnThemMoi=(Button)findViewById(R.id.btnThemCTCT_DanhSCTCT);
        btnThongKe=(Button)findViewById(R.id.btnThongKe_DanhSCTCT);
        lv=(ListView)findViewById(R.id.lvDSCTCT_DanhSachCTCT);
    }
    public void BanDau(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("idMaLoaiCT");
        idLCT=bundle.getInt("idMaLoaiChiTieu");
        DataBase ddBase = new DataBase(DanhSachCTCT.this,"DanhSachTenChiTieu.sqlite",null,1);
        String sql="SELECT tct.TenChiTieu,ct.Id,ct.TenCTCT,ct.TienChi,ct.NgayThanhLap,tct.LoaiChiTieu from ChiTiet ct, TenChiTieu tct WHERE ct.IdTenLoaiChiTieu=tct.Id and "+idLCT+"=tct.Id";
        Cursor cursor=ddBase.GetData(sql);
        while (cursor.moveToNext()){
           String tenChiTieu=cursor.getString(0);
           int idCTCT=cursor.getInt(01);
           String tenCTCT=cursor.getString(2);
           Double soTien=cursor.getDouble(3);
           String ngay=cursor.getString(4);
           int lCTCT=cursor.getInt(5);
           ChiTieuThuChi ct=new ChiTieuThuChi(tenChiTieu,idCTCT,tenCTCT,soTien,ngay,lCTCT);
           arrayList.add(ct);
        }
        registerForContextMenu(lv);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DataBase dataBase=new DataBase(this,"DanhSachTenChiTieu.sqlite",null,1);
        switch (requestCode){
            case sua:{
                if(resultCode==luusua){
                    Toast.makeText(DanhSachCTCT.this,"Sửa  thành công",Toast.LENGTH_LONG).show();
                    Bundle bundle = data.getBundleExtra("Data");
                    String sql="SELECT tct.TenChiTieu,ct.Id,ct.TenCTCT,ct.TienChi,ct.NgayThanhLap,tct.LoaiChiTieu from ChiTiet ct, TenChiTieu tct WHERE ct.IdTenLoaiChiTieu=tct.Id and "+idLCT+"=tct.Id";
                    Cursor cursor=dataBase.GetData(sql);
                    while (cursor.moveToNext()) {
                        String tenChiTieu=cursor.getString(0);
                        int idCTCT=cursor.getInt(01);
                        String tenCTCT=cursor.getString(2);
                        Double soTien=cursor.getDouble(3);
                        String ngay=cursor.getString(4);
                        int lCTCT=cursor.getInt(5);
                        ChiTieuThuChi ct=new ChiTieuThuChi(tenChiTieu,idCTCT,tenCTCT,soTien,ngay,lCTCT);
                        arrayList.set(Vitri,ct);
                    }
                }break;
            }
            case DanhSachCTCT.Xem:{
                if(resultCode==TroVe){
                }

            }break;
            case DanhSachCTCT.ThemMoi:{
                if(resultCode==DanhSachCTCT.LuuThemMoi){
                    Toast.makeText(DanhSachCTCT.this,"Thêm thành công ",Toast.LENGTH_LONG).show();
                    String sql="SELECT tct.TenChiTieu,ct.Id,ct.TenCTCT,ct.TienChi,ct.NgayThanhLap,tct.LoaiChiTieu from ChiTiet ct, TenChiTieu tct WHERE ct.IdTenLoaiChiTieu=tct.Id and "+idLCT+"=tct.Id";
                    Cursor cursor=dataBase.GetData(sql);
                    if(cursor.moveToLast()){
                        String tenChiTieu=cursor.getString(0);
                        int idCTCT=cursor.getInt(01);
                        String tenCTCT=cursor.getString(2);
                        Double soTien=cursor.getDouble(3);
                        String ngay=cursor.getString(4);
                        int lCTCT=cursor.getInt(5);
                        ChiTieuThuChi ct=new ChiTieuThuChi(tenChiTieu,idCTCT,tenCTCT,soTien,ngay,lCTCT);
                        arrayList.add(ct);
                    }
                }
            }break;
        }apdTer.notifyDataSetChanged();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }
}