package com.observer;

import java.util.Observable;

/**
 * 
 *  �۲����
 *  @author zhqiang
 *  @created 2013-9-27 ����10:12:58
 *  @lastModified       
 *  @history
 */
public class TV extends Observable {
    private String channel ="CCTV1";

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
        super.setChanged();
        super.notifyObservers(channel);     
    }

}
