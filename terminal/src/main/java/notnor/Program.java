package notnor;

import notnor.export.CSVOutputWriter;
import notnor.export.DataToOutput;
import notnor.export.OutputRecord;
import notnor.export.SQLiteOutputWriter;
import notnor.input.InputParser;
import notnor.input.RepositoryID;

import java.io.IOException;
import java.util.List;

public class Program {
  private List<OutputRecord> output = null;

  void run(String[] args) throws Exception
  {


    InputParser inputParser = new InputParser();
    inputParser.parse(args);

    List<RepositoryID> repositoryIDsOfRequestedRepos = inputParser.getRepositoryIDs();

    output = DataToOutput.prepare(repositoryIDsOfRequestedRepos);


    try
    {
      new CSVOutputWriter().writeOutput(output);

      new SQLiteOutputWriter().writeOutput(output);

    } catch (Exception e)
    {
      e.printStackTrace();
      throw new IOException("Failed to write file to disk");
    }


  }
}
