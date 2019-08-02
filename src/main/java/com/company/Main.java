package com.company;

public class Main {

  public static void main(String[] args)
  {


    if (args.length == 0)
    {
      System.out.println("No input argument(s) passed.\n");
      printHelp();
      return;
    }

    try
    {
      Program program = new Program();
      program.run(args);

    } catch (Exception e)
    {
      System.out.println(e.getMessage());
      e.printStackTrace();
      printHelp();
    }

  }

  private static void printHelp()
  {
    System.out.println("Usage:\tGitHubInfoParser [URL]...\n" +
        "\tor\tGitHubInfoParser [owner/repo]...");
  }
}



