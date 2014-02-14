
package com.proxy;

import java.lang.reflect.Method;

public interface Advicer {

    void beforeMethod(Method method);

    void afterMethod(Method method);    

}




