package org.example;

import java.util.*;

public class Port {
    // Переменная класса для хранения входных индексов
    private String[] indexes;
    // Конструктор, инициализирующий индексы
    public Port(String[] indexes) {
        this.indexes = indexes;
    }
    // Метод, преобразующий входные индексы в двумерный целочисленный массив
    public int[][] convertIndexes() {
        // Создаем пустой список для хранения последовательностей
        List<int[]> sequences = new ArrayList<>();
// Перебираем каждый входной индекс
        for (String index : indexes) {
            // разбиваем индекс на отдельные части
            String[] parts = index.split(",");
            List<Integer> sequence = new ArrayList<>();
// Перебираем каждую часть индекса
            for (String part : parts) {
                if (part.contains("-")) {
                    String[] range = part.split("-");
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);

                    for (int i = start; i <= end; i++) {
                        sequence.add(i);
                    }
                } else {
                    sequence.add(Integer.parseInt(part));
                }
            }
// Преобразуем список последовательностей в массив и добавляем его в список последовательностей
            int[] arr = sequence
                    .stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
            sequences.add(arr);
        }

        return cartesianProduct(sequences.toArray(new int[0][]));
    }
    // Метод, возвращающий cartesian  произведение массива массивов
    public int[][] cartesianProduct(int[][] arrays) {
        // подсчет общего количества комбинаций
        int total = 1;
        for (int[] array : arrays) {
            total *= array.length;
        }
// Создаем двумерный массив для хранения комбинаций
        int[][] result = new int[total][arrays.length];
// Перебор каждой комбинации
        for (int i = 0; i < total; i++) {
            int[] row = result[i];
            int n = i;
// Проходимся по каждому массиву в обратном порядке
            for (int j = arrays.length - 1; j >= 0; j--) {
                int[] array = arrays[j];
                int index = n % array.length;
                row[j] = array[index];
                n /= array.length;
            }
        }
// Возвращаем результат
        return result;
    }

    public static void main(String[] args) {
        String[] indexes = {"1,3-5", "2-5", "3-8,9" , "0"};
        Port port = new Port(indexes);
        int[][] sequences = port.convertIndexes();

        for (int[] seq : sequences) {
            System.out.println(Arrays.toString(seq));
        }
    }
}
