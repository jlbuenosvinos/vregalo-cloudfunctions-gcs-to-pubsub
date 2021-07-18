package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process.VideoInputProcessManager;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process.VideoInputProcessManagerImpl;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoInput;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.ConfigLoader;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.UUIDGenerator;

import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class FromGcsToPubSubCloudFunction implements BackgroundFunction<StorageEvent> {

    public static final Logger logger = Logger.getLogger(FromGcsToPubSubCloudFunction.class.getName());

    @Override
    public void accept(StorageEvent event, Context context) throws Exception {
        try {
            logger.info("Receive event.");
            logger.info("Video storage bucket URL: " + ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("video.input.store.url.env.name")));

            VideoInput newInputVideo = new VideoInput();
            newInputVideo.setBucket(event.getBucket());
            newInputVideo.setName(event.getName());
            newInputVideo.setGeneration(event.getMetageneration());
            newInputVideo.setId(new UUIDGenerator().getUuid());

            logger.info("Video Input : " + newInputVideo.toString());

            VideoInputProcessManager videoInputProcessManager = new VideoInputProcessManagerImpl();
            videoInputProcessManager.processVideo(newInputVideo);

            logger.info("End receive event.");
        }
        catch(Exception e) {
            logger.severe("Unable to process the event [" + event.toString() + "]");
            throw e;
        }

    }

}