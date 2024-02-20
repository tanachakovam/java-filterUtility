package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class FilterUtility {
    public List<Long> integersList = new ArrayList<>();
    public List<Float> floatsList = new ArrayList<>();
    public List<String> stringsList = new ArrayList<>();

    public String integersFile = "integers.txt";
    public String floatsFile = "floats.txt";


    public String stringsFile = "strings.txt";


    public void saveInt(List<Long> integersList, boolean isAppending) throws Exception {
        if (integersList.size() != 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(integersFile, isAppending))) {
                for (Long number : integersList) {
                    writer.write(number.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new Exception("Ошибка в сохранении в файл.");
            }
        }
    }

    public void saveStr(List<String> stringsList, boolean isAppending) throws Exception {
        if (stringsList.size() != 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(stringsFile, isAppending))) {
                for (String word : stringsList) {
                    if (!word.isEmpty()) {
                        writer.write(word);
                        writer.newLine();
                    }
                }

            } catch (IOException e) {
                throw new Exception("Ошибка в сохранении в файл.");
            }
        }
    }

    public void saveFloats(List<Float> floatsList, boolean isAppending) throws Exception {
        if (floatsList.size() != 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(floatsFile, isAppending))) {

                for (Float number : floatsList) {
                    writer.write(number.toString() + "\n");
                }

            } catch (IOException e) {
                throw new Exception("Ошибка в сохранении в файл.");
            }
        }
    }

    public void load(List<String> files) throws Exception {
        try {
            for (String file : files) {
                FileInputStream inputStream = new FileInputStream(file);
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNext()) {

                    if (scanner.hasNextLong()) {
                        Long number = scanner.nextLong();
                        integersList.add(number);


                    } else if (scanner.hasNextFloat()) {
                        float number = scanner.nextFloat();
                        floatsList.add(number);

                    } else if (scanner.hasNextLine()) {
                        String string = scanner.nextLine();
                        if (!string.isEmpty()) {
                            stringsList.add(string);
                        }
                    } else {
                        scanner.next();
                    }
                }

                scanner.close();
            }

        } catch (IOException exc) {
            throw new FileNotFoundException("Файл не прочитан");
        }

    }

    public void saveAllData(boolean isAppending) throws Exception {
        saveInt(integersList, isAppending);
        saveFloats(floatsList, isAppending);
        saveStr(stringsList, isAppending);
    }

    public void changeFilesNames(String prefix) {
        floatsFile = prefix + floatsFile;
        integersFile = prefix + integersFile;
        stringsFile = prefix + stringsFile;

    }

    public void moveFiles(String path) {
        Path targetFloats = Paths.get(path + "/" + floatsFile);
        Path targetIntegers = Paths.get(path + "/" + integersFile);
        Path targetStrings = Paths.get(path + "/" + stringsFile);

        try {
            Files.move(Paths.get(floatsFile), targetFloats, StandardCopyOption.REPLACE_EXISTING);
            Files.move(Paths.get(integersFile), targetIntegers, StandardCopyOption.REPLACE_EXISTING);
            Files.move(Paths.get(stringsFile), targetStrings, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.out.println("Failed to change the file path.");
        }
    }

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


    public static void main(String[] args) throws Exception {
        FilterUtility filterUtil = new FilterUtility();
        String[] arguments = {"-p", "result_", "-o", "src/main/java", "-s", "-a", "-f", "in1.txt", "in2.txt"};
        boolean isAppending = false; // По умолчанию файлы результатов перезаписываются.

        if (arguments.length == 0) {
            throw new RuntimeException("Arguments needed.");
        } else {
            for (int i = 0; i < arguments.length; i++) {
                // Опция -p задает префикс имен выходных файлов.
                if (arguments[i].contains(".txt")) {
                    List<String> files = new ArrayList<>();
                    files.add(arguments[i]);
                    filterUtil.load(files);

                    // С помощью опции -a можно задать режим добавления в существующие файлы.
                } else if (arguments[i].equals("-a")) {
                    isAppending = true;

                } else if (arguments[i].equals("-p")) {
                    filterUtil.changeFilesNames(arguments[++i]);

                    // C помощью опции -o нужно уметь задавать путь для результатов.
                } else if (arguments[i].equals("-o")) {
                    filterUtil.moveFiles(arguments[++i]);
                }

            }
            // Запускаем цикл для статистики.
            for (String argument : arguments) {
                if (argument.equals("-s")) {
                    filterUtil.printShortStats();
                } else if (argument.equals("-f")) {
                    filterUtil.printFullStat();
                }

            }
            filterUtil.saveAllData(isAppending);
        }
    }
}