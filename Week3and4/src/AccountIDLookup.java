import java.util.Arrays;

public class AccountIDLookup {

    public static int linearFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear First: index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear First: Not found (" + comparisons + " comparisons)");
        return -1;
    }

    public static int linearLast(String[] logs, String target) {
        int comparisons = 0;
        for (int i = logs.length - 1; i >= 0; i--) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear Last: index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear Last: Not found (" + comparisons + " comparisons)");
        return -1;
    }

    public static int binarySearch(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                System.out.println("Binary Search: found index " + mid + " (" + comparisons + " comparisons)");
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Binary Search: Not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Count total occurrences using binary search helper
    public static int countOccurrences(String[] logs, String target) {
        int first = firstOccurrence(logs, target);
        if (first == -1) return 0;
        int last = lastOccurrence(logs, target);
        return last - first + 1;
    }

    private static int firstOccurrence(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                high = mid - 1;
            } else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return result;
    }

    private static int lastOccurrence(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                low = mid + 1;
            } else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return result;
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        // Sample transaction logs (unsorted)
        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs: " + Arrays.toString(logs));

        // Linear search first and last
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        // Sort for binary search
        Arrays.sort(logs);
        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // Binary search
        binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");
        System.out.println("Total occurrences of accB: " + count);
    }
}