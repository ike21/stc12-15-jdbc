package ru.innopolis.stc12.jdbc.realExample.dao.mappers;

import ru.innopolis.stc12.jdbc.realExample.pojo.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {
    private CityMapper() {
    }

    public static PreparedStatement getStateFromCity(PreparedStatement statement, City city) throws SQLException {
        statement.setString(1, city.getName());
        statement.setInt(2, city.getCitizens());
        return statement;
    }

    public static City getCityFromSet(ResultSet resultSet) throws SQLException {
        City city = null;
        if (resultSet.next()) {
            city = new City(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("citizens"));
        }
        return city;
    }

}
