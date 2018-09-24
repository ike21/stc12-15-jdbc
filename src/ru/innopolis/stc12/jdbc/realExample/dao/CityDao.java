package ru.innopolis.stc12.jdbc.realExample.dao;

import ru.innopolis.stc12.jdbc.realExample.pojo.City;

public interface CityDao {
    boolean addCity(City city);

    City getCityById(int id);

    boolean updateCity(City city);

    boolean deleteCityById(int id);

    boolean deleteCityByName(City city);
}
