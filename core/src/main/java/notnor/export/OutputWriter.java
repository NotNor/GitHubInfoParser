package notnor.export;

import java.util.List;

public interface OutputWriter {
  void writeOutput(List<OutputRecord> outputRecords) throws Exception;
}
