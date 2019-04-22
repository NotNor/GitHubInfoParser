package com.company;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


public class OutputWriter {
    public OutputWriter(List<OutputRecord> outputRecords) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(new Date().hashCode() +".csv"))) {
            out.write("Owner,Repository Name,Language,Forks number,Stars number,License,License URL\n ");
            for (OutputRecord record : outputRecords
            ) {
                out.write(record.toString());
            }
        }

    }
}
