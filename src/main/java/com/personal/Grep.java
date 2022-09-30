package com.personal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Grep {
    private final String stringToSearch;
    private final String directoryToSearchIn;
    private final Boolean isRecursive;
    private final Boolean isIgnorecase;
    private ArrayDeque<String> queue;
    

    public Grep(GrepBuilder grepBuilder) {
        this.stringToSearch = grepBuilder.getStringToSearch();
        this.directoryToSearchIn = grepBuilder.getDirectoryToSearchIn();
        this.isIgnorecase = grepBuilder.getIsIgnorecase();
        this.isRecursive = grepBuilder.getIsRecursive();
        this.queue = new ArrayDeque<String>();
    }

    public String getStringToSearch() {
        return stringToSearch;
    }

    public String getDirectoryToSearchIn() {
        return directoryToSearchIn;
    }

    public Boolean getIsRecursive() {
        return isRecursive;
    }

    public Boolean getIsIgnorecase() {
        return isIgnorecase;
    }

    private void listFiles(){
        this.listFilesInDirectory(this.directoryToSearchIn);
    }

    private void listFilesInDirectory(String directory) {
        File[] items = new File(directory).listFiles();
        for (File file : items) {
            if (file.isDirectory()) {
                listFilesInDirectory(file.getAbsolutePath());
            } else {
                this.queue.add(file.getAbsolutePath());
            }
        }
    }

    private void searchForString() {    
        while (!this.queue.isEmpty()) {
            FileInputStream inputStream = null;
            Scanner sc = null;
            try {
                String fileToScan = this.queue.pollLast();
                try {
                    inputStream = new FileInputStream(fileToScan);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                
                sc = new Scanner(inputStream, "UTF-8");

                while (sc.hasNextLine()) {
                    Boolean contains = false;
                    String line = sc.nextLine();
                    System.out.print("here");
                    if (this.isIgnorecase) {
                        if (line.toLowerCase().contains(this.stringToSearch.toLowerCase())){
                            contains = true;
                        }
                    } else {
                        if (line.contains(this.stringToSearch)){
                            contains = true;
                        }
                    }

                    if (contains) {
                        System.out.println(String.format("File: %s\nContains line: %s", fileToScan, this.stringToSearch));
                    }
                }
                
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                if (sc != null) {
                    sc.close();
                }
            }
        }
    }

    public void runner() {
        new Thread(() -> {
            this.listFiles();
        }).start();
        
        new Thread(() -> {
            this.searchForString();
        }).start();
    }
}
