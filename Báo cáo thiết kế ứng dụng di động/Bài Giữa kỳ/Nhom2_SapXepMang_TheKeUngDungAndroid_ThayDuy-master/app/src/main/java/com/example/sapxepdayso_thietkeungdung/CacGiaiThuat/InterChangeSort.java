package com.example.sapxepdayso_thietkeungdung.CacGiaiThuat;

public class InterChangeSort implements  Sort{
    @Override
    public void Sort(int[] arr, int n, String kq) {
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if (arr[i]>arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
