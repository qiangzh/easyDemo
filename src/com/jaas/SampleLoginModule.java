package com.jaas;

import java.io.IOException;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class SampleLoginModule implements LoginModule {
    private Subject subject;

    private CallbackHandler callbackHandler;

    private boolean isAuthenticated = false;

    private SamplePrincipal principal;

    public SampleLoginModule() {
    }

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    public boolean login() throws LoginException {
        try {
            //1������û�����
            NameCallback nameCallback = new NameCallback("username");
            PasswordCallback passwordCallback = new PasswordCallback("password", false);
            Callback[] calls = new Callback[] { nameCallback, passwordCallback };
            this.callbackHandler.handle(calls);
            String username = nameCallback.getName();
            String password = String.valueOf(passwordCallback.getPassword());

            //2����֤���磺��ѯ���ݿ⡢LDAP������
            if ("sa".equals(username) && "sa".equals(password)) {
                this.principal = new SamplePrincipal(username);
                this.isAuthenticated = true;
            }
            else {
                throw new LoginException("user or password is wrong");
            }
        }
        catch (IOException e) {
            throw new LoginException("no such user");
        }
        catch (UnsupportedCallbackException e) {
            throw new LoginException("login failure");
        }
        return this.isAuthenticated;
    }

    /**
     * ��֤������Subject�м����û�����
     * @return
     * @throws javax.security.auth.login.LoginException
     */
    public boolean commit() throws LoginException {
        if (this.isAuthenticated) {
            this.subject.getPrincipals().add(this.principal);
        }
        else {
            throw new LoginException("Authentication failure");
        }

        return this.isAuthenticated;
    }

    public boolean abort() throws LoginException {
        return false;
    }

    public boolean logout() throws LoginException {
        this.subject.getPrincipals().remove(this.principal);
        this.principal = null;
        return true;
    }
}