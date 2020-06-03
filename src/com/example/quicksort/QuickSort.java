package com.example.quicksort;

import com.example.selectionsort.SortTestHelper;

/**
 * Created by Lyman on 2020/6/3.
 * <p>
 * 描述：实现快速排序，时间复杂度 O(nlogn)
 * <p>
 * 实现思路：先找到数组最前面的元素，将该元素用变量 v 保存，并设置该元素的下标为 l，
 * 假设将数组进行分割。小于 v 的元素放在左侧，使用 arr[l + 1, j] 进行表示，
 * 大于 v 的元素放在右侧，使用 arr[j + 1, i - 1] 表示，其中 i 为待比较的元素。
 * 如果下标为 i 的元素值大于 v 那么直接将该元素添加到 arr[j + 1, i - 1] 这个区间中，并将 i++，
 * 如果小于 v 那么就将 j + 1 位置的元素与 i 位置的元素调换位置，然后 j++，i++，直至比较完成后
 * 将 v 与在 j 位置的元素进行调换，接着再进行递归操作。
 */
public class QuickSort {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 对 arr[0, n - 1] 之间的元素进行排序
        sort(arr, 0, n - 1);
    }

    /**
     * 对 arr[l, r] 位置的元素进行排序
     * @param arr 待排序的数组
     * @param l 待排序的区间中最左侧的下标
     * @param r 待排序的区间中最右侧的下标
     */
    private static void sort(Comparable[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 将 arr[l, r] 进行 partition 操作
     * @param arr 待排序的数组
     * @param l 待排序的区间中最左侧的下标
     * @param r 待排序的区间中最右侧的下标
     * @return 返回 p，使得 arr[l, p - 1] < arr[p]，arr[p + 1, r] > arr[p]
     */
    private static int partition(Comparable[] arr, int l, int r) {
        Comparable v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) <= 0) {
                swap(arr, ++j, i);
//                j++;
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 实现元素位置调换
     */
    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n - 1);
        Integer[] arr2 = arr1.clone();

        SortTestHelper.testSort("com.example.mergesort.MergeSort", arr1);
        SortTestHelper.testSort("com.example.quicksort.QuickSort", arr2);
    }
}
