package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoInput;

/**
 * Created by jbuenosv@google.com
 */
public class NewVideoInputSubmittedCommand implements Command {

    private VideoInput video;

    /**
     * Default constructor
     */
    public NewVideoInputSubmittedCommand() {
    }

    public VideoInput getVideo() {
        return video;
    }

    public void setVideo(VideoInput video) {
        this.video = video;
    }
}
