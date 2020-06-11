package com.example.quicksort;

import com.example.insertionsort.InsertionSort;
import com.example.selectionsort.SortTestHelper;

/**
 * Created by Lyman.
 * 描述：实现三路快排
 *
 * 实现思路：将 arr[l, r] 这个区间的数分为 < v, == v, > v 这三部分
 * 之后递归对 < v; > v 这两部分进行三路快速排序
 *
 * 总结：三路快排在处理有大量重复数的情况下性能最优，在系统级别的算法中
 * 通常也会选择三路快排，就算数组中没有大量相同的元素，三路快排相对于快速排序和
 * 双路快排也在一个数量级。
 */
public class QuickSort3Ways {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort3(arr, 0, n - 1);
    }

    private static void sort3(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        // partition
        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        Comparable v = arr[l];
        int lt = l; // arr[l + 1, lt] < v
        int gt = r + 1; // arr[gt, r] > v
        int i = l + 1;  // arr[lt + 1, i) == v
        while (i < gt) {
            if (arr[i].compareTo(v) == 0) {
                i++;
            } else if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else {
                swap(arr, i, gt - 1);
                gt--;
            }
        }
        swap(arr, l, lt);

        sort3(arr, l, lt - 1);
        sort3(arr, gt, r);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 10000000;
        System.out.println("Test for Random Array, size = " + n + " random range [0, " + n + "]");
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n - 1);
        Integer[] arr2 = arr1.clone();
        Integer[] arr3 = arr1.clone();

        SortTestHelper.testSort("com.example.quicksort.QuickSort3Ways", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr3);

        int swapTimes = 100;
        System.out.println("Test for Nearly Ordered Array, size = " + n + ", swap time = " + swapTimes);
        arr1 = SortTestHelper.generateNearlyOrderedArray(n, swapTimes);
        arr2 = arr1.clone();

        SortTestHelper.testSort("com.example.quicksort.QuickSort3Ways", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr3);

        System.out.println("Test for Random Array, size = " + n + " random range [0, 10]");
        arr1 = SortTestHelper.generateRandomArray(n, 0, 10);
        arr2 = arr1.clone();

        SortTestHelper.testSort("com.example.quicksort.QuickSort3Ways", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr3);
    }
}
