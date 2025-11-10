import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Introduzca el tamaño de vector: ");
        int[] array = new int[new Scanner(System.in).nextInt()];
        Random random = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(10);
        Ordenacion.cronometrar(array);
    }
}