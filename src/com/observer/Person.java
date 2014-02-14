package com.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 *  �۲���
 *  @author zhqiang
 *  @created 2013-9-27 ����10:13:14
 *  @lastModified       
 *  @history
 */
public class Person implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(String.format("I am watching the %s channel", arg));
    }

}
