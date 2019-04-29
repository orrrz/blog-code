import java.util.Arrays;

/**
 * Created by icejam.
 */
public class Sorts {
    // ----------------------------------------------BubbleSort--------------------------------------
    // BubbleSort Solution1
    public static void bubble(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {    // 排序多少次，每执行完一轮排序，需要排序的个数减1,排序结束后最后一个数为最大值，不用管；
            for (int j = 0; j < i; j++) {    // 临界值的j != i，否则越界
                if (arr[j] > arr[j + 1]) {  // 相邻两个数排序并替换
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // BubbleSort Solution2
    public static void bubbleSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // 循环次数为数据个数 -1
            // 比较相邻两个数，若arr[j] > arr[j + 1]，则执行swap()
            // a[j]就像一个气泡一样“浮”到合适位置了
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // ----------------------------------------------SelectSort--------------------------------------

    // SelectSort
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  // 循环次数为数据个数 -1
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { //
                // 找最小值的下标
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // 里面的循环完，minIndex已经是最小值的下标
            // 交换最小值和第一个位置的数据
            if (i != minIndex) {
                swap(arr, i, minIndex);
            }
        }
    }


    // ----------------------------------------------InsertSort--------------------------------------
    // InsertSort Solution 1    移动法
    // 从第二个数据开始插入排序
    public static void insertSort(int[] arr) {
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];  // 当前数据
            int j = i - 1;  // 往前插入的起始位置
            while (j >= 0 && arr[j] > temp) { // 和当前数据的前面所有数据进行比较
                arr[j + 1] = arr[j];  // 往后移
                j--;
            }
            // 找到插入的位置，把该数据放到插入位置的后面
            arr[j + 1] = temp;
        }
    }


    // InsertSort  Solution 2   跟Arrays.class  jdk1.8第1345行源码方式一样，交换法
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap2(arr, j, j - 1);
                }
            }
        }
    }


    // ----------------------------------------------ShellSort--------------------------------------
    // 插入时采用交换法
    public static void shellSort1(int[] arr) {
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    //插入排序采用交换法
                    swap(arr, j, j - gap);
                    j -= gap;
                }
            }
        }
    }

    // 插入时采用移动法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动法
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    // ----------------------------------------------QuickSort--------------------------------------
    // QuickSort  Solution 1
    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        } else {
            int middle = findMiddle(arr, low, high);
            quickSort(arr, low, middle - 1);
            quickSort(arr, middle + 1, high);
        }
    }

    // 寻找分界点
    public static int findMiddle(int[] arr, int low, int high) {
        int temp = arr[high];    // 参照物，取最右边的数为基准数
        int left = low;     // 左下标
        int right = high - 1;   // 右下标

        while (true) {
            // 从左边找第一个比参照物大的值
            while (left < high && arr[left] <= temp) {
                left++;
            }
            if (left == high) {
                // 参照物是最大值
                break;
            }

            // 从右边找第一个比参照物小的值
            while (right >= low && arr[right] >= temp) {
                right--;
            }

            // 判断是否交叉
            if (left > right) {
                // 是， 交换左下标和参照物的值，结束，左下标把数据分成两半，结束循环
                swap(arr, left, high);
                break;
            } else {
                // 否，交换左右下标的值，继续
                swap(arr, left, right);
                continue;
            }
        }
        return left;
    }

    // quickSort Solution 2
    private static void quickSort2(int[] arr, int low, int high) {

        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndex(arr, low, high);

            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            quickSort2(arr, 0, index - 1);
            quickSort2(arr, index + 1, high);
        }

    }

    private static int getIndex(int[] arr, int low, int high) {
        // 基准数据
        int tmp = arr[low];
        while (low < high) {
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high] = arr[low];

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low] = tmp;
        return low; // 返回tmp的正确位置
    }


    // 递归实现一个数的阶乘
    public static void multi(int n) {
        System.out.println(factor(n));
    }
    private static int factor(int n) {
        if (n <= 1) return 1;
        return n * factor(n - 1);
    }


    // -----------------------------------------Merge Sort----------------------------------------
    private static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];   // 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        mergeSort(arr, 0, arr.length - 1, temp);
    }
    private static void mergeSort(int[]arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);   // 右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);     // 将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;   // 左序列指针
        int j = mid + 1;// 右序列指针
        int t = 0;      // 临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {  //将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {    //将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        // 将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }

    }


    // ----------------------------------------------Main--------------------------------------

    public static void main(String[] args) {
        int[] arr = initArr();
        printArr(arr);
//        bubble(arr);
//        selectSort(arr);
//        insertSort(arr);
//        insertSort2(arr);
//        shellSort1(arr);
//        shellSort2(arr);
//        quickSort2(arr, 0, arr.length - 1);
        mergeSort(arr);
//        multi(5);
        printArr(arr);
    }


    // 交换两个数
    public static void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    public static void swap2(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    // 初始化一个数组
    public static int[] initArr() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.ceil((Math.random() * 100));
        }
        return arr;
    }

    // 打印数组
    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i] + ",");
            } else {
                sb.append(arr[i] + "]");
            }
        }
        System.out.println(sb);
    }
}
