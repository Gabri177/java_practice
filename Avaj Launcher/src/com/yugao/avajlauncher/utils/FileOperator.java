package com.yugao.avajlauncher.utils;

import com.yugao.avajlauncher.exception.InputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileOperator {

    private static List<String> fileContent = new ArrayList<String>();

    public static void readFileContent(String fileName) throws InputException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null){
                if (line.trim().isEmpty())
                    throw new InputException("File Format Error: Unexpected new line found!!!");
                FileOperator.fileContent.add(line);
            }
        } catch (Exception e) {
            throw new InputException(e.getMessage());
        }
    }

    public static void outPutFileContent() {
        for (String line : fileContent) {
            System.out.println(line);
        }
    }

    public static List<String> getFileContent() {
        return fileContent;
    }


}
