package com.model.aviator;

import java.util.List;

public class RuleModelZc {
    private String propertyName;
    //前括号
    private String frontBrackets;
    //后括号
    private String postBrackets;
    private String conditionOperation;
    private String operation;
    private String type;
    private List<Values> values;
    private boolean noValues;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getFrontBrackets() {
        return frontBrackets;
    }

    public void setFrontBrackets(String frontBrackets) {
        this.frontBrackets = frontBrackets;
    }

    public String getPostBrackets() {
        return postBrackets;
    }

    public void setPostBrackets(String postBrackets) {
        this.postBrackets = postBrackets;
    }

    public String getConditionOperation() {
        return conditionOperation;
    }

    public void setConditionOperation(String conditionOperation) {
        this.conditionOperation = conditionOperation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Values> getValues() {
        return values;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }

    public boolean isNoValues() {
        return noValues;
    }

    public void setNoValues(boolean noValues) {
        this.noValues = noValues;
    }
}
