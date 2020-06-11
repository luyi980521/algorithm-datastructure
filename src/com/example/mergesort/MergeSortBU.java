package com.example.mergesort;

/**
 * Created by Lyman.
 * 描述：自底向上实现归并排序，时间复杂度 O(nlogn)
 */
public class MergeSortBU {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int size = 1; size <= n; size += size) {
            for (int i = 0; i + size < n; i += size + size) {
                // 对 arr[i, i + size - 1] 和 arr[i + size, i + 2 * size - 1] 进行归并
                MergeSort.merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1));
            }
        }
    }
}
