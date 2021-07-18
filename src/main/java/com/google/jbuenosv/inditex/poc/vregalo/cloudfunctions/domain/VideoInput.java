package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.ConfigLoader;

import java.io.Serializable;

/**
 * Created by jbuenosv@google.com
 */
public class VideoInput  implements Serializable {

    private String id;
    private String name;
    private String bucket;
    private String generation;
    private String videoURL;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.videoURL = ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("video.input.store.url.env.name")) + "/" + name ;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VideoInput {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bucket='" + bucket + '\'' +
                ", generation='" + generation + '\'' +
                ", videoURL='" + videoURL + '\'' +
                '}';
    }

    /**
     * Gets the JSON representation
     * @return Video Input JSON representation
     */
    public String toJson() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        result.append("{" + NEW_LINE);
        result.append("    \"id\": " + getId() + "," + NEW_LINE);
        result.append("    \"name\": " + getName() + "," + NEW_LINE);
        result.append("    \"bucket\": \"" + getBucket() + "\","  + NEW_LINE);
        result.append("    \"generation\": \"" + getGeneration() + "\","  + NEW_LINE);
        result.append("    \"video_url\": \"" + getVideoURL() + "\","  + NEW_LINE);
        result.append("}");
        return result.toString();
    }

}
