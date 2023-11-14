package com.yonder.tss.utils;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static CSVWriter createCSVWriter(String filePath) {
        FileWriter fileWriter = createFileWriter(filePath);

        return Optional.ofNullable(fileWriter)
                .stream()
                .map(CSVWriter::new)
                .findFirst()
                .orElse(null);
    }

    private static FileWriter createFileWriter(String filePath) {
        File file = new File(filePath);
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            logger.error(String.format("Couldn't create writer for file [%s]", filePath));
        }
        return null;
    }
}
