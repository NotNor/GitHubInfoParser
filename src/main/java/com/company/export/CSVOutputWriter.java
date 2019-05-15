package com.company.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CSVOutputWriter implements OutputWriter {

    public void writeOutput(List<OutputRecord> outputRecords) throws IOException {

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYMMdd_HHmmss");
        String fileName = date.format(formatter);

        try (PrintWriter outFile = new PrintWriter(new FileOutputStream(fileName +".csv"))) {
            outFile.write("Owner,Repository Name,Language,Forks number,Stars number,License,License URL\n");
            for (OutputRecord record : outputRecords
            ) {
                outFile.write(record.toString() + "\n");
            }
        }
    }
}
