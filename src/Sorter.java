import java.util.ArrayList;

public class Sorter {

    public static ArrayList<Integer> selectionSort(ArrayList<Integer> values) {
        for (int i = 0; i < values.size() - 1; i++) {
            int minIndex = i;

            for (int j = minIndex + 1; j < values.size(); j++) {
                if (values.get(j) < values.get(minIndex)) {
                    minIndex = j;
                }
            }
            int aux = values.get(minIndex);
            values.set(minIndex, values.get(i));
            values.set(i, aux);
        }

        return values;
    }
}
