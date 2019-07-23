package com.company;

import com.company.export.OutputRecord;

import javax.json.JsonException;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;

class JsonParser {
  static OutputRecord parseData(String owner, String repoName, JsonStructure repoData)
  {

    String language = getValue("/language", repoData);
    int forksNum = Integer.parseInt(getValue("/forks", repoData));
    int starsNum = Integer.parseInt(getValue("/stargazers_count", repoData));
    String licenseName = getValue("/license/name", repoData);
    String licenseURL = getValue("/license/url", repoData);

    return new OutputRecord(owner, repoName, language, forksNum, starsNum, licenseName, licenseURL);
  }

  private static String getValue(String pointer, JsonStructure repoData)
  {
    try
    {
      if (repoData.getValue(pointer).getValueType() == JsonValue.ValueType.STRING)
      {
        return ((JsonString) repoData.getValue(pointer)).getString();
      }
      else
      {
        return repoData.getValue(pointer).toString();
      }
    } catch (JsonException e)
    {
      return null;
    }
  }
}
