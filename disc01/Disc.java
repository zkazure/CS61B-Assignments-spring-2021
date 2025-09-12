public class Disc {
    /** find position of the minimum number from k to end in inputArray */
    public static int mystery(int[] inputArray, int k) {
        int x = inputArray[k];
        int answer = k;
        int index = k+1;
        while(index < inputArray.length) {
            if (inputArray[index] < x) {
                x = inputArray[index];
                answer = index;
            }
            index = index + 1;
        }
        return answer;
    }

    /** sort the array ascending */
    public static void mystery2(int[] inutArray) {
        int index = 0;
        while (index < inputArray.length) {
            int targetIndex = mystery(inputArray, index);
            int temp = inputArray[targetIndex];
            inputArray[targetIndex] = inputArray[index];
            inputArray[index] = temp;
            index = index + 1;
        }
    }

    /** return nth number in fibonacci sequence  */
    public static int fib(int n) {
        int[] arr[n];
        arr[0] = 0, arr[1] = 1;
        for (int i=2; i<n; ++i) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n-1];
    }

    public static int fib2(int n, int k, int f0, int f1) {
        if (n==k)
            return f0 + f1;
        else
            return fib2(n, k++, f1, f0+f1);
    }
    
    public static void main(String[] args) {
        int size = 27;
        String name = "Fido";
        Dog myDog = new Dog(name, size);
        int x = size - 5;
        if (x < 15) {
            myDog.bark(8);
        }

        while (x>3) {
            x -= 1;
            myDog.play(); 
        }

        int[] numList = {2, 4, 6, 8};
        System.out.print("Hello ");
        System.out.println("Dog: " + name);

        System.out.println(numList[1]);
        if (numList[3] == 8) {
            System.out.println("potato");
        }
    }
}
