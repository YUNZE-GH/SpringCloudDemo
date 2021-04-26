package com.gh.common.sort;

/**
 * 快速排序
 * @author gaohan
 * @version 1.0
 * @date 2021/4/25 21:49
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 6, 2, 5};
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int left = leftIndex;
        int right = rightIndex;
        // 设置基准值，待排序的左侧第一个元素作为基准值
        int key = arr[left];

        // 从左右两边交替扫描，直到left = right
        while (left < right) {
            // 从右往左扫描，找到第一个比基准值小的元素，
            while (left < right && arr[right] >= key) {
                right--;
            }
            // 找到这种元素将arr[right]放入arr[left]中
            arr[left] = arr[right];

            while (left < right && arr[left] <= key) {
                // 从左往右扫描，找到第一个比基准值大的元素
                left++;
            }
            // 找到这种元素将arr[left]放入arr[right]中
            arr[right] = arr[left];
        }
        // 基准值归位
        arr[left] = key;

        for (int a : arr) {
            System.err.print(a + " ");
        }
        System.err.println();

        // 对基准值左边的元素进行递归排序；leftIndex代表左区开始的索引，而此时的left为基准值的索引，所以left-1为左区结束的索引
        quickSort(arr, leftIndex, left - 1);
        // 对基准值右边的元素进行递归排序；此时right等于left，所以right此时也是基准值的索引,所以right+1为右区的开始索引，rightIndex为右区的结束索引
        quickSort(arr, right + 1, rightIndex);
    }

}
