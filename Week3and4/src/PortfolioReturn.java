import java.util.Arrays;

class Asset {
    String symbol;
    double returnRate;  // e.g., 12.0 for 12%
    double volatility;  // tie-breaker

    Asset(String symbol, double returnRate, double volatility) {
        this.symbol = symbol;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return symbol + ":" + returnRate + "%";
    }
}

public class PortfolioReturn {

    // ---------------- Merge Sort (Ascending, Stable) ----------------
    public static void mergeSort(Asset[] arr, int left, int right, Asset[] temp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private static void merge(Asset[] arr, int left, int mid, int right, Asset[] temp) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {  // stable
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
    }

    // ---------------- Quick Sort (Descending by returnRate, Ascending by volatility) ----------------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        Asset pivot = medianOfThree(arr[low], arr[mid], arr[high]);

        // Move pivot to end
        int pivotIndex = high;
        for (int i = low; i <= high; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        Asset temp = arr[pivotIndex]; arr[pivotIndex] = arr[high]; arr[high] = temp;
        pivot = arr[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    private static Asset medianOfThree(Asset a, Asset b, Asset c) {
        if ((a.returnRate > b.returnRate) == (a.returnRate < c.returnRate)) return a;
        else if ((b.returnRate > a.returnRate) == (b.returnRate < c.returnRate)) return b;
        else return c;
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 5.0),
                new Asset("TSLA", 8.0, 7.0),
                new Asset("GOOG", 15.0, 4.0),
                new Asset("MSFT", 12.0, 3.0) // same returnRate as AAPL to test stability
        };

        System.out.println("Original Assets: " + Arrays.toString(assets));

        // Merge Sort ascending
        Asset[] temp = new Asset[assets.length];
        mergeSort(assets, 0, assets.length - 1, temp);
        System.out.println("MergeSort Ascending: " + Arrays.toString(assets));

        // Quick Sort descending
        quickSort(assets, 0, assets.length - 1);
        System.out.println("QuickSort Descending: " + Arrays.toString(assets));
    }
}