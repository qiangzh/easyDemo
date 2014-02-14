package com.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    private Properties properties;

    BeanFactory(InputStream inputStream) {
        properties = new Properties();
        try {
            properties.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Object getBean(String name) {
        Object object = null;
        try {
            object = Class.forName(properties.getProperty(name)).newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (object instanceof ProxyFactoryBean) {
            ProxyFactoryBean proxy = (ProxyFactoryBean)object;
            try {
                proxy.setAdvicer((Advicer)Class.forName(properties.getProperty(name+".advicer")).newInstance());
                proxy.setTarget(Class.forName(properties.getProperty(name+".target")).newInstance());
                object = proxy.getProxy();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return object;

    }

}
