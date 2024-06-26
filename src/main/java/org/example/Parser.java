package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    Statistics statistics = new Statistics();
    FilterUtilityManager filterUtil = new FilterUtilityManager(new File("common.txt"));


    public boolean parse(String[] args) throws Exception {
        boolean isAppending = false; // По умолчанию файлы результатов перезаписываются.

        if (args.length == 0) {
            throw new RuntimeException("args needed.");
        } else {
            for (int i = 0; i < args.length; i++) {

                if (args[i].contains(".txt")) {
                    List<String> files = new ArrayList<>();
                    files.add(args[i]);
                    filterUtil.save(files);
                    filterUtil.filterFilesData();

                    // С помощью опции -a можно задать режим добавления в существующие файлы.
                } else if (args[i].equals("-a")) {
                    isAppending = true;

                    // Опция -p задает префикс имен выходных файлов.
                } else if (args[i].equals("-p")) {
                    filterUtil.changeFilesNames(args[++i]);

                    // C помощью опции -o нужно уметь задавать путь для результатов.
                } else if (args[i].equals("-o")) {
                    filterUtil.moveAllFiles(args[++i]);
                }

            }
        }
        return isAppending;
    }

    public void checkNeededStat(String[] args) {
        // Запускаем цикл для статистики.
        for (String argument : args) {
            if (argument.equals("-s")) {
                statistics.printShortStats();
            } else if (argument.equals("-f")) {
                statistics.printFullStat();
            }

        }
    }

    public void checkIfArgsCorrect(String[] args) {
        List<String> arguments = new ArrayList<>(List.of(args));
        if (arguments.contains("-s") & arguments.contains("-f")) {
            throw new RuntimeException("args are incorrect. You can not use -s and -f args together.");
        }

    }


}
