package notnor.export;

import notnor.GetDataFromGithub;
import notnor.JsonParser;
import notnor.input.RepositoryID;

import javax.json.JsonStructure;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataToOutput {

  public static List<OutputRecord> prepare(List<RepositoryID> repositoryIDsOfRequestedRepos) {
    ConcurrentHashMap<RepositoryID, JsonStructure> reposData = new ConcurrentHashMap<>();
    GetDataFromGithub.getData(repositoryIDsOfRequestedRepos, reposData);
    List<OutputRecord> outputRecords = new LinkedList<>();

    for (Map.Entry<RepositoryID, JsonStructure> entry : reposData.entrySet())
    {
      String owner = entry.getKey().getOwner();
      String repoName = entry.getKey().getRepoName();
      OutputRecord record = JsonParser.parseData(owner, repoName, entry.getValue());
      outputRecords.add(record);
    }
    return outputRecords;
  }

}
