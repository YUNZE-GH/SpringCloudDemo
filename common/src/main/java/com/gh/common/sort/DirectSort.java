package com.gh.common.sort;

/**
 * 直接排序
 * @author gaohan
 * @version 1.0
 * @date 2021/4/26 22:25
 */
public class DirectSort {

    /*public static void main(String[] args) {
        int[] array = {1, 1, 2, 4, 7, 5};
        directSort(array);
    }*/

    public static void directSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        for (int item : array) {
            System.out.print(item + " ");
        }
    }
}
