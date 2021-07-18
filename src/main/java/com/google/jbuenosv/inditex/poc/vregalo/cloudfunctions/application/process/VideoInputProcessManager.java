package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoInput;

/**
 * Created by jbuenosv@google.com
 */
public interface VideoInputProcessManager {

    /**
     * Process the input video
     * @param videoInput input video
     */
    void processVideo(VideoInput videoInput);

}
