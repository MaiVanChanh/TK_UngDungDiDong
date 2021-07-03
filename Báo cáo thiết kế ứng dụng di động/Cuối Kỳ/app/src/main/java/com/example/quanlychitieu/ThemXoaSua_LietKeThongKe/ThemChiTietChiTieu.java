package com.example.quanlychitieu.ThemXoaSua_LietKeThongKe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlychitieu.ChiTieuThuChi;
import com.example.quanlychitieu.DanhSachCTCT;
import com.example.quanlychitieu.DataBase;
import com.example.quanlychitieu.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ThemChiTietChiTieu extends AppCompatActivity {
    EditText txtName,txtSoTien,txtTenNhom,txtNgay;
    ImageButton btnXacNhan;
    Button btnXacNhanCapNhat,btnThoat;
    ImageView imbtnChonNgay;
    DataBase data;
   static int trangthai=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_them_chi_tiet_chi_tieu);
        int idTenLCT,idTenCTCT;
        data=  new DataBase(this,"DanhSachTenChiTieu.sqlite",null,1);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        idTenCTCT=bundle.getInt("idTenCTCT");
        idTenLCT=bundle.getInt("idTenNhomLCT");
        trangthai=bundle.getInt("TT");
        AnhXa();
        BanDau(idTenLCT,trangthai,idTenCTCT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnXacNhanCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String ten=txtName.getText().toString().trim();
                String tien=txtSoTien.getText().toString().trim();
                if(ten.length()>0 && tien.length()>0 ){
                    if(idTenCTCT>-1 && trangthai==1){
                        Double money=Double.parseDouble(tien);
                        String sql="UPDATE ChiTiet  SET  TenCTCT = '"+txtName.getText().toString()+"',NgayThanhLap = '"+txtNgay.getText().toString()+"',TienChi = '"+txtSoTien.getText().toString()+"' where Id="+idTenCTCT+"";
                        data.QueryData(sql);
                        setResult(DanhSachCTCT.luusua,intent);
                        finish();
                    }
                    if(trangthai==2){
                        Double money=Double.parseDouble(tien);
                        String date=txtNgay.getText().toString().trim();
                        String sql="INSERT into ChiTiet VALUES(NULL,"+idTenLCT+",'"+ten+"','"+date.trim()+"',"+money+")";
                       // txtNgay.setText(sql);
                        data.QueryData(sql);
                        setResult(DanhSachCTCT.LuuThemMoi,intent);
                        finish();
                    }
                    if(trangthai==0){
                        setResult(DanhSachCTCT.TroVe,intent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(ThemChiTietChiTieu.this,"Bat buoc phai nhap het thong tin",Toast.LENGTH_LONG).show();
                }
            }
        });
        imbtnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     chonNgay();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public double ChuyenSo(String x){
        double y=0;
        for(int i=0;i<x.length();i++){
            try {
                double so=Double.parseDouble(x.substring(i,1));
                y=y+ so;
                y=y*10;
            } catch(NumberFormatException e){
            }
        }
        return y;
    }
    public void chonNgay( ){
        //khai báo ngày tùy biến theo thời gian thực
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        //xây dựng DatePicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán thời gian người dùng lựa chọn
                calendar.set(year,month,dayOfMonth);
                //định dạng ngày theo kiểu ngày / tháng / năm
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //đưa dữ liệu vào edittext
                txtNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        //hiển thị DatePicker
        datePickerDialog.show();
    }
    public void BanDau(int id,int trangThai,int idTenCT) {
        Calendar cal = Calendar.getInstance();
        String ngay="";
        int month=(cal.get(Calendar.MONTH)+1);
        String thang;
        if(month<10){
            thang="0"+month;
        }
        else {
            thang=month+"";
        }
        ngay=""+cal.get(Calendar.DAY_OF_MONTH)+"/"+thang+"/"+ cal.get(Calendar.YEAR)+"";
        txtNgay.setText(ngay);
        Cursor cursor=data.GetData("select TenChiTieu from TenChiTieu WHERE Id="+id+"");
        while(cursor.moveToNext()){
            txtTenNhom.setText("Nhóm :"+cursor.getString(0));
        }
        txtTenNhom.setEnabled(false);
        if(trangthai ==1||trangthai==2){
            txtName.setEnabled(true);
            txtSoTien.setEnabled(true);
            if(trangThai==1){
                String sql="SELECT ct.TienChi,ct.TenCTCT,ct.NgayThanhLap from ChiTiet ct, TenChiTieu tct WHERE ct.IdTenLoaiChiTieu=tct.Id and "+id+"=tct.Id and "+idTenCT+"=ct.Id";
                cursor=data.GetData(sql);
                while(cursor.moveToNext()){
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    double tien=cursor.getDouble(0);
                    String usd=formatter.format(tien);
                    txtSoTien.setText(usd);
                    txtName.setText(cursor.getString(1));
                    txtNgay.setText(cursor.getString(2));
                }
            }
        }
        else{
            txtName.setEnabled(false);
            txtSoTien.setEnabled(false);
        }

    }
    public void AnhXa( ){
        txtSoTien=(EditText)findViewById(R.id.txtSoTien_ThemCTCT);
        txtTenNhom=(EditText)findViewById(R.id.txtTenNhomCTCT_ThemCTCT);
        txtName=(EditText)findViewById(R.id.txtTenCTCT_ThemCTCT);
        txtNgay=(EditText)findViewById(R.id.txtChonNgay_ThemCTCT);
        btnXacNhanCapNhat=(Button)findViewById(R.id.btnXacNhan_ThemCTCT);
        imbtnChonNgay=(ImageView)findViewById(R.id.imChonNgay_ThemCTCT);
        btnThoat=(Button)findViewById(R.id.btnThoat_ThemCTCT);
        txtNgay.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}