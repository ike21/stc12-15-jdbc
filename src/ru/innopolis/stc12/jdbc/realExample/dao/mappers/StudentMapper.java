package ru.innopolis.stc12.jdbc.realExample.dao.mappers;

import ru.innopolis.stc12.jdbc.realExample.pojo.City;
import ru.innopolis.stc12.jdbc.realExample.pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {
    private StudentMapper() {
    }

    public static PreparedStatement getStateFromStudent(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.getName());
        statement.setString(2, student.getFamilyName());
        statement.setInt(3, student.getAge());
        statement.setString(4, student.getContact());
        statement.setInt(5, student.getCity().getId());
        return statement;
    }

    public static Student getStudentFromSet(ResultSet resultSet, City city) throws SQLException {
        Student student = new Student(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("family_name"),
                resultSet.getInt("age"),
                resultSet.getString("contact"),
                city);
        return student;
    }

}
