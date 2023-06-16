package com.recursive;

import java.util.ArrayList;
import java.util.List;

public class Premutation {
    public static void main(String args[]) {
        List<String >perms= getPrem("ABC");
        for(String line:perms){
            System.out.println(line);
        }
    }

    public static List<String> getPrem(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            List<String> prem = new ArrayList<>();
            prem.add("");
            return prem;
        }

        char first = s.charAt(0);

        String rest = s.substring(1);
        List<String> prem = getPrem(rest);
        for (String line : prem) {
            for (int colIndex = 0; colIndex < line.length(); colIndex++) {
                String newLine = insertChar(line, first, colIndex);
                prem.add(newLine);
            }
        }

        return prem;
    }

    private static String insertChar(String line, char first, int colIndex) {
        return line.substring(0, first) + first + line.substring(first);
    }
}
