import java.util.*;

class Client {
    String name;
    int riskScore;
    double balance;

    Client(String name, int riskScore, double balance) {
        this.name = name;
        this.riskScore = riskScore;
        this.balance = balance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

class Processor {

    static void process(Client[] arr) {
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

        insertionSort(arr);
        System.out.println(Arrays.toString(arr));

        int k = Math.min(10, arr.length);
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void bubbleSort(Client[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int swaps = 0;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    swaps++;
                }
            }

            System.out.println("Swaps in pass " + (i + 1) + ": " + swaps);
        }
    }

    static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return a.riskScore - b.riskScore;
        }
        return Double.compare(a.balance, b.balance);
    }
}

public class ClientRiskScore {
    public static void main(String[] args) {
        Client[] arr = {
                new Client("C", 80, 1000),
                new Client("A", 20, 2000),
                new Client("B", 50, 1500)
        };

        Processor.process(arr);
    }
}