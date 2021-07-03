package com.example.sapxepdayso_thietkeungdung;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.BinaryInserlonSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.BubbeSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.FlashSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.HeapSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.InsertSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.InterChangeSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.QuickSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.SelectionSort;
import com.example.sapxepdayso_thietkeungdung.CacGiaiThuat.Sort;

public class MainActivity extends AppCompatActivity {
    Button btnNhap, btnChonGT, btnSapXep,btnclose;
    EditText txtDaySo;
    static int MaGT=0,n;
    static Sort s;
    public static final int Nhap=1;
    public static final int LuuNhap=2;
    public static final int GiaiThuat=3;
    public static final int LuuGiaiThuat=4;
    public static final int KetQuaSapXep=5;

    String TenGiaiThuat="";
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Add();


        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NhapDaySo.class);
                Bundle bundle = new Bundle();
                btnChonGT.setEnabled(true);
                if(txtDaySo.getText().toString()!=""){
                    bundle.putString("DaySoDaNhap",txtDaySo.getText().toString());
                }
                else {
                    bundle.putString("DaySoDaNhap","");
                }
                intent.putExtra("data",bundle);
                startActivityForResult(intent,MainActivity.Nhap);
            }
        });
        btnChonGT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSapXep.setEnabled(true);
                Intent intent = new Intent(MainActivity.this,GiaiThuat.class);
                startActivityForResult(intent,MainActivity.GiaiThuat);
            }
        });
        btnSapXep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String[] input = txtDaySo.getText().toString().split(" ");
                n=input.length;
                int[] arr = new int[input.length];
                for(int i=0;i<input.length;i++)
                    arr[i] = Integer.parseInt(input[i].toString());
                String dayso="";
                dayso=SapXep(MaGT,arr,n,dayso);
                Intent intent =new Intent(MainActivity.this,KetQua.class);
                Bundle bundle=new Bundle();
                bundle.putString("KetQuaSX",dayso);
                bundle.putString("TenGT",TenGiaiThuat);
               // bundle.putInt("MaGTSX",MaGT);
                intent.putExtra("data",bundle);
                startActivityForResult(intent,MainActivity.KetQuaSapXep);
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder bui=new AlertDialog.Builder(MainActivity.this);
                bui.setTitle("Xác nhận");
                bui.setMessage("Bạn có thực sự muốn thoát");
                bui.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case MainActivity.Nhap :
                if(resultCode == MainActivity.LuuNhap){
                    Bundle bundle = data.getBundleExtra("data");
                    String x= bundle.getString("DaySo");
                    txtDaySo.setText(x);
                    //    txtDaySo.setText()
                }
            case MainActivity.GiaiThuat:
                if(resultCode==MainActivity.LuuGiaiThuat){
                    Bundle bundle = data.getBundleExtra("data");
                    TenGiaiThuat= bundle.getString("GT");
                    MaGT=bundle.getInt("MaGT");
                    btnChonGT.setText(TenGiaiThuat);
                }
            case  MainActivity.KetQuaSapXep:
        }
    }
    public String TenGiaiThuat(int id){
        if(id==0){
            return ";";
        }
        return "";
    }
    public void Add(){
        btnNhap=(Button)findViewById(R.id.btnNhap_Main);
        btnChonGT=(Button)findViewById(R.id.btnChon_Main);
        btnSapXep=(Button)findViewById(R.id.btnKQ_Main);
        btnclose=(Button)findViewById(R.id.btnClose_Main);
        txtDaySo=(EditText)findViewById(R.id.txtDaySo_Main);
        txtDaySo.setEnabled(false);
        btnChonGT.setEnabled(false);
        btnSapXep.setEnabled(false);
    }
    public String SapXep(int id,int [] args,int n,String dayso){

        if(id==0){
            s= new SelectionSort();
            //InsertSort in = new InsertSort();
            s.Sort(args,n,dayso);

        }
        if(id==1){
            s= new InsertSort();
            s.Sort(args,n,dayso);

        }
        if(id == 2){
            s= new BubbeSort();
            s.Sort(args,n,dayso);

        }
        if(id == 3){
            s= new InterChangeSort();
            s.Sort(args,n,dayso);

        }
        if(id == 4){
            s= new BinaryInserlonSort();
            s.Sort(args,n,dayso);
        }
        if(id == 5){
            s= new QuickSort();
            s.Sort(args,n,dayso);
        }
        if(id == 6){
            s= new HeapSort();
            s.Sort(args,n,dayso);
        }
        if(id == 7){
            s= new FlashSort();
            s.Sort(args,n,dayso);
        }
        for(int i=0;i<n;i++){
            dayso+=String.valueOf(args[i]) +" ";
        }
       return dayso;
    }

}