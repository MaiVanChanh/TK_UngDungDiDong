package com.example.sapxepdayso_thietkeungdung.CacGiaiThuat;

import java.util.Arrays;

public class BinaryInserlonSort implements Sort{
    @Override
    public void Sort(int[] arr, int n, String kq) {
        for (int i = 1; i < n; i++)
        {
            int x = arr[i];

            int j = Math.abs(
                    Arrays.binarySearch(arr, 0,
                            i, x) + 1);

            // Shifting array to one
            // location right
            System.arraycopy(arr, j,
                    arr, j + 1, i - j);

            // Placing element at its
            // correct location
            arr[j] = x;
        }
    }
}
