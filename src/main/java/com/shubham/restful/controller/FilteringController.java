package com.shubham.restful.controller;

import com.shubham.restful.model.SomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(value = "/filtering")
    public SomeBean getSomeBean() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping(value = "/filtering-list")
    public List<SomeBean> getSomeBeanList() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("Nvalue1", "Nvalue2", "Nvalue3"));
    }
}
