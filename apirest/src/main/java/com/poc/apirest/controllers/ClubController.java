package com.poc.apirest.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.apirest.models.Club;
import com.poc.apirest.services.ClubService;

@RestController
@RequestMapping(value = PathController.CLUB_URL)
public class ClubController {

    @Resource
    private ClubService clubService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Club getClub(@PathVariable(value = "id") final Integer id) {
        System.out.println("my id is " + id);
        return this.getClubService().getClubById(id);
    }

    /**
     * @return the clubService
     */
    public ClubService getClubService() {
        return this.clubService;
    }

    /**
     * @param clubService
     *            the clubService to set
     */
    public void setClubService(final ClubService clubService) {
        this.clubService = clubService;
    }

}
