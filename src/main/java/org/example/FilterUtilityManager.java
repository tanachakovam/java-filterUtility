package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterUtilityManager implements FilterManager {

    protected static List<Long> integersList = new ArrayList<>();
    protected static List<Float> floatsList = new ArrayList<>();
    protected static List<String> stringsList = new ArrayList<>();

    protected static String integersFile = "integers.txt";
    protected static String floatsFile = "floats.txt";
    protected static String stringsFile = "strings.txt";

    @Override
    public void filterFilesData(List<String> files) throws Exception {
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

    @Override
    public void saveAllData(boolean isAppending) throws Exception {
        saveInt(integersList, isAppending);
        saveFloats(floatsList, isAppending);
        saveStr(stringsList, isAppending);
    }

    @Override
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

    @Override
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

    @Override
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


    @Override
    public void changeFilesNames(String prefix) {
        floatsFile = prefix + floatsFile;
        integersFile = prefix + integersFile;
        stringsFile = prefix + stringsFile;


    }

    @Override
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

}
