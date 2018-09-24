package ru.innopolis.stc12.jdbc.realExample.dao;

import ru.innopolis.stc12.jdbc.realExample.connectionManager.ConnectionManager;
import ru.innopolis.stc12.jdbc.realExample.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.jdbc.realExample.dao.mappers.StudentMapper;
import ru.innopolis.stc12.jdbc.realExample.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addStudent(Student student) {
        Connection connection = connectionManager.getConnection();
        CityDaoImpl cityDao = new CityDaoImpl();
        if (student != null || student.getCity() != null) {
            if (cityDao.getCityById(student.getCity().getId()) == null) {
                cityDao.addCity(student.getCity());
            }

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students VALUES (DEFAULT, ?, ?, ?, ?, ?)")
            ) {
                StudentMapper.getStateFromStudent(statement, student);
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
    public Student getStudentById(int id) {
        CityDaoImpl cityDao = new CityDaoImpl();
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from students WHERE id = ?")
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = StudentMapper.getStudentFromSet(resultSet, cityDao.getCityById(resultSet.getInt("city")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        CityDaoImpl cityDao = new CityDaoImpl();
        if (student != null || student.getCity() != null) {
            if (cityDao.getCityById(student.getCity().getId()) == null) {
                cityDao.addCity(student.getCity());
            }

            if (student.getId() != 0) {
                Connection connection = connectionManager.getConnection();
                try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE students SET name=?, family_name=?, age=?, contact=?, city=? WHERE id=?")
                ) {
                    StudentMapper.getStateFromStudent(statement, student);
                    statement.setInt(6, student.getId());
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStudentById(int id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM students WHERE id=?")
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
    public boolean deleteStudentByName(Student student) {
        if (student.getName() != null) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE name = ?")) {
                statement.setString(1, student.getName());
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
