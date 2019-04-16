package com.company;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<OutputRecord> output = new LinkedList<>();
        for (String requestedGitHubRepo:args) {
            String[] repoOwnerAndName = extractRepoOwnerAndName(requestedGitHubRepo.split("/"));
            if(repoOwnerAndName != null) {
                String owner = repoOwnerAndName[0];
                String repoName = repoOwnerAndName[1];

                try {
                    URL url = new URL("https://api.github.com/repos/" + owner + "/" + repoName);

                    HttpsURLConnection gitHubRestApi = (HttpsURLConnection) url.openConnection();
                    JsonReader gitHubRestApiReader = Json.createReader(gitHubRestApi.getInputStream());
                    JsonStructure userRepoTree = gitHubRestApiReader.read();
                    com.company.OutputRecord record = new com.company.OutputRecord(owner, repoName, userRepoTree);
                    output.add(record);
                    gitHubRestApiReader.close();

                } catch (Exception e) {
                    printHelp();
                }
            } else {
                printHelp();
            }
        }
   }

private static String[] extractRepoOwnerAndName(String[] repoLink) {
        String[] repoOwnerAndName = new String[2];
        if (repoLink.length == 2) {
            repoOwnerAndName = repoLink;
        } else if (repoLink.length >= 4 && (repoLink[0].equals("http:") || repoLink[0].equals("https:"))) {
            repoOwnerAndName[0]=repoLink[3];
            repoOwnerAndName[1]=repoLink[4];
        } else if (repoLink.length >= 3 && (repoLink[0].equals("www.github.com") || repoLink[0].equals("github.com"))) {
            repoOwnerAndName[0]=repoLink[1];
            repoOwnerAndName[1]=repoLink[2];

    } else {
            repoOwnerAndName = null;
        }

return repoOwnerAndName;
}

private static void printHelp() {
    System.out.println("Usage:\tGitHubInfoParser [URL]...\n" +
            "\tor\tGitHubInfoParser [owner/repo]...");
}



//public static void navigateTree(JsonValue tree, String key) {
//    if (key != null)
//        System.out.print("Key " + key + ": ");
//
//
//    switch(tree.getValueType()) {
//        case OBJECT:
//            System.out.println("OBJECT");
//            JsonObject object = (JsonObject) tree;
//            for (String name : object.keySet())
//                navigateTree(object.get(name), name);
//            break;
//        case ARRAY:
//            System.out.println("ARRAY");
//            JsonArray array = (JsonArray) tree;
//            for (JsonValue val : array)
//                navigateTree(val, null);
//            break;
//        case STRING:
//            JsonString st = (JsonString) tree;
//            System.out.println("STRING " + st.getString());
//            break;
//        case NUMBER:
//            JsonNumber num = (JsonNumber) tree;
//            System.out.println("NUMBER " + num.toString());
//            break;
//        case TRUE:
//        case FALSE:
//        case NULL:
//            System.out.println(tree.getValueType().toString());
//            break;
//    }
//}


}



