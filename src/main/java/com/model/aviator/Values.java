package com.model.aviator;

import java.util.List;

public class Values {
    private String label;
    private List value;
    private boolean subDeptFlag;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List getValue() {
        return value;
    }

    public void setValue(List value) {
        this.value = value;
    }

    public boolean isSubDeptFlag() {
        return subDeptFlag;
    }

    public void setSubDeptFlag(boolean subDeptFlag) {
        this.subDeptFlag = subDeptFlag;
    }
}
