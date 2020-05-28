package com.example.selectionsort;

/**
 * Created by Lyman on 2020/5/28.
 * 描述：用于测试排序的工具类
 */
public class SortTestHelper {

    /**
     * 生成有 n 个元素的随机数组，每个元素的随机范围是 [rangeL, rangeR]
     *
     * @param n      需要生成的元素个数
     * @param rangeL 随机范围的左边界
     * @param rangeR 随机范围的有边界
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeR > rangeL;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }

    /**
     * 将给定的数组中的元素进行打印输出
     *
     * @param arr 待打印输出的数组
     */
    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
