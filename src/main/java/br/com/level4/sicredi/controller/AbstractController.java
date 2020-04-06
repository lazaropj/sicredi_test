package br.com.level4.sicredi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Hashtable;

public abstract class AbstractController {

    protected ResponseEntity<Object> handleException(Exception e) {
        Hashtable obj = new Hashtable();
        obj.put("Message error: ", e.getMessage());
        return new ResponseEntity<>(obj, HttpStatus.CONFLICT);
    }
}
