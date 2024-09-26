package br.com.julio.ApiTest.service.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
