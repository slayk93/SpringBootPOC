package com.poc.facebookconnect.controllers;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;

@Controller
public class CustomConnectController extends ConnectController {

    @Inject
    public CustomConnectController(final ConnectionFactoryLocator connectionFactoryLocator, final ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    // @Override
    // protected String connectedView(final String providerId) {
    // return "redirect:/feeds";
    // }

}