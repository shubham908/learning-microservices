package com.shubham.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

// @JsonIgnoreProperties(value = {"field2"})
// This is the other way of specifying values to ignore.
// But, this is hard-coded field-name.
// Choose @JsonIgnore more
public class SomeBean {

    private String field1;

    @JsonIgnore
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
