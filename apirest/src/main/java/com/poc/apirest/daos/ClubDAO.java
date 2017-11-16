package com.poc.apirest.daos;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.poc.apirest.models.Club;

@Service
public class ClubDAO extends JdbcDaoSupport {

    @Autowired
    public ClubDAO(final DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Club getClubById(final Integer id) {

        this.getJdbcTemplate().execute("DROP TABLE IF EXISTS clubs");
        this.getJdbcTemplate().execute("CREATE TABLE clubs ( id integer NOT NULL, name text )");

        this.getJdbcTemplate().execute("INSERT INTO clubs(id, name) VALUES (1, 'Wellness Training Ravel')");
        this.getJdbcTemplate().execute("INSERT INTO clubs(id, name) VALUES (2, 'Fds')");
        this.getJdbcTemplate().execute("INSERT INTO clubs(id, name) VALUES (3, 'Genae')");

        /**
         * We catch the EmptyResultDataAccessException because the method queryForObject throws that exception if the query result is 0 or more than 1
         */
        try {
            final Club club = this.getJdbcTemplate().queryForObject("SELECT id, name FROM clubs WHERE id = ?", new Object[] {id}, new BeanPropertyRowMapper<>(Club.class));
            return club;
        } catch(final EmptyResultDataAccessException e) {
            return null;
        }
    }

}
