import java.util.*;
import java.util.function.Function;

public class Ordenacion {

    private static HashMap<String, Function<int[], int[]>> methods() {
        HashMap<String, Function<int[], int[]>> methods = new HashMap<>();
        methods.put("Inserción", (ints) -> {
            int[] aux = ints.clone();
            for (int i = 1; i < aux.length; i++) {
                int valor = aux[i], j = i;
                for (; j > 0; j--) {
                    if (valor < aux[j - 1])
                        aux[j] = aux[j - 1];
                    else
                        break;
                }
                aux[j] = valor;
            }
            return aux;
        });
        methods.put("Burbuja", (ints -> {
            int[] aux = ints.clone();
            for (int i = aux.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (aux[j] > aux[j + 1]) {
                        int r = aux[j];
                        aux[j] = aux[j + 1];
                        aux[j + 1] = r;
                    }
                }
            }
            return aux;
        }));
        methods.put("Mezcla", (ints -> {
            int[] aux = new int[ints.length];
            int[] a = new int[aux.length / 2], b = new int[aux.length - aux.length / 2];
            System.arraycopy(ints, 0, a, 0, aux.length / 2);
            System.arraycopy(ints, aux.length / 2, b, 0, aux.length - aux.length / 2);
            if(a.length > 1)
                a = methods.get("Mezcla").apply(a);
            if(b.length > 1)
                b = methods.get("Mezcla").apply(b);
            int auxIndex = 0, aIndex = 0, bIndex = 0;
            for (; aIndex < a.length && bIndex < b.length; auxIndex++) {
                if (a[aIndex] < b[bIndex]) {
                    aux[auxIndex] = a[aIndex];
                    aIndex++;
                }
                else {
                    aux[auxIndex] = b[bIndex];
                    bIndex++;
                }
            }
            if (aIndex < bIndex)
                System.arraycopy(a, aIndex, aux, auxIndex, a.length - aIndex);
            else
                System.arraycopy(b, bIndex, aux, auxIndex, b.length - bIndex);
            return aux;
        }));
        return methods;
    }

    public static void cronometrar(int[] array) {
        HashMap<String, Function<int[], int[]>> methods = methods();
        for (Map.Entry<String, Function<int[], int[]>> entry : methods.entrySet()) {
            String key = entry.getKey();
            Function<int[], int[]> function = entry.getValue();
            long ini, fin;
            ini = System.currentTimeMillis();
            function.apply(array);
            fin = System.currentTimeMillis();
            System.out.print(key + ": ");
            System.out.println(fin - ini + "ms");
        }
    }

    public static void seleccionar(int[] array) {
        System.out.println("\nVector: " + Arrays.toString(array));
        HashMap<String, Function<int[], int[]>> methods = methods();
        String[] names = methods.keySet().toArray(new String[0]);
        for (int i = 0; i < names.length;) {
            System.out.println(i + 1 + ": " + names[i++]);
        }
        System.out.print("Selecciona una opción: ");
        short op = (short) (new Scanner(System.in).nextShort() - 1);
        System.out.print("\nMétodo seleccionado: " + names[op] + "\nVector ordenado: " + Arrays.toString(methods.get(names[op]).apply(array)));
    }
}
