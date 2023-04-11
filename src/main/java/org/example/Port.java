package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Port {

    // Переменная класса для хранения входных индексов
    private final String[] indexes;

    // Конструктор, инициализирующий индексы
    public Port(String[] indexes) {
        this.indexes = indexes;
    }

    // Метод, преобразующий входные индексы в двумерный целочисленный массив
    public int[][] convertIndexes() {

        // Создаем пустой список для хранения последовательностей
        List<List<Integer>> sequences = new ArrayList<>();

        // Перебираем каждый входной индекс
        for (String index : indexes) {

            // разбиваем индекс на отдельные части
            String[] parts = index.split(",");
            List<Integer> sequence = new ArrayList<>();

            // Перебираем каждую часть индекса
            String intSplitter = "-";
            for (String part : parts) {
                if (part.contains(intSplitter)) {
                    String[] range = part.split(intSplitter);
                    IntStream
                            .range(Integer.parseInt(range[0]), Integer.parseInt(range[range.length - 1]))
                            .forEach(sequence::add);
                } else {
                    sequence.add(Integer.parseInt(part));
                }
            }
            sequences.add(sequence);
        }

        return cartesianProduct(sequences);
    }

    // Метод, возвращающий cartesian  произведение массива массивов
    public int[][] cartesianProduct(List<List<Integer>> arr) {

        // подсчет общего количества комбинаций
        final AtomicInteger total = new AtomicInteger(1);
        arr.forEach(totLstSize -> total.set(total.get() * totLstSize.size()));

        // Создаем двумерный массив для хранения комбинаций
        int[][] result = new int[total.get()][arr.size()];

        // Перебор каждой комбинации
        for (int i = 0; i < total.get(); i++) {
            int[] row = result[i];
            int counter = i;

            // Проходимся по каждому массиву в обратном порядке
            for (int j = arr.size() - 1; j >= 0; j--) {
                List<Integer> array = arr.get(j);
                int index = counter % array.size();
                row[j] = array.get(index);
                counter /= array.size();
            }
        }
        // Возвращаем результат
        return result;
    }
}
/*
    public static void main(String[] args) {
        String[] indexes = {"1,3-5", "2-5", "3-8-12,9-14" , "0"};
        Port port = new Port(indexes);
        int[][] sequences = port.convertIndexes();

        for (int[] seq : sequences) {
            System.out.println(Arrays.toString(seq));
        }
    }
}
*/
