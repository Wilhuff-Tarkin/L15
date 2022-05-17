package configuration.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class YamlModel {



    @Getter
    @Setter
    @JsonProperty
    private String testedDataSet;


    @Setter
    @Getter
    private HashMap<String, EnvironmentModel> testData;


    public YamlModel() {
    }

    public YamlModel(String testedDataSet, HashMap<String, EnvironmentModel> testData) {
        this.testedDataSet = testedDataSet;
        this.testData = testData;
    }

    public HashMap<String, EnvironmentModel> getAllTestData() {
        return testData;
    }

    public HashMap<String, Object> getSpecificTestData(String testSetName) {
        return testData.get(testSetName).getTestPropertiesMap();
    }
}
