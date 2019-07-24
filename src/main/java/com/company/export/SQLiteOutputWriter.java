package com.company.export;

import java.sql.*;
import java.util.List;

public class SQLiteOutputWriter implements OutputWriter {


  public static final String DB_NAME = "githubReposInfo.db";
  public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

  public static final String TABLE_OWNER = "owner";
  public static final String OWNER_COLUMN_ID = "_id";
  public static final String OWNER_COLUMN_NAME = "name";

  public static final String TABLE_REPO_INFO = "repo_info";
  public static final String REPO_COLUMN_OWNER_ID = "_id";
  public static final String REPO_COLUMN_REPO_NAME = "name";
  public static final String REPO_COLUMN_LANGUAGE = "language";
  public static final String REPO_COLUMN_FORKS_NUM = "forks";
  public static final String REPO_COLUMN_STARS_NUM = "stars";
  public static final String REPO_COLUMN_LICENSE_NAME = "license";
  public static final String REPO_COLUMN_LICENSE_URL = "lic_url";

  public static final String QUERY_OWNER = "SELECT " + OWNER_COLUMN_ID + " FROM " +
      TABLE_OWNER + " WHERE " + OWNER_COLUMN_NAME + "=? COLLATE NOCASE";

  public static final String INSERT_OWNER = "INSERT OR IGNORE INTO " + TABLE_OWNER + " (" + OWNER_COLUMN_NAME + ") " +
      "VALUES(?)";

  public static final String INSERT_REPO = "INSERT OR IGNORE INTO " + TABLE_REPO_INFO +
      " (" +
      REPO_COLUMN_OWNER_ID + "," +
      REPO_COLUMN_REPO_NAME + "," +
      REPO_COLUMN_LANGUAGE + "," +
      REPO_COLUMN_FORKS_NUM + "," +
      REPO_COLUMN_STARS_NUM + "," +
      REPO_COLUMN_LICENSE_NAME + "," +
      REPO_COLUMN_LICENSE_URL +
      ") VALUES(?,?,?,?,?,?,?)";


  @Override
  public void writeOutput(List<OutputRecord> outputRecords) throws Exception
  {
    try (Connection sqliteDB = DriverManager.getConnection(CONNECTION_STRING);
         Statement statement = sqliteDB.createStatement())
    {
      sqliteDB.setAutoCommit(false);

      statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_OWNER +
          " (" + OWNER_COLUMN_ID + " INTEGER PRIMARY KEY, " +
          OWNER_COLUMN_NAME + " TEXT UNIQUE COLLATE NOCASE" +
          ")");

      statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_REPO_INFO +
          " (" + REPO_COLUMN_OWNER_ID + " INTEGER, " +
          REPO_COLUMN_REPO_NAME + " TEXT UNIQUE COLLATE NOCASE, " +
          REPO_COLUMN_LANGUAGE + " TEXT, " +
          REPO_COLUMN_FORKS_NUM + " INTEGER, " +
          REPO_COLUMN_STARS_NUM + " INTEGER, " +
          REPO_COLUMN_LICENSE_NAME + " TEXT, " +
          REPO_COLUMN_LICENSE_URL + " TEXT, " +
          "PRIMARY KEY ( " +
          REPO_COLUMN_REPO_NAME + ", " + REPO_COLUMN_OWNER_ID + "))");


      for ( OutputRecord record : outputRecords )
      {
        PreparedStatement queryOwner = sqliteDB.prepareStatement(QUERY_OWNER);
        queryOwner.setString(1, record.getOwner());
        ResultSet ownerAlreadyInDB = queryOwner.executeQuery();

        int ownerID;

        if ( ownerAlreadyInDB.next() )
        {
          ownerID = ownerAlreadyInDB.getInt(1);
        }
        else
        {
          PreparedStatement insertOwner = sqliteDB.prepareStatement(INSERT_OWNER);
          insertOwner.setString(1, record.getOwner());
          insertOwner.executeUpdate();
          ownerID = insertOwner.getGeneratedKeys().getInt(1);
        }

        PreparedStatement insertRepoInfo = sqliteDB.prepareStatement(INSERT_REPO);
        insertRepoInfo.setInt(1, ownerID);
        insertRepoInfo.setString(2, record.getRepoName());
        insertRepoInfo.setString(3, record.getLanguage());
        insertRepoInfo.setInt(4, record.getForksNum());
        insertRepoInfo.setInt(5, record.getStarsNum());
        insertRepoInfo.setString(6, record.getLicenseName());
        insertRepoInfo.setString(7, record.getLicenseURL());

        insertRepoInfo.execute();
      }

      sqliteDB.commit();
    }
  }
}
