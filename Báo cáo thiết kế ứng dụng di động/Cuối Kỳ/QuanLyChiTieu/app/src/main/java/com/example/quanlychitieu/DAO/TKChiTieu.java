package com.example.quanlychitieu.DAO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlychitieu.ChiTieuThuChi;
import com.example.quanlychitieu.ChiTieuThuChi_ApdTer;
import com.example.quanlychitieu.DanhSachCTCT;
import com.example.quanlychitieu.DataBase;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.ThemXoaSua_LietKeThongKe.ThemChiTietChiTieu;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.max;

public class TKChiTieu extends AppCompatActivity implements View.OnClickListener {
    Button btnNgayBD,btnNgayKT,btnDuyet,btnTroVe;
    TextView txtNgayBD,txtNgayKT;
    String ngayBegin,ngayEnd,ngayTK;
    TextView txtTienThu,txtTienChi;
    ListView list;
    ArrayList<ChiTieuThuChi> arrayList=new ArrayList<>();
    ArrayAdapter<ChiTieuThuChi_ApdTer> arrayAdapter=null;
    ChiTieuThuChi_ApdTer apdTer;
    int kiemTraNgayBD=0,lanDuyet=0, kiemTraNgayKT=1, trangThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_k_chi_tieu);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        add();
        btnNgayBD.setOnClickListener(this);
        btnNgayKT.setOnClickListener(this);
        btnDuyet.setOnClickListener(this);
        btnTroVe.setOnClickListener(this);
    }
    public void add(){
        btnTroVe=(Button)findViewById(R.id.btnTroVe_ThongKeCT);
        btnNgayBD=(Button)findViewById(R.id.btnNgayBD_TKCT);
        btnNgayKT=(Button)findViewById(R.id.btnNgayKT_ThongKeCT);
        txtNgayBD=(TextView)findViewById(R.id.txtNgayBD_TKCT);
        txtNgayKT=(TextView)findViewById(R.id.txtNgayKT_TK);
        btnDuyet=(Button)findViewById(R.id.btnDuyet_ThongKeCT);
        txtTienChi=(TextView)findViewById(R.id.txtTienChi_ThongKe);
        txtTienThu=(TextView)findViewById(R.id.txtTienThu_ThongKe);
        list=(ListView)findViewById(R.id.lv_TKChiTieu);
        Calendar calendar = Calendar.getInstance();
        Date date=Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        txtNgayKT.setText(simpleDateFormat.format(date));
    }
    public void chonNgay(final int loai){
        //khai báo ngày tùy biến theo thời gian thực
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        //xây dựng DatePicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(TKChiTieu.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán thời gian người dùng lựa chọn
                calendar.set(year,month,dayOfMonth);
                //định dạng ngày theo kiểu ngày / tháng / năm
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //đưa dữ liệu vào edittext
                if(loai==1)
                {
                    txtNgayBD.setText(simpleDateFormat.format(calendar.getTime()));
                }
                else
                {
                    txtNgayKT.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }
        },nam,thang,ngay);
        //hiển thị DatePicker
        datePickerDialog.show();
    }
    public int KiemTra(int ngayBD,int ngayKT){
        int ngay, thang, nam ;
        int bool=0;
        int ngay_khac, thang_khac, nam_khac ;
        if(ngayBD==1){
            ngay = Integer.parseInt(txtNgayBD.getText().toString().substring(0,2));
            thang = Integer.parseInt(txtNgayBD.getText().toString().substring(3,5));
            nam = Integer.parseInt(txtNgayBD.getText().toString().substring(6));
        }
        else{
            Toast.makeText(getApplicationContext(),"Chưa nhập ngày bắt đầu",Toast.LENGTH_LONG).show();
            return 0;
        }
       if(ngayKT==1){
           ngay_khac = Integer.parseInt(txtNgayKT.getText().toString().substring(0,2));
           thang_khac = Integer.parseInt(txtNgayKT.getText().toString().substring(3,5));
           nam_khac = Integer.parseInt(txtNgayKT.getText().toString().substring(6));
       }
       else{
           Toast.makeText(getApplicationContext(),"Chưa nhập ngày kết thúc",Toast.LENGTH_LONG).show();
           return 0;
       }
       if(nam_khac < nam ){
           Toast.makeText(getApplicationContext(),"Năm bắt đầu không hợp lệ",Toast.LENGTH_LONG).show();
           return 0;
       }
       else{
           if(nam_khac > nam){
               return 1;
           }
           else{
               if(thang_khac >=thang){
                   if(thang_khac >thang)
                         return 1;
                   else{
                       if(ngay_khac <ngay){
                           Toast.makeText(getApplicationContext(),"Ngày bắt đầu không hợp lệ",Toast.LENGTH_LONG).show();
                           return 0;
                       }
                       else{
                           return 1;
                       }
                   }
               }
               else{
                   Toast.makeText(getApplicationContext(),"Tháng bắt đầu không hợp lệ",Toast.LENGTH_LONG).show();
                   return 0;
               }
           }
       }
    }
    public int XuLyNGay(String y){
        int ngay, thang, nam,ngay_khac, thang_khac, nam_khac,x,xx,xxx ;
        //date Begin
        ngay = Integer.parseInt(txtNgayBD.getText().toString().substring(0,2));
        thang = Integer.parseInt(txtNgayBD.getText().toString().substring(3,5));
        nam = Integer.parseInt(txtNgayBD.getText().toString().substring(6));
        //dateNow
        x = Integer.parseInt(y.substring(0,2));
        xx = Integer.parseInt(y.substring(3,5));
        xxx = Integer.parseInt(y.substring(6));

        //Date End
        ngay_khac = Integer.parseInt(txtNgayKT.getText().toString().substring(0,2));
        thang_khac = Integer.parseInt(txtNgayKT.getText().toString().substring(3,5));
        nam_khac = Integer.parseInt(txtNgayKT.getText().toString().substring(6));
        //chuyen ngay
       // Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse();
        //String dateNow=y,dateEnd=txtNgayKT.getText().toString().trim(),dateBegin=txtNgayBD.getText().toString().trim();
        //Date ngayBD=new SimpleDateFormat("dd/MM/yyyy").parse(dateBegin);

       if(xxx <= nam_khac && xxx >= nam){
           if(xxx == nam_khac && xxx == nam){
               if(xx <=thang_khac && xx >= thang_khac){
                   if(x <= ngay_khac && x >= ngay) {
                       return 1;
                   }
               }
           }
           else{
               //Toast.makeText(TKChiTieu.this,""+xxx+""+nam_khac+""+ nam+"",Toast.LENGTH_LONG).show();
               return 1;
           }

       }

        return 0;
    }
    public String HienDangTien(String s){
        Double t=Double.parseDouble(s);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        String str2 = vn.format(t);
        return str2;
    }
    public String daoXau(String s) {
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            s1 += s.charAt(s.length() - 1 - i);
        }
        return s1; //xuat chuoi sau khi dao
    }
    public String TinhTong(String s1, String s2) {
        String s = "";

        int len1 = s1.length();
        int len2 = s2.length();
        int max = max(len1, len2); //ham max length xuat ra gia tri chieu dai lon nhat trong 2 chuoi
        s1 = daoXau(s1);
        s2 = daoXau(s2);
        //thuc hien noi them so 0 vao chuoi voi muc dich lam cho 2 chuoi so bang nhau
        if (max > len1) {
            for (int i = max; i >= len1; i--) {
                s1 += "0";
            }
        }
        if (max > len2) {
            for (int i = max; i >= len2; i--) {
                s2 += "0";
            }
        }

        //thuc hien phep cong
        int soGhiNho = 0;
        for (int i = 0; i < max; i++) {
            int tong = 0;
            //charAt ky tu tai vi tri thu i trong xau
            tong = s1.charAt(i) - '0' + s2.charAt(i) - '0' + soGhiNho;
            s += tong % 10;
            soGhiNho = tong / 10;
        }
        if (soGhiNho == 1) {
            s += 1;
        }
        //thuc hien dao xau truoc khi xuat
        s = daoXau(s);
        return s;
    }
    public void Duyet(){
        String a="0";
        String b="0";
        DataBase ddBase = new DataBase(TKChiTieu.this,"DanhSachTenChiTieu.sqlite",null,1);
            if(lanDuyet==1){
                arrayList.clear();
                Intent intent=getIntent();
                Bundle bundle=intent.getBundleExtra("data");
                int idLCT=bundle.getInt("idLCT");
                String sql="SELECT tct.TenChiTieu,ct.Id,ct.TenCTCT,ct.TienChi,ct.NgayThanhLap,tct.LoaiChiTieu from ChiTiet ct, TenChiTieu tct WHERE ct.IdTenLoaiChiTieu=tct.Id and tct.Id="+idLCT+"";
                Cursor cursor=ddBase.GetData(sql);
                while (cursor.moveToNext()){
                    String tenChiTieu=cursor.getString(0);
                    int idCTCT=cursor.getInt(01);
                    String tenCTCT=cursor.getString(2);
                    Double soTien=cursor.getDouble(3);
                    String ngay=cursor.getString(4);
                    int lCTCT=cursor.getInt(5);
                    if(XuLyNGay(ngay.trim())==1){
                        ChiTieuThuChi ct=new ChiTieuThuChi(tenChiTieu,idCTCT,tenCTCT,soTien,ngay,lCTCT);
                        arrayList.add(ct);
                        if(lCTCT==0){
                            int x=(int) Math.round(soTien);
                            String temp=TinhTong(a,x+"");
                            a=temp;
                        }
                        if(lCTCT==1){
                            int y=(int) Math.round(soTien);
                            String temp=TinhTong(b,y+"");
                            b=temp;
                        }
                    }
                }
                apdTer=new ChiTieuThuChi_ApdTer(TKChiTieu.this,R.layout.dongchitieuchitiet,arrayList);
                list.setAdapter(apdTer);
                txtTienThu.setText(HienDangTien(a));
                txtTienChi.setText(HienDangTien(b));
        }
        lanDuyet=0;
    }
    @Override
    public void onClick(View view) {
        if(view==btnNgayBD){
            kiemTraNgayBD=1;
            chonNgay(1);
            lanDuyet=1;
        }
        if(view==btnNgayKT){
            chonNgay(2);
            kiemTraNgayKT=1;
            lanDuyet=1;
        }
        if(view==btnDuyet){
            if(KiemTra(kiemTraNgayBD,kiemTraNgayKT)>0){
                Duyet();
            }
        }
        if(view==btnTroVe){
            finish();
        }
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