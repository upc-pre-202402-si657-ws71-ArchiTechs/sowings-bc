package com.chaquitaclla.microservice.products.sowings.domain.exceptions;

public class SowingNotFoundException extends RuntimeException{
    public SowingNotFoundException(Long aLong) {
        super("Sowing with id " + aLong + " not found");
    }
}
