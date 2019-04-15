package com.company;


import javax.json.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;


public class Main {

    public static void main(String[] args) {
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
                    String language = ((JsonString)userRepoTree.getValue("/language")).getString();
                    String forksNum = (userRepoTree.getValue("/forks")).toString();
                    String starsNum = (userRepoTree.getValue("/stargazers_count")).toString();
                    String licenseName = ((JsonString)userRepoTree.getValue("/license/name")).getString();
                    String licenseURL = ((JsonString)userRepoTree.getValue("/license/url")).getString();
                    gitHubRestApiReader.close();
                    System.out.println(owner + "," +  repoName + "," + language+ "," + forksNum+ "," +  starsNum+ "," + licenseName+ "," + licenseURL);
//                navigateTree(userRepoTree,null);
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



