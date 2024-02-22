package org.example;

import java.util.List;

public interface FilterManager {
    void saveAllData(boolean isAppending) throws Exception;

    void saveInt(List<Long> integersList, boolean isAppending) throws Exception;

    void saveFloats(List<Float> floatsList, boolean isAppending) throws Exception;

    void saveStr(List<String> stringsList, boolean isAppending) throws Exception;

    void moveFiles(String path);

    void changeFilesNames(String prefix);

    void filterFilesData(List<String> files) throws Exception;

}
