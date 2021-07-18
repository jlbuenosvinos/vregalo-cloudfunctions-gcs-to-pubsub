package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.store;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception.FromGcsToPubSubCloudFunctionException;
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

import java.io.InputStream;
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
        try {
            props = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            props.load(inputStream);

            // @todo



        }
        catch(Exception e) {
            logger.severe("Unable to load the configuration file.");
            throw new FromGcsToPubSubCloudFunctionException(e);
        }
    }

    /**
     * Publish a @VideoInput JSON representation
     * @param video Input video
     * @return returns a server-assigned message id (unique within the topic)
     */
    public String publish(VideoInput video) throws InterruptedException {
        String messageId = "-1";
        Publisher publisher = null;
        TopicName topicName = TopicName.ofProjectTopicName("inditex-poc-vregalo","video-input-topic");

        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(video.toJson());
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            messageId = messageIdFuture.get();

            logger.info("The message [" + video.getId() + "] has been published having the id [" + messageId + "] :-)");

        }
        catch (IOException | ExecutionException | InterruptedException e) {
            logger.severe("Unable tp publish the message.");
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
