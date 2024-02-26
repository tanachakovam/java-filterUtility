package org.example;

import java.util.List;

public interface FilterManager {
    void saveAllData(boolean isAppending) throws Exception;

    void saveData(List<Object> list, Object file, boolean isAppending) throws Exception;

    void moveAllFiles(String path);

    void moveFile(String path, Object file);

    void changeFilesNames(String prefix);

    void filterFilesData(List<String> files) throws Exception;

}
