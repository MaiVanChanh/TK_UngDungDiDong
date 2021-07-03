package com.example.sapxepdayso_thietkeungdung;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NhapDaySo extends AppCompatActivity {
    Button btnXacNhan;
    EditText txtDaySo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_day_so);
        btnXacNhan=(Button)findViewById(R.id.btnNhap_NhapDaySo);
        txtDaySo=(EditText)findViewById(R.id.txtNhap_NhapDaySo);
        txtDaySo.setText("");
        txtDaySo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if(txtDaySo.getText().toString().trim().length() < 1 )
                    {
                        txtDaySo.setBackgroundColor(0xfffff000);
                        btnXacNhan.setEnabled(false);
                    }
                    else {
                        txtDaySo.setBackgroundColor(0xffffffff);
                        btnXacNhan.setEnabled(true);
                    }
                }
                return false;
            }
        });
        Intent intent = getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        txtDaySo.setText(bundle.getString("DaySoDaNhap"));
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                String daySo=txtDaySo.getText().toString()+"";
                bundle.putString("DaySo",daySo);
                intent.putExtra("data",bundle);
                setResult(MainActivity.LuuNhap,intent);
                finish();
            }
        });
    }


}