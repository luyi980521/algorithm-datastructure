package com.example.mergesort;

import com.example.insertionsort.InsertionSort;
import com.example.selectionsort.SortTestHelper;

import java.util.Arrays;

/**
 * Created by Lyman.
 * 描述：实现归并排序，算法时间复杂度 O(nlogn)
 * 实现思路：将数组分成以递归的方式分成两半，分到一定细的粒度后将向上进行归并，
 * 归并时需要创建临时空间，将原数组中的元素全部移动到临时数组中，然后通过变量指针的形式实现比较，
 * 将较小的元素添加到原数组中，直至判断结束。
 *
 * 总结；拿插入排序和归并排序做对比：
 * 当元素无序时，归并排序效率要优于插入排序.
 * 如果数组包含大量的有序元素时，插入排序效率更高。
 *
 * 第一次优化归并排序后，可以避免无效的 merge，这样归并排序的效率要比选择排序更高。
 * 第二次优化归并排序后，可以在数据量比较小的时候使用插入排序，这样可以进一步提升性能。
 */
public class MergeSort {

    /**
     * 供用户调用的排序方法
     * @param arr 待排序的数组
     */
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 对 arr[l, r] 这个区间的数进行排序
     * @param arr 待排序的数组
     * @param l 待排序数组的左边界
     * @param r 待排序数组的右边界
     */
    public static void sort(Comparable[] arr, int l, int r) {
//        if (l >= r) return;

        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int mid = (l + r) / 2;
        // 对 mid 左右两侧进行归并排序
        sort(arr, l, mid);
        sort(arr, mid + 1, r);

        // 只有当 mid 左边的元素大于右边元素是才进行 merge（可减少无效的 merge）
        // 该方式适用于近乎有序的数组
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    /**
     * 将 arr[l, mid] 和 arr[mid + 1, r] 中的数进行归并
     * @param arr 数组
     * @param l 传入的数组的左边界
     * @param mid 传入的数组的中间位置的索引
     * @param r 传入的数组的右边界
     */
    public static void merge(Comparable[] arr, int l, int mid, int r) {
//        Comparable[] aux = Arrays.copyOfRange(arr, l, r+1);
        Comparable[] aux = new Comparable[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }

        // 为 mid 左右的数组设置指针
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i >= mid + 1) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 50000;
//        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
//        Integer[] arr2 = arr1.clone();
//
//        SortTestHelper.testSort("com.example.insertionsort.InsertionSort", arr1);
//        SortTestHelper.testSort("com.example.mergesort.MergeSort", arr2);

        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(n, 10);
        Integer[] arr2 = arr1.clone();
        SortTestHelper.testSort("com.example.insertionsort.InsertionSort", arr1);
        SortTestHelper.testSort("com.example.mergesort.MergeSort", arr2);
    }
}
