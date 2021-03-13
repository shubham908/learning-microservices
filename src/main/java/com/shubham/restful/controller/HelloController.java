package com.shubham.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

  private MessageSource messageSource;

  @Autowired
  public HelloController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
  public String helloWorld() {
    return "Hello World";
  }

  @RequestMapping(value = "/hello-world-internationalized", method = RequestMethod.GET)
  public String helloWorldInternationalized(
      @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
    return messageSource.getMessage("good.morning.message", null, locale);
  }
}
