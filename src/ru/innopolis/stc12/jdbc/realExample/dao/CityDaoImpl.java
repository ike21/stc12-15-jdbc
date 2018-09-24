package ru.innopolis.stc12.jdbc.realExample.dao;

import ru.innopolis.stc12.jdbc.realExample.connectionManager.ConnectionManager;
import ru.innopolis.stc12.jdbc.realExample.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.jdbc.realExample.dao.mappers.CityMapper;
import ru.innopolis.stc12.jdbc.realExample.pojo.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addCity(City city) {
        if (city != null) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT  INTO cities VALUES(DEFAULT, ?, ?)")
            ) {
                CityMapper.getStateFromCity(statement, city);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public City getCityById(int id) {
        Connection connection = connectionManager.getConnection();
        City city;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from cities WHERE id = ?")
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                city = CityMapper.getCityFromSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }

    @Override
    public boolean updateCity(City city) {
        if (city.getId() != 0) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE cities SET name=?, citizens=? WHERE id=?")
            ) {
                CityMapper.getStateFromCity(statement, city);
                statement.setInt(3, city.getId());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCityById(int id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM cities WHERE id=?")
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCityByName(City city) {
        if (city.getName() != null) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM cities WHERE name = ?")) {
                statement.setString(1, city.getName());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
