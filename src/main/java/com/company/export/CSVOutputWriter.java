package com.company.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;


public class CSVOutputWriter implements OutputWriter {

    public void writeOutput(List<OutputRecord> outputRecords) throws IOException {
        try (PrintWriter outFile = new PrintWriter(new FileOutputStream(new Random().nextInt(10000) +".csv"))) {
            outFile.write("Owner,Repository Name,Language,Forks number,Stars number,License,License URL\n");
            for (OutputRecord record : outputRecords
            ) {
                outFile.write(record.toString() + "\n");
            }
        }
    }
}
