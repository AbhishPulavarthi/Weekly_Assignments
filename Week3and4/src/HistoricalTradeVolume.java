import java.util.Arrays;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTradeVolume {

    // Merge Sort (ascending, stable)
    public static void mergeSort(Trade[] arr, int left, int right, Trade[] temp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private static void merge(Trade[] arr, int left, int mid, int right, Trade[] temp) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) { // <= ensures stability
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
    }

    // Quick Sort (descending, in-place)
    public static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = lomutoPartition(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    private static int lomutoPartition(Trade[] arr, int low, int high) {
        Trade pivot = arr[high]; // pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot.volume) { // DESC order
                i++;
                Trade temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    // Merge two sorted arrays (ascending)
    public static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        int n = a.length, m = b.length;
        Trade[] merged = new Trade[n + m];
        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (a[i].volume <= b[j].volume) merged[k++] = a[i++];
            else merged[k++] = b[j++];
        }
        while (i < n) merged[k++] = a[i++];
        while (j < m) merged[k++] = b[j++];
        return merged;
    }

    // Compute total volume
    public static long totalVolume(Trade[] trades) {
        long sum = 0;
        for (Trade t : trades) sum += t.volume;
        return sum;
    }

    public static void main(String[] args) {
        // Sample input
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        System.out.println("Original trades: " + Arrays.toString(trades));

        // MergeSort ascending
        Trade[] temp = new Trade[trades.length];
        mergeSort(trades, 0, trades.length - 1, temp);
        System.out.println("MergeSort Ascending: " + Arrays.toString(trades));

        // QuickSort descending
        quickSortDesc(trades, 0, trades.length - 1);
        System.out.println("QuickSort Descending: " + Arrays.toString(trades));

        // Example: merging morning and afternoon sessions
        Trade[] morning = { new Trade("trade1", 100), new Trade("trade3", 500) };
        Trade[] afternoon = { new Trade("trade2", 300) };
        Trade[] fullDay = mergeTwoSorted(morning, afternoon);
        System.out.println("Merged Morning + Afternoon: " + Arrays.toString(fullDay));

        // Total volume
        long total = totalVolume(fullDay);
        System.out.println("Total Volume: " + total);
    }
}