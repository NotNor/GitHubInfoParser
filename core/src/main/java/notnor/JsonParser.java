package notnor;


import notnor.export.OutputRecord;

import javax.json.*;

public
class JsonParser {
  public static OutputRecord parseData(String owner, String repoName, JsonStructure repoData)
  {

    String language = getStringValue("/language", repoData);
    Integer forksNum = getIntegerValue("/forks", repoData);
    Integer starsNum =  getIntegerValue("/stargazers_count", repoData);
    String licenseName = getStringValue("/license/name", repoData);
    String licenseURL = getStringValue("/license/url", repoData);

    return new OutputRecord(owner, repoName, language, forksNum, starsNum, licenseName, licenseURL);
  }

  private static String getStringValue(String pointer, JsonStructure repoData)
  {
    try
    {
      if ( repoData.getValue(pointer).getValueType() == JsonValue.ValueType.STRING )
      {
        return ( (JsonString) repoData.getValue(pointer) ).getString();
      }
      else if ( repoData.getValue(pointer).getValueType() == JsonValue.ValueType.NULL )
      {

        return null;
      }

      throw new IllegalArgumentException(
              "Invalid datatype.\n" +
              "Requested: INTEGER\n" +
              "Found: " + repoData.getValue(pointer).getValueType()
      );
    }
    catch ( JsonException e )
    {
      return null;
    }
  }

  private static Integer getIntegerValue(String pointer, JsonStructure repoData)
  {
    try
    {
      if ( repoData.getValue(pointer).getValueType() == JsonValue.ValueType.NUMBER )
      {
        return ((JsonNumber) repoData.getValue(pointer)).intValue();
      }
      else if ( repoData.getValue(pointer).getValueType() == JsonValue.ValueType.NULL )
      {
        return null;
      }

      throw new IllegalArgumentException("Invalid datatype.\n" +
          "Requested: INTEGER\n" +
          "Found: " + repoData.getValue(pointer).getValueType());
    }
    catch (JsonException e)
    {
      return null;
    }
  }

}
