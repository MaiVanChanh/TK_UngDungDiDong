package com.example.quanlychitieu.QuanTrong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.quanlychitieu.DAO.TkChiTieu_Fragment;
import com.example.quanlychitieu.Home_Fragment;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.ThemXoaSua_LietKeThongKe.ThemLoaiChiTieu;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity_Fragment extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    public static final int ThemChiTieu=0;
    public static final int LuuThemChiTieu=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__fragment);
        Add();
        getSupportFragmentManager().beginTransaction().replace(R.id.Framgment_container,new Home_Fragment()).commit();
        bottonMenu();
    }
    public void Add(){
        chipNavigationBar=(ChipNavigationBar)findViewById(R.id.btn_nav_mainFragment);
    }
    public void bottonMenu(){
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                int co=0;
                Fragment fragment=null;
                switch (i) {
                    case R.id.btn_nav_Home: {
                        fragment = new Home_Fragment();
                        break;
                    }
                    case R.id.btn_nav_ThongKe: {
                        fragment = new TkChiTieu_Fragment();
                        break;
                    }
                    case R.id.btn_nav_Them: {
                        co = 1;
                        // fragment=new Home_Fragment();
                        Intent intent = new Intent(MainActivity_Fragment.this, ThemLoaiChiTieu.class);
                        startActivityForResult(intent, MainActivity_Fragment.ThemChiTieu);
                    }
                    break;
                }
                if(co==0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.Framgment_container,fragment).commit();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Home_Fragment.ThemChiTieu){
            if(resultCode==Home_Fragment.LuuThemChiTieu){
                getSupportFragmentManager().beginTransaction().replace(R.id.Framgment_container,new Home_Fragment()).commit();
            }
        }
    }
}