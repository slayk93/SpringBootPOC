package com.poc.facebookconnect.controllers;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeds")
public class FeedController {

    private final Facebook             facebook;
    private final ConnectionRepository connectionRepository;

    public FeedController(final Facebook facebook, final ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    // Use to specify the request type (get => GetMapping, put => PutMapping, post => PostMapping, ...)
    public String feed(final Model model) {
        if(this.connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        final User userProfile = this.facebook.fetchObject("me", User.class);
        model.addAttribute("facebookProfile", userProfile);
        final PagedList<Post> feed = this.facebook.feedOperations().getFeed();
        model.addAttribute("feeds", feed);

        // This is the template name to be called : src/main/resources/templates/facebook.html
        return "feeds";
    }
}
