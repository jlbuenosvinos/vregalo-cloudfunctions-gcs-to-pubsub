package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.model.StorageEvent;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.utils.ConfigLoader;

import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class FromGcsToPubSubCloudFunction implements BackgroundFunction<StorageEvent> {

    public static final Logger logger = Logger.getLogger(FromGcsToPubSubCloudFunction.class.getName());

    @Override
    public void accept(StorageEvent event, Context context) throws Exception {
        try {
            logger.info("Receive event on " + event.toString());
            logger.info("Video storage bucket URL: " + ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("video.input.store.url.env.name")));



            logger.info("End receive event on " + event.getName());
        }
        catch(Exception e) {
            logger.severe("There is an exception: " + e.getMessage());
            throw e;
        }

    }

}