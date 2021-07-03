package com.example.sapxepdayso_thietkeungdung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KetQua extends AppCompatActivity {
    Button btnClose;
    EditText txtKetQua,txtTenGT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        btnClose=(Button)findViewById(R.id.btnClose_KetQua);
        txtKetQua=(EditText)findViewById(R.id.txtKetQua_KetQua);
        txtTenGT=(EditText)findViewById(R.id.txtTenGT_KetQua);
        Intent intent=getIntent();

        Bundle bundle=intent.getBundleExtra("data");
        txtTenGT.setText(bundle.getString("TenGT")+"");
        txtKetQua.setText(bundle.getString("KetQuaSX"));
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                setResult(MainActivity.LuuGiaiThuat,intent);
                finish();

            }
        });
    }
    public void HienTenGT(int id){
        switch(id){

        }
    }
}