package com.example.selectionsort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Created by Lyman on 2020/5/28.
 *
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

    /**
     * 判断排序是否有序
     *
     * @param arr 数组
     * @return 是否有序
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据 sortName 找到对应的排序算法，测试对 arr 数组排序的正确性和时间
     *
     * @param sortName 需要使用的排序方式
     * @param arr 待排序的数组
     */
    public static void testSort(String sortName, Comparable[] arr) {
        try {
            // 通过反射获取到需要使用的排序方式
            Class<?> sortClass = Class.forName(sortName);
            // 通过反射获取的对象找到需要执行的方法并传入参数类型
            Method sortMethod = sortClass.getDeclaredMethod("sort", Comparable[].class);
            // 计算排序所需时间
            long statTime = System.currentTimeMillis();
            sortMethod.invoke(sortClass, (Object) arr);
            long endTime = System.currentTimeMillis();

            assert isSorted(arr);

            System.out.println(sortClass.getSimpleName() + ": " + (endTime - statTime) + " ms");
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个近乎有序的数组
     *
     * @param n 可指定的数组长度
     * @param swapTimes 交换的次数
     * @return 返回生成的数组
     */
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < swapTimes; i++) {
            int posX = random.nextInt(n);
            int posY = random.nextInt(n);
            swap(arr, posX, posY);
        }
        return arr;
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
}
