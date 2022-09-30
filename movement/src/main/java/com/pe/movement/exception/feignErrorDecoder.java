package com.pe.movement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class feignErrorDecoder implements ErrorDecoder {
  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String s, Response response) {
    System.out.println("response = " + response);
    System.out.println("response.body() = " + response.body());
    if (400 == response.status()) {
      return new ResponseStatusException(HttpStatus.valueOf(response.status()), "bad request");
    }
    if (404 == response.status()) {
      return new ResponseStatusException(HttpStatus.valueOf(response.status()), "account not found");
    }

    return defaultErrorDecoder.decode(s, response);
  }
}
