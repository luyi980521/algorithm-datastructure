package com.example.selectionsort;

/**
 * Created by Lyman on 2020/5/28.
 *
 * 描述：选择排序实现，算法时间复杂度为 O(n^2)
 * 实现思路：在传入的数组中设定最小元素的下标 minIndex 为数组最前面的元素，
 * 然后将该下标处的值与后面元素的值进行比对，如果后面的元素小于它，
 * 就将 minIndex 更新为那个元素的下标，一轮循环完成后将最初 minIndex
 * 位置的元素与现在 minIndex 位置的元素交换位置。
 */
public class SelectionSort {

    /**
     * 将传入的数组进行排序
     *
     * @param arr 待排序的数
     */
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 寻找 [i, n) 区间中的最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            // 找到最小元素后交换数值的位置
            swap(arr, minIndex, i);
        }
    }

    /**
     * 将数组中的两个数字进行换位
     *
     * @param arr      数组
     * @param minIndex 最小值在数组中的索引
     * @param i        待交换的元素的索引
     */
    private static void swap(Object[] arr, int minIndex, int i) {
        Object temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
//        SelectionSort.sort(arr);
//        SortTestHelper.printArray(arr);
        SortTestHelper.testSort("com.example.selectionsort.SelectionSort", arr);
    }
}
