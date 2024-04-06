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
    private File file;

    public FilterUtilityManager(File file) {
        this.file = file;
    }

    public FilterUtilityManager() {

    }

    // метод сохраняет все данные в указанный файл
    @Override
    public void save(List<String> files) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String file : files) {
                FileInputStream inputStream = new FileInputStream(file);
                Scanner scanner = new Scanner(inputStream);

                while (scanner.hasNext()) {
                    writer.write(scanner.nextLine());
                    writer.write("\n");
                }
                scanner.close();
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Ошибка в сохранении в файл.");
        }
    }

    @Override
    public void filterFilesData() throws Exception {
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

    @Override
    public void saveAllData(boolean isAppending) throws Exception {
        saveData(new ArrayList<>(integersList), integersFile, isAppending);
        saveData(new ArrayList<>(floatsList), floatsFile, isAppending);
        saveData(new ArrayList<>(stringsList), stringsFile, isAppending);
    }

    @Override
    public void saveData(List<Object> list, Object file, boolean isAppending) throws Exception {
        if (list.size() != 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(file), isAppending))) {
                for (Object object : list) {
                    writer.write(object.toString());
                    writer.newLine();
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
    public void moveAllFiles(String path) {
        moveFile(path, floatsFile);
        moveFile(path, integersFile);
        moveFile(path, stringsFile);
    }

    @Override
    public void moveFile(String path, Object file) {
        Path targetFile = Paths.get(path + "/" + file);
        try {
            Files.move(Paths.get(floatsFile), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to change the file path.");
        }
    }
}
