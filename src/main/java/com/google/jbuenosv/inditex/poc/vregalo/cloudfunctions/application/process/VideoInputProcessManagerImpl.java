package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process;


import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command.NewVideoInputSubmittedCommand;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command.NewVideoInputSubmittedCommandHandler;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoInput;

/**
 * Created by jbuenosv@google.com
 */
public class VideoInputProcessManagerImpl implements VideoInputProcessManager {

    NewVideoInputSubmittedCommandHandler newVideoInputSubmittedCommandHandler;
    NewVideoInputSubmittedCommand videoInputSubmittedCommand;

    /**
     * Default constructor
     */
    public VideoInputProcessManagerImpl() {
        this.newVideoInputSubmittedCommandHandler = new NewVideoInputSubmittedCommandHandler();
        this.videoInputSubmittedCommand = new NewVideoInputSubmittedCommand();
    }

    /**
     * Process the input video
     * @param videoInput input video
     */
    @Override
    public void processVideo(VideoInput videoInput) {
        this.videoInputSubmittedCommand.setVideo(videoInput);
        newVideoInputSubmittedCommandHandler.execute(videoInputSubmittedCommand);
    }

}
