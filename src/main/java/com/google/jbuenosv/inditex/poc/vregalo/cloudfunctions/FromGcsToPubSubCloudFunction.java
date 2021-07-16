package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions;

import javax.enterprise.context.ApplicationScoped;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 * Created by jbuenosv@google.com
 */
@ApplicationScoped
public class FromGcsToPubSubCloudFunction implements BackgroundFunction<StorageEvent> {

    public static final Logger logger = LoggerFactory.getLogger(FromGcsToPubSubCloudFunction.class);

    @Counted(name = "countAccept", description = "Counts how many times the processOrder method has been invoked")
    @Timed(name = "timeAccept", description = "Times how long it takes to invoke the accept method", unit = MetricUnits.MILLISECONDS)
    @Override
    public void accept(StorageEvent event, Context context) throws Exception {
        logger.debug("Receive event on ",event.toString());



        logger.debug("End receive event on ",event.getName());
    }

}