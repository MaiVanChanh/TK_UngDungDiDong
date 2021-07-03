package com.example.sapxepdayso_thietkeungdung.CacGiaiThuat;

public class QuickSort implements Sort{
    @Override
    public void Sort(int[] arr, int n, String kq) {
        String []Chitiet=new String[1000];
        int giaTriCT = 0;
      //  int giaTriCT = 0;
        QuickSort(arr,0,n-1, Chitiet, giaTriCT);
    }

    public void swap(int[] arr, int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public void QuickSort(int[] arr, int left, int right, String[] Chitiet, int giaTriCT)
    {
        int i, j, giaTri;
        giaTri = arr[(left+right)/2];
        i = left;
        j = right;
        do {
            while(arr[i]<giaTri)
            {
                i++;
            }
            while(arr[j]>giaTri)
            {
                j--;
            }
            if(i<=j)
            {
                swap(arr, i, j);
                i++;
                j--;
            }
        }while (i<=j);
        String s = "i = " + String.valueOf(giaTriCT+1) + " :";
        for (int m=0;m<arr.length;m++)
        {
            s+= " " + String.valueOf(arr[m]);
        }
        Chitiet[giaTriCT++] = s;
        if (left<j)
        {
            QuickSort(arr,left,j,Chitiet, giaTriCT);
        }
        if (i<right)
        {
            QuickSort(arr,i,right,Chitiet, giaTriCT);
        }
    }
}
