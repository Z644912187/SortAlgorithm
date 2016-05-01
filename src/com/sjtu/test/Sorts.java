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
	 * �鲢����
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
	 * ������
	 * http://www.cnblogs.com/mengdd/archive/2012/11/30/2796845.html
	 * 
	 * 
	 * @param arr
	 */
	public static void HeapSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			buildMaxHeap(arr, arr.length - 1 - i);
			swap(arr, 0, arr.length - 1 - i);  // //�����Ѷ������һ��Ԫ��
		}
	}
	
	private static void buildMaxHeap(int[] arr, int lastIndex) {
		for (int i = (lastIndex-1)/2;i >= 0;i--) {
			int biggerIndex = (i*2 + 1); //��¼�ϴ��ӽڵ��±�
			if (biggerIndex <= lastIndex) {//�������ӽڵ�
				if (biggerIndex < lastIndex) { //�������ӽڵ�
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
	 * ϣ������
	 * 
	 * ����˼�룺�㷨�Ƚ�Ҫ�����һ������ĳ������d��n/2,nΪҪ�������ĸ������ֳ������飬
	 * ÿ���м�¼���±����d.��ÿ����ȫ��Ԫ�ؽ���ֱ�Ӳ�������Ȼ������һ����С��������d/2���������з��飬
	 * ��ÿ�����ٽ���ֱ�Ӳ������򡣵���������1ʱ������ֱ�Ӳ��������������ɡ�
	 * 
	 * �㷨�ĸ��Ӷ�Ϊn��1.2����
	 * 
	 * @param arr
	 */
	public static void ShellSort(int[] arr) {
		for (int increment = arr.length / 2; increment > 0; increment /= 2) {
			InsertSortWithIncrement(arr, increment);
		}
	}
	
	/**
	 * ��������
	 * 
	 * ����˼�룺��Ҫ�����һ�����У�����ǰ��(n-1) [n>=2] �����Ѿ�����
	 * ��˳��ģ�����Ҫ�ѵ�n�����嵽ǰ����������У�ʹ����n����
	 * Ҳ���ź�˳��ġ���˷���ѭ����ֱ��ȫ���ź�˳��
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
	 * ������������������򵥲�����������increment = 1��
	 * ��ϣ��������ʵ�ǶԲ���������������㷨�Ƚ�Ҫ�����һ������ĳ������d��n/2,nΪҪ�������ĸ������ֳ������飬
	 * ����һ����С��������d/2���ݹ���ò�������˼�룬����ɼ�ϣ�������㷨˼��
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
	 * ��������QuickSort��
	 * http://blog.csdn.net/pzhtpf/article/details/7560294
	 * ����������һ���͵����򣬷ֶ���֮�����ģ�ݹ���㷨���ӱ�������˵�����ǹ鲢����ľ͵ذ汾��������������������Ĳ���ɡ�
	 * ��1�� ���������1�����ݣ�ֱ�ӷ��ء�
	 * ��2�� һ��ѡ����������ߵ�ֵ��Ϊ֧�����ݡ�
	 * ��3�� �����зֳ�2���֣�һ���ֶ�����֧�����ݣ�����һ���ֶ�С��֧�����ݡ�
	 * ��4�� ���������õݹ��������С�
	 * ��������ȴ󲿷������㷨��Ҫ�졣�������ǿ�����ĳЩ����������д���ȿ����������㷨�����Ǿ�ͨ��������ԣ�
	 * û�б���������ˡ����������ǵݹ�ģ������ڴ�ǳ����޵Ļ�����˵��������һ���õ�ѡ�� 
	 * 
	 * ƽ��ʱ�临�Ӷ�n*log2(n)�������ڲ����򷽷�����ߺõģ�����������������õġ�
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
	 * ð������BubbleSort��
	 * 
	 * ð�������������������㷨����ʵ������������Ч����͵��㷨����ͨ��һ����һ�˵رȽ������е�ÿһ��Ԫ�أ�
	 * ʹ�ϴ�������³�����С����������������O(n^2)���㷨��
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
