package com.sjtu.test;

public class Sorts {

	public static boolean isDebug = true;
	public static int[] test_arr = {38,49,65,97,39,76,13,27,49,78,34,12,64,1};
	
	public static void main(String[] args) {
		MergeSort(test_arr,0,test_arr.length - 1);
//		HeapSort(test_arr);
		println(test_arr);
	}
	
	
	
	/**
	 * 归并排序
	 * http://blog.csdn.net/hguisu/article/details/7776068
	 * 
	 * @param arr
	 */
	public static void MergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right)/2;
			MergeSort(arr, left, mid);
			MergeSort(arr, mid+1, right);
			merge(arr, left, mid, right);
		}
	}
	public static void merge(int[] arr,int left, int mid, int right) {
		int[] tempArr = new int[arr.length];
		int i = left;
		int m = mid + 1;
		int j = left;
		while(i <= mid && m <= right) {
			if (arr[i] < arr[m]) {
				tempArr[j++] = arr[i++];
			} else {
				tempArr[j++] = arr[m++];
			}
		}
		while(i <= mid) {
			tempArr[j++] = arr[i++];
		}
		while(m <= right) {
			tempArr[j++] = arr[m++];
		}
		while(left <= right) {
			arr[left] = tempArr[left++];
		}
	}
	
	/**
	 * 堆排序
	 * http://www.cnblogs.com/mengdd/archive/2012/11/30/2796845.html
	 * 
	 * 
	 * @param arr
	 */
	public static void HeapSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			buildMaxHeap(arr, arr.length - 1 - i);
			swap(arr, 0, arr.length - 1 - i);  // //交换堆顶和最后一个元素
		}
	}
	
	private static void buildMaxHeap(int[] arr, int lastIndex) {
		for (int i = (lastIndex-1)/2;i >= 0;i--) {
			int biggerIndex = (i*2 + 1); //记录较大子节点下表
			if (biggerIndex <= lastIndex) {//存在左子节点
				if (biggerIndex < lastIndex) { //存在右子节点
					if (arr[biggerIndex] < arr[i*2 + 2] ) {
						biggerIndex ++;
					}
				}
				if(arr[i] < arr[biggerIndex]) {
					swap(arr, i, biggerIndex);
				}
			}
		}
	}
	
	/**
	 * 希尔排序
	 * 
	 * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
	 * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，
	 * 在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
	 * 
	 * 算法的复杂度为n的1.2次幂
	 * 
	 * @param arr
	 */
	public static void ShellSort(int[] arr) {
		for (int increment = arr.length / 2; increment > 0; increment /= 2) {
			InsertSortWithIncrement(arr, increment);
		}
	}
	
	/**
	 * 插入排序
	 * 
	 * 基本思想：在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排
	 * 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
	 * 也是排好顺序的。如此反复循环，直到全部排好顺序。
	 * 
	 * O(n*n)
	 * 
	 * @param arr
	 */
	public static void InsertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i - 1;
			int temp = arr[i];
			for (;j >= 0 && temp < arr[j]; j--) {
				arr[j + 1] = arr[j];
			} 
			arr[j+1] = temp;
		}
	}
	
	/**
	 * 
	 * 插入排序的衍生，及简单插入排序即增量increment = 1；
	 * 而希尔排序其实是对插入排序的衍生，算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
	 * 再用一个较小的增量（d/2）递归调用插入排序思想，详情可见希尔排序算法思想
	 * 
	 * @param arr
	 * @param increment
	 */
	public static void InsertSortWithIncrement(int[] arr, int increment) {
		for (int i = increment; i < arr.length; i+= increment) {
			int j = i - increment;
			int temp = arr[i];
			for(;j >= 0 && temp < arr[j]; j-= increment) {
				arr[j + increment] = arr[j];
			}
			arr[j + increment] = temp;
		}
	}
	
	/**
	 * 快速排序（QuickSort）
	 * http://blog.csdn.net/pzhtpf/article/details/7560294
	 * 快速排序是一个就地排序，分而治之，大规模递归的算法。从本质上来说，它是归并排序的就地版本。快速排序可以由下面四步组成。
	 * （1） 如果不多于1个数据，直接返回。
	 * （2） 一般选择序列最左边的值作为支点数据。
	 * （3） 将序列分成2部分，一部分都大于支点数据，另外一部分都小于支点数据。
	 * （4） 对两边利用递归排序数列。
	 * 快速排序比大部分排序算法都要快。尽管我们可以在某些特殊的情况下写出比快速排序快的算法，但是就通常情况而言，
	 * 没有比它更快的了。快速排序是递归的，对于内存非常有限的机器来说，它不是一个好的选择。 
	 * 
	 * 平均时间复杂度n*log2(n)，所有内部排序方法中最高好的，大多数情况下总是最好的。
	 * 
	 * @param arr
	 */
	public static void QuickSort(int[] arr,int left, int right) {
		int partition;
		if(left < right) {
			partition = partition(arr, left, right);
			QuickSort(arr, left, partition-1);
			QuickSort(arr, partition+1, right);
		}
	}
	
	private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot)
                right--;
            if (left < right)
                arr[left++] = arr[right];
            while (left < right && arr[left] <= pivot)
                left++;
            if (left < right)
                arr[right--] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
	
	/**
	 * 
	 * 冒泡排序（BubbleSort）
	 * 
	 * 冒泡排序是最慢的排序算法。在实际运用中它是效率最低的算法。它通过一趟又一趟地比较数组中的每一个元素，
	 * 使较大的数据下沉，较小的数据上升。它是O(n^2)的算法。
	 * 
	 * @param arr
	 */
	public static void BubbleSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j >= i+1 ; j--) {
				if (arr[j] < arr[j-1]) {
					swap(arr, j, j-1);
				}
			}
		}
	}
	
	public static void swap(int[] arr,int i, int j) {
		arr[i] = arr[i] + arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
	}
	
	private static void println(String str) {
		if (isDebug) {
			System.out.println(str);
		}
	}
	
	private static void println(int[] arr) {
		StringBuffer sb = new StringBuffer();
		if (isDebug) {
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i] + ",");
			}
			System.out.println(sb.toString().substring(0, sb.length()-1));
		}
	}
	
}
