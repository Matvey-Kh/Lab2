import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
public class Lab2 {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) throws IOException {
        out.println("Введите размеры массива");
        out.print("Длина N: ");
        int N = in.nextInt();
        out.print("Высота M: ");
        int M = in.nextInt();
        String[][] firstArray = new String[N][M];
        firstArray[N-1][M-1] = "0";
        int i = 0; int j = 0;
        String exsp;
        out.println("Заполнение массива:");
        while (firstArray[N-1][M-1] == "0") {
            exsp = in.nextLine();
            if (exsp.length() == 4 && exsp.charAt(0) >= '0' && exsp.charAt(0) <= '9' && exsp.charAt(3) >= '0' && exsp.charAt(3) <= '9' && exsp.charAt(1) == 'x' && exsp.charAt(2) == '+') {
                firstArray[i][j] = exsp;
                i++;
                if (i == N) {
                    i = 0; j++;
                }
            }
            else {
                out.println("Выражения должны иметь вид 'Ax+B', где A и B - цифры!");
            }
        }
        out.print("Введите число K для x: ");
        int K = in.nextInt();
        int[][] secondArray = new int[N][M];
        int[] arrayForSort = new int[N*M];
        String[] sortedArray = new String[N*M];
        int k = 0;
        for (j = 0; j < N; j++) {
            for (i = 0; i < M; i++) {
                secondArray[i][j] = (firstArray[i][j].charAt(0)-'0')*K+firstArray[i][j].charAt(3)-'0';
                out.println(firstArray[i][j].charAt(0)+"*"+K+"+"+firstArray[i][j].charAt(3)+" = "+secondArray[i][j]);
                arrayForSort[k] = secondArray[i][j];
                sortedArray[k] = firstArray[i][j];
                k++;
            }
        }
        int temp = 0; boolean replaceQ = false; int c = 1; String Temp = "";
        do {
            replaceQ = false;
            for (i = 0; i < N*M-c; i++) {
                if (arrayForSort[i] >= arrayForSort[i+1]) {
                    temp = arrayForSort[i];
                    arrayForSort[i] = arrayForSort[i+1];
                    arrayForSort[i+1] = temp;
                    if (arrayForSort[i] == arrayForSort[i+1]) {
                        if (sortedArray[i].charAt(0) > sortedArray[i+1].charAt(0)) {
                            Temp = sortedArray[i];
                            sortedArray[i] = sortedArray[i+1];
                            sortedArray[i+1] = Temp;                               
                        }
                    }
                    else {
                        Temp = sortedArray[i];
                        sortedArray[i] = sortedArray[i+1];
                        sortedArray[i+1] = Temp;    
                    }
                    replaceQ = true;
                }    
            }
            c++;
        } while (replaceQ == true);
        int maxSquare = 0;
        K = K*K; int result = 0; int resInd = 0;
        out.println("Теперь они отсортированы в порядке возрастания значений от x = K = "+K/K+", и мы можем вывести их значения в этом порядке от x = K^2 = "+K);
        for (i = 0; i < N*M; i++) {
            result = (sortedArray[i].charAt(0)-'0')*K+sortedArray[i].charAt(3)-'0';
            if (result > maxSquare) {
                maxSquare = result;
                resInd = i;
            }
            out.println(sortedArray[i].charAt(0)+"*"+K+"+"+sortedArray[i].charAt(3)+" = "+result);
        }
        out.println("Наибольшее значение выражения при x = K^2 = "+K+" - это:\n"+sortedArray[resInd].charAt(0)+"*"+K+"+"+sortedArray[resInd].charAt(3)+" = "+maxSquare);
    }
}