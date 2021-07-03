package com.example.sapxepdayso_thietkeungdung.CacGiaiThuat;

public class FlashSort implements Sort {
    @Override
    public void Sort(int[] arr, int n, String kq)
    {
        flashsort(arr);
    }


    public void swap(int[] arr, int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public String flashsort(int[] mang)
    {
        int max = 0,min = mang[0];
        int n = mang.length;
        int m = (int) (.45 * n);
        int[] l = new int[m];
        for(int i = 0; i < n;++i)
        {
            if(mang[i] < min)
            {
                min = mang[i];
            }
            if(mang[i] > mang[max])
            {
                max = i;
            }
        }
        if(min== mang[max])
        {
            return mang.toString();
        }
        double c1 = (m - 1) / (mang[max] - min);
        for(int k = 0;k < m;++k)
        {
            l[k] = 0;
        }
        for(int j = 0; j < n;++j)
        {
            int kk = (int) (c1 * (mang[j] - min));
            ++l[(kk)];
        }
        for(int p = 1; p < m;++p)
        {
            l[p] = l[p] + l[p - 1];
        }
        int hold =mang[max];
        mang[max] = mang[0];
        mang[0] = hold;
        int move = 0;int t;int flash;
        int j = 0;
        int k = m - 1;
        while (move < (n - 1))
        {
            while (j > (l[k] - 1))
            {
                ++j;
                k = (int) (c1 * (mang[j] - min));

            }
            if(k < 0)
            {
                break;
            }
            flash = mang[j];
            while (j != l[k])
            {
                k = (int) (c1 * (flash - min));
                hold = mang[t = -- l[k]];
                mang[t] = flash;
                flash = hold;
                ++move;
            }
        }
        for(j = 1; j < n;++j)
        {
            hold = mang[j];
            int i = j -1;
            while (i >= 0 && mang[i] > hold)
            {
                mang[i + 1] = mang[i--];
            }
            mang[i + 1] = hold;
        }
        return null;
    }
}
