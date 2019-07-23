package com.company.export;

import java.util.List;

interface OutputWriter {
  void writeOutput(List<OutputRecord> outputRecords) throws Exception;
}
