package com.dijing.server.web.utils.email;

import javax.mail.*;

public class Auth extends Authenticator{

	String userName=null;   
    String password=null;   
        
    public Auth(){   
    }   
    public Auth(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }
}
