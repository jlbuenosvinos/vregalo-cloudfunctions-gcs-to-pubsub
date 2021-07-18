package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception;

/**
 * Created by jbuenosv@google.com
 */
public class FromGcsToPubSubCloudFunctionException extends RuntimeException {

    public FromGcsToPubSubCloudFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FromGcsToPubSubCloudFunctionException(String message) {
        super(message);
    }

    public FromGcsToPubSubCloudFunctionException(Throwable cause) {
        super(cause);
    }

}