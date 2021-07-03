package com.example.sapxepdayso_thietkeungdung.CacGiaiThuat;

public class InsertSort implements Sort{
    @Override
    public void Sort(int[] arr, int n,String kq) {
        kq="";
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        for(int i=0;i<n;i++){
            kq=String.valueOf(arr[i])+" ";
        }

    }

}
