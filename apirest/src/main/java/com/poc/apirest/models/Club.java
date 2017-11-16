package com.poc.apirest.models;

public class Club {

    private Integer id;
    private String  name;

    public Club() {
    }

    public Club(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
