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

            //�˴�ָ����ʹ�������ļ��ġ�Sample"��֤ģ�飬��Ӧ��ʵ����ΪSampleLoginModule
            LoginContext lc = new LoginContext("Sample", new SampleCallbackHandler(username, password));

            //���е�¼�����������֤ʧ�ܻ��׳��쳣
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