package com.example.sapxepdayso_thietkeungdung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GiaiThuat extends AppCompatActivity {
    Button btnChon;
    RadioGroup rb;
    int maGT=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giai_thuat);
        btnChon=(Button)findViewById(R.id.btnClose_ChonGT);
        rb=(RadioGroup)findViewById(R.id.rbGT);

        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=getIntent();
               String x="";

               int id = rb.getCheckedRadioButtonId();
               switch(id){
                   case R.id.selectionSort_ChonGT:{
                       x="selection Sort";maGT=0;
                       break;
                   }

                   case R.id.insertSort_ChonGT:{
                       x="insert Sort";maGT=1;break;
                   }

                   case R.id.bubbleSort_ChonGT:{
                       x="bubble Sort";maGT=2;break;
                   }

                   case R.id.interchangeSort_ChonGT:{
                       x="interchange Sort";maGT=3;break;
                   }

                   case R.id.binarySort_ChonGT:{
                       x="Binary Inserlon Sort";maGT=4;break;
                   }

                   case R.id.quickSort_ChonGT:{
                       x="quick Sort";maGT=5;break;
                   }

                   case R.id.heapSort_ChonGT:{
                       x="heap Sort";maGT=6;break;
                   }

                   case R.id.flashSort_ChonGT:{
                       x="flashSort Sort";maGT=7;break;
                   }

               }
              // x=id+"";
               Bundle bundle=new Bundle();
               bundle.putString("GT",x);
               bundle.putInt("MaGT",maGT);
             //  bundle.putSerializable("DuLieu");
               intent.putExtra("data",bundle);
               setResult(MainActivity.LuuGiaiThuat,intent);
               finish();
            }
        });
    }
}