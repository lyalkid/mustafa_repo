package ru.itis.repository;

import ru.itis.model.PersonalData;

public interface PersonalDataRepository {
    public void save(PersonalData data);
    public boolean updateFirstName(String firstName);
    public boolean updateLastName(String lastName);


    public boolean deleteById(Long id);

}

// home page, profile servlet(personal date), announcement servlet, favorites servlets, Logout servlet

