package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.store;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoInput;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.ConfigLoader;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class PubSubPublisher {

    public static final Logger logger = Logger.getLogger(PubSubPublisher.class.getName());

    private Properties props;

    /**
     * Private constructor
     */
    public PubSubPublisher() {
    }

    /**
     * Publish a @VideoInput JSON representation
     * @param video Input video
     * @return returns a server-assigned message id (unique within the topic)
     */
    public String publish(VideoInput video) throws InterruptedException, IOException, ExecutionException {
        String messageId = "-1";
        Publisher publisher = null;

        String projectName = ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("gcp.project.env.name"));
        String topìcName = ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("video.input.topic.env.name"));

        TopicName topicName = TopicName.of(projectName,topìcName);

        try {
            logger.info("Ready to build a Topic [" + topicName.toString() + "]");
            publisher = Publisher.newBuilder(topicName).build();
            ByteString data = ByteString.copyFromUtf8(video.toJson());
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            messageId = messageIdFuture.get();
            logger.info("The message [" + video.getId() + "] has been published having the id [" + messageId + "] :-)");
        }
        finally {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
        return messageId;

    }

}
