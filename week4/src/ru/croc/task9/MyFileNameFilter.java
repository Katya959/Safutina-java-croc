package ru.croc.task9;

import java.io.File;
import java.io.FilenameFilter;

// Реализация интерфейса FileNameFilter
public class MyFileNameFilter implements FilenameFilter {

    private String ext;

    public MyFileNameFilter(String ext){
        this.ext = ext.toLowerCase();
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(ext);
    }
}
