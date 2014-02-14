package com.jaas;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * 
 *  ｛该处说明该类型的含义及作用｝
 *  @author zhqiang
 *  @created 2013-9-24 下午01:17:35
 *  @lastModified       
 *  @history
 */
public class SampleCallbackHandler implements CallbackHandler {
    private String username;

    private String password;

    public SampleCallbackHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback ncb = (NameCallback) callbacks[i];
                ncb.setName(this.username);
            }
            else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pcb = (PasswordCallback) callbacks[i];
                pcb.setPassword(this.password.toCharArray());
            }
        }
    }
}