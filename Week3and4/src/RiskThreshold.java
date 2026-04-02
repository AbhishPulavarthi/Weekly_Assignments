import java.util.Arrays;

public class RiskThreshold {

    // ---------------- Linear Search ----------------
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear: Found threshold at index " + i + " (" + comparisons + " comparisons)");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear: Threshold " + target + " not found (" + comparisons + " comparisons)");
        }
    }

    // ---------------- Binary Search for exact match ----------------
    public static int binarySearch(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                System.out.println("Binary: Found " + target + " at index " + mid + " (" + comparisons + " comparisons)");
                return mid;
            } else if (risks[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        System.out.println("Binary: " + target + " not found (" + comparisons + " comparisons)");
        return -1;
    }

    // ---------------- Binary Floor (largest <= target) ----------------
    public static int floor(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int floorValue = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                floorValue = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floorValue = risks[mid]; // candidate
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Binary Floor of " + target + ": " + floorValue + " (" + comparisons + " comparisons)");
        return floorValue;
    }

    // ---------------- Binary Ceiling (smallest >= target) ----------------
    public static int ceiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int ceilingValue = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                ceilingValue = risks[mid];
                break;
            } else if (risks[mid] < target) {
                low = mid + 1;
            } else {
                ceilingValue = risks[mid]; // candidate
                high = mid - 1;
            }
        }
        System.out.println("Binary Ceiling of " + target + ": " + ceilingValue + " (" + comparisons + " comparisons)");
        return ceilingValue;
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        int[] unsortedRisks = {50, 10, 100, 25};
        int[] sortedRisks = {10, 25, 50, 100};

        int threshold = 30;

        System.out.println("Unsorted Risks: " + Arrays.toString(unsortedRisks));
        linearSearch(unsortedRisks, threshold);

        System.out.println("\nSorted Risks: " + Arrays.toString(sortedRisks));
        binarySearch(sortedRisks, threshold);
        floor(sortedRisks, threshold);
        ceiling(sortedRisks, threshold);
    }
}