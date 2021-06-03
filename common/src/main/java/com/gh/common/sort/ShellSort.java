package com.gh.common.sort;


import java.util.Arrays;

/**
 * 希尔排序
 * @author gaohan
 * @version 1.0
 * @date 2021/4/24 23:49
 */
public class ShellSort {
    /*public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 5, 4, 9, 7, 8};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));

    }*/

    // 希尔排序
    public static void shellSort(int[] arr) {
        int count = 0;
        // 挨个遍历步长，缩短步长，直到步长为零
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            // 遍历所有的元素
            for (int i = gap; i < arr.length; i++) {
                // 遍历本组中所有的元素,从本组第一个元素开始
                for (int index = i - gap; index >= 0; index -= gap) {   // index=index-gap本组前面的那个元素
                    count++;
                    // 如果当前元素大于加上步长之后的那个元素,（前面的比后面的大了）交换
                    if (arr[index] > arr[index + gap]) {
                        int temp = arr[index];
                        arr[index] = arr[index + gap];
                        arr[index + gap] = temp;
//                        break;
                    }

                }
            }
        }
        System.err.println(count);
    }
}
