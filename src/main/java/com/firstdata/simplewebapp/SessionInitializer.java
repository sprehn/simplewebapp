package com.firstdata.simplewebapp;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Initialize spring session module.
 */
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {

    public SessionInitializer() {
        super(Config.class);
    }  
    
}
