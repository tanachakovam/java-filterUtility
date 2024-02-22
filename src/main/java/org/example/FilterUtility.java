package org.example;

public class FilterUtility {

    public static void main(String[] args) throws Exception {
        String[] arges = {"-s", "-f", "-p", "new-", "-a", "in1.txt", "in2.txt"};
        Parser parser = new Parser();
        FilterUtilityManager utilityManager = new FilterUtilityManager();

        boolean isAppending = parser.parse(arges);
        utilityManager.saveAllData(isAppending);
        parser.checkNeededStat(arges);

    }
}