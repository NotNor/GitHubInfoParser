package com.company;

import com.company.export.OutputRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

class JsonParserTest {

   private String owner = "Microsoft";
   private String name = "BosqueLanguage";
   private String language = "TypeScript";
   private int forks = 300;
   private int stargazers_count = 800;
   private String licenseName = "Other";
   private String licenseUrl = null;



   private JsonBuilderFactory factory = Json.createBuilderFactory(null);
   private JsonObject validJsonData = factory.createObjectBuilder()
           .add("language", language)
           .add("forks", forks)
           .add("stargazers_count", stargazers_count)
           .add("license", factory.createObjectBuilder()
               .add("name", licenseName)
               .addNull("url"))
           .build();

   private OutputRecord record = new OutputRecord(
           owner,
           name,
           language,
           forks,
           stargazers_count,
           licenseName,
           licenseUrl);

   @Test
   void jsonParserValidData() {
      Assertions.assertEquals(record, JsonParser.parseData(owner, name, validJsonData));
   }







}


