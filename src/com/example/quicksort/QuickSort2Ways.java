package com.example.quicksort;

import com.example.selectionsort.SortTestHelper;

/**
 * Created by Lyman.
 * 描述：实现双路快排
 *
 * 实现思路：双路快排的实现方式与普通的快排几乎差不多，不同之处主要在于 partition 方法
 * 双路快排的标定点元素（v）是随机选择的，然后添加两个指针满足如下定义：
 * arr[l + 1, i] <= v; arr[j, r] >= v;
 * 当 arr[i] > v 时停止，否则继续 i++
 * 当 arr[j] < v 时停止，否则继续 j--
 * 当个变量都停止时进行交换，直至循环结束后将 l 与 j 进行交换。
 *
 * 总结：双路快排相对于快排更适合有大量重复元素的场景
 */
public class QuickSort2Ways {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort2(arr, 0, n - 1);
    }

    private static void sort2(Comparable[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition2(arr, l, r);
        sort2(arr, l, p - 1);
        sort2(arr, p + 1, r);
    }

    private static int partition2(Comparable[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        Comparable v = arr[l];

        // arr[l + 1, i] <= v; arr[j, r] >= v;
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(v) < 0) i++;
            while (j >= l + 1 && arr[j].compareTo(v) > 0) j--;
            if (i > j) break;
            swap(arr, i++, j--);
//            i++;
//            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        System.out.println("Test for Random Array, size = " + n + " random range [0, " + n + "]");
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n - 1);
        Integer[] arr2 = arr1.clone();

        SortTestHelper.testSort("com.example.quicksort.QuickSort2Ways", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr2);

        int swapTimes = 100;
        System.out.println("Test for Nearly Ordered Array, size = " + n + ", swap time = " + swapTimes);
        arr1 = SortTestHelper.generateNearlyOrderedArray(n, swapTimes);
        arr2 = arr1.clone();

        SortTestHelper.testSort("com.example.quicksort.QuickSort2Ways", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr2);

        System.out.println("Test for Random Array, size = " + n + " random range [0, 10]");
        arr1 = SortTestHelper.generateRandomArray(n, 0, 10);
        arr2 = arr1.clone();

        SortTestHelper.testSort("com.example.quicksort.QuickSort2Ways", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr2);
    }
}
