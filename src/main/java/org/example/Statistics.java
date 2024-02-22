package org.example;


import java.util.Collections;

public class Statistics extends FilterUtilityManager {


    public void printShortStats() {
        System.out.println("Краткая статистика: ");
        System.out.println(" Количество элементов в файле " + integersFile + " = " + integersList.size());
        System.out.println(" Количество элементов в файле " + floatsFile + " = " + floatsList.size());
        System.out.println(" Количество элементов в файле " + stringsFile + " = " + stringsList.size());
    }

    public void printFullStat() {
        System.out.println("Полная статистика для целых чисел: ");
        printIntFullStat();
        System.out.println("Полная статистика для дробных чисел: ");
        printFloatFullStat();
        System.out.println("Полная статистика для строк: ");
        printStringFullStat();
    }

    public void printStringFullStat() {
        int sizeOfLongest = Integer.MIN_VALUE;
        int sizeOfShortest = Integer.MAX_VALUE;
        if (stringsList.size() != 0) {
            for (String s : stringsList) {
                if (sizeOfShortest > s.length()) {
                    sizeOfShortest = s.length();
                }
                if (sizeOfLongest < s.length()) {
                    sizeOfLongest = s.length();
                }
            }
        }
        System.out.println("Количество: " + stringsList.size());
        System.out.println("Размер самой короткой строки: " + sizeOfShortest);
        System.out.println("Размер самой длинной строки: " + sizeOfLongest);
    }

    public void printIntFullStat() {
        long sum = 0;
        long average = 0;
        if (integersList.size() != 0) { // здесь проверить или в начале
            for (int i = 0; i < integersList.size(); i++) {
                sum = sum + integersList.get(i);
                average = sum / integersList.size();
            }
        }
        System.out.println("Минимальное значение = " + Collections.min(integersList));
        System.out.println("Максимальное значение = " + Collections.max(integersList));
        System.out.println("Среднее  = " + average);
        System.out.println("Сумма = " + sum);
    }


    public void printFloatFullStat() {
        float sum = 0;
        float average = 0;
        if (floatsList.size() != 0) { // здесь проверить или в начале
            for (int i = 0; i < floatsList.size(); i++) {
                sum = sum + floatsList.get(i);
                average = sum / floatsList.size();
            }
        }
        System.out.println("Минимальное значение = " + Collections.min(floatsList));
        System.out.println("Максимальное значение = " + Collections.max(floatsList));
        System.out.println("Среднее  = " + average);
        System.out.println("Сумма = " + sum);
    }
}
