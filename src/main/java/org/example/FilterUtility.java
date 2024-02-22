package org.example;

public class FilterUtility {

    public static void main(String[] args) throws Exception {
        Parser parser = new Parser();
        FilterUtilityManager utilityManager = new FilterUtilityManager();

        boolean isAppending = parser.parse(args);
        utilityManager.saveAllData(isAppending);
        parser.checkNeededStat(args);

    }
}