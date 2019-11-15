package notnor;

import notnor.export.DataToOutput;
import notnor.export.OutputRecord;
import notnor.input.RepositoryID;
import notnor.utils.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class WebController {

  @GetMapping("/")
  public String home() {
    return ViewNames.HOME;
  }

  @PostMapping ("/")
  public String result(@RequestParam(value = "repo", defaultValue = "") String[] repos, Model model) {

    List<RepositoryID>repositoryIDs = new LinkedList<>();

    for (int i=0; i< repos.length; i++ )
    {
      if (!repos[i].equals(""))
      {
        String owner = repos[i].split("/")[0];
        String repoName = repos[i].split("/")[1];
        repositoryIDs.add(new RepositoryID(owner, repoName));
      }
    }
    List<OutputRecord> output = DataToOutput.prepare(repositoryIDs);
    model.addAttribute("data", output) ;

    return ViewNames.RESULT;
  }

}
