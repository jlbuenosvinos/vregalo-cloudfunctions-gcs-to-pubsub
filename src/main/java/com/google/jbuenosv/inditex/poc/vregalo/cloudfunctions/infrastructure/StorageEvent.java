package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure;

import java.io.Serializable;

/**
 * Created by jbuenosv@google.com
 */
public class StorageEvent implements Serializable {

    private String bucket;
    private String name;
    private String metageneration;

    public StorageEvent() {
    }

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
    }

    public String getMetageneration() {
        return metageneration;
    }

    public void setMetageneration(String metageneration) {
        this.metageneration = metageneration;
    }

    @Override
    public String toString() {
        return "StorageEvent {" +
                "bucket='" + bucket + '\'' +
                ", name='" + name + '\'' +
                ", metageneration='" + metageneration + '\'' +
                '}';
    }

}
