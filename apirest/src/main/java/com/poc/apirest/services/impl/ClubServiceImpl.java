package com.poc.apirest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.apirest.daos.ClubDAO;
import com.poc.apirest.models.Club;
import com.poc.apirest.services.ClubService;

@Service(value = "ClubService")
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubDAO clubDAO;

    @Override
    public Club getClubById(final Integer id) {
        return this.clubDAO.getClubById(id);
    }

}
