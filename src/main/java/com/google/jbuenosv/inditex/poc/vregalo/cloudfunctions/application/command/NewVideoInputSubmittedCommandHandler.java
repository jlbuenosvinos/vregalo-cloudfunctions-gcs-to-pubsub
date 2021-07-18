package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception.FromGcsToPubSubCloudFunctionException;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoInput;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.store.PubSubPublisher;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.UUIDGenerator;

import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class NewVideoInputSubmittedCommandHandler implements  CommandHandler {

    public static final Logger logger = Logger.getLogger(NewVideoInputSubmittedCommandHandler.class.getName());

    private UUIDGenerator uuidGenerator;
    private PubSubPublisher publisher;

    /**
     * Default constructor
     */
    public NewVideoInputSubmittedCommandHandler() {
        this.uuidGenerator = new UUIDGenerator();
        this.publisher = new PubSubPublisher();
    }

    /**
     * Executes the command
     * @param command command to be executed
     */
    @Override
    public void execute(Command command) {
        NewVideoInputSubmittedCommand videoInputSubmittedCommand = (NewVideoInputSubmittedCommand)command;
        VideoInput video = videoInputSubmittedCommand.getVideo();

        try {
            logger.info("Ready to process the video [" + video.getId()  + "].");
            String internalId = publisher.publish(video);
            logger.info("Video [" + video.getId()  + "] has been processed [" + internalId + "].");
        }
        catch(Exception e) {
            logger.severe("Unable to process the video [" + video.getId() + "]");
            throw new FromGcsToPubSubCloudFunctionException(e);
        }

    }

}
