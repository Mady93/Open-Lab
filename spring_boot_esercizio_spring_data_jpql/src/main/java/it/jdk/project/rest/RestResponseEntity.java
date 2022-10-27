package it.jdk.project.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RestResponseEntity<T,E,C> {

    public abstract T getData();
    public abstract boolean isOk();
    public abstract E getError();
    public abstract C getErrorCode();

    private RestResponseEntity(){}

   
    public static <T> RestResponseEntity buildData(T data){
            return new RestResponseEntity<T, Object, Object>() {
                @Override
                public T getData() {
                    return data;
                }
                @Override
                public boolean isOk() {
                    return true;
                }
                @Override
                public Object getError() {
                    return null;
                }
                @Override
                public Object getErrorCode() {
                    return null;
                }
            };
    }

    
    
    public static <E,C> RestResponseEntity buildError(E errorMessage, C erroCode){
        return new RestResponseEntity<Object, E, C>() {
            @Override
            public Object getData() {
                return null;
            }
            @Override
            public boolean isOk() {
                return false;
            }
            @Override
            public E getError() {
                return errorMessage;
            }
            @Override
            public C getErrorCode() {
                return erroCode;
            }
        };
    }
}
