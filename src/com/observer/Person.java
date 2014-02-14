package com.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 *  π€≤Ï’ﬂ
 *  @author zhqiang
 *  @created 2013-9-27 …œŒÁ10:13:14
 *  @lastModified       
 *  @history
 */
public class Person implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(String.format("I am watching the %s channel", arg));
    }

}
