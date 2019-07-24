package com.company;

import com.company.export.OutputRecord;

import javax.json.*;

class JsonParser {
  static OutputRecord parseData(String owner, String repoName, JsonStructure repoData)
  {

    String language = (String) getValue("/language", repoData);
    Integer forksNum = (Integer) getValue("/forks", repoData);
    Integer starsNum = (Integer) getValue("/stargazers_count", repoData);
    String licenseName = (String) getValue("/license/name", repoData);
    String licenseURL = (String) getValue("/license/url", repoData);

    return new OutputRecord(owner, repoName, language, forksNum, starsNum, licenseName, licenseURL);
  }

  private static Object getValue(String pointer, JsonStructure repoData)
  {
    try
    {
      if ( repoData.getValue(pointer).getValueType() == JsonValue.ValueType.STRING )
      {
        return ((JsonString) repoData.getValue(pointer)).getString();
      }
      else if ( repoData.getValue(pointer).getValueType() == JsonValue.ValueType.NUMBER )
      {
        return ( (JsonNumber) (repoData.getValue(pointer)) ).intValue();
      }
      else
      {
        return null;
      }
    } catch (JsonException e)
    {
      return null;
    }
  }
}
