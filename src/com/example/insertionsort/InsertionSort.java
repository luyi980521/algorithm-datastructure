package com.example.insertionsort;

import com.example.selectionsort.SortTestHelper;

/**
 * Created by Lyman on 2020/5/29.
 *
 * 描述：插入排序实现，算法时间复杂度为 O(n^2)
 * 实现思路：在进行插入排序时，由于第一个元素默认就是有序的，所以从第二个元素开始进行排序，
 * 排序时从后向前进行比较，如果后面的值小于前面的值，进行一次交换，否则可以直接结束本次循环。
 *
 * 总结：如果使用插入排序对无序的数据集进行排序执行时间很可能要比选择排序更长，
 * 但是如果数据集是有序的，那么插入排序的性能会优于选择排序（有序性月高，性能越高。）
 */
public class InsertionSort {

    public static void sort(Comparable[] arr) {

        // 1. 插入排序第一种写法
//        for (int i = 1; i < arr.length; i++) {
//            // 寻找 arr[i] 合适的插入位置，要从元素当前位置向前找
//            for (int j = i; j > 0; j--) {
//                if (arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j, j - 1);
//                } else {
//                    break;
//                }
//            }
//        }

        // 2. 插入排序第二种写法（将代码进行简化）
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) > 0; j--) {
//                swap(arr, j, j - 1);
//            }
//        }

        // 3. 插入排序第三种写法，将最后位置的元素保存，如果条件满足后将前面的元素后移，
        // 找到了待插入的位置后将元素以赋值的形式插入进去。
        // （这样写可以避免频繁使用 swap 函数，以赋值的方式替代了 swap 函数。）
        for (int i = 1; i < arr.length; i++) {
            Comparable e = arr[i];
            int j;  // 保存元素 e 应该插入的位置
            for (j = i; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void sort(Comparable[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            Comparable e = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * 将数组中的两个数字进行换位
     *
     * @param arr 数组
     * @param i   待交换的下标 1
     * @param j   待交换的下标 2
     */
    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(n, 10);
        Integer[] arr2 = arr1.clone();
        SortTestHelper.testSort("com.example.selectionsort.SelectionSort", arr1);
        SortTestHelper.testSort("com.example.insertionsort.InsertionSort", arr2);
    }
}
