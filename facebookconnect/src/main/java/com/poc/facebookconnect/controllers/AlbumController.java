package com.poc.facebookconnect.controllers;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final Facebook             facebook;
    private final ConnectionRepository connectionRepository;

    public AlbumController(final Facebook facebook, final ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    // Use to specify the request type (get => GetMapping, put => PutMapping, post => PostMapping, ...)
    public String album(final Model model) {
        if(this.connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        final Album album = this.facebook.mediaOperations().getAlbum("10210909710392616");
        model.addAttribute("album", album);

        // This is the template name to be called : src/main/resources/templates/facebook.html
        return "albums";
    }
}
