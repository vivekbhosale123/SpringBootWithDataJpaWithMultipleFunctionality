package com.vdb.SpringBootWithDataJpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordAlreadyExistsException extends RuntimeException {

    public RecordAlreadyExistsException(String msg) {
        super(msg);
    }

}
