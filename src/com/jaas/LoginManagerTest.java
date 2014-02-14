package com.jaas;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class LoginManagerTest {

    public static void main(String[] args) {
        String jaasConfigFilePath = "L:/workspace/Test/src/com/jaas/jaas.config";
        String jaasPolicyFilePath = "L:/workspace//Test/src/com/jaas/jaas.policy";
        System.setProperty("java.security.auth.login.config", jaasConfigFilePath);
        
        try {
            String username = "sa";
            String password = "sa";

            //此处指定了使用配置文件的“Sample"验证模块，对应的实现类为SampleLoginModule
            LoginContext lc = new LoginContext("Sample", new SampleCallbackHandler(username, password));

            //进行登录操作，如果验证失败会抛出异常
            lc.login();            
            
            Subject subject = lc.getSubject();
            
            lc.logout();
            
        }
        catch (LoginException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}