package ru.innopolis.stc12.jdbc.realExample;

import ru.innopolis.stc12.jdbc.realExample.dao.CityDao;
import ru.innopolis.stc12.jdbc.realExample.dao.CityDaoImpl;
import ru.innopolis.stc12.jdbc.realExample.dao.StudentDao;
import ru.innopolis.stc12.jdbc.realExample.dao.StudentDaoImpl;
import ru.innopolis.stc12.jdbc.realExample.pojo.City;
import ru.innopolis.stc12.jdbc.realExample.pojo.Student;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
        CityDao cityDao = new CityDaoImpl();
        Student student = studentDao.getStudentById(6);
//        City city = cityDao.getCityById(3);
        System.out.println(student);
//        System.out.println(city);
//
//        student.setName("Mikhail");
//        student.setFamilyName("Nekhoroshev");
        student.setCity(null);
        studentDao.updateStudent(student);

//        studentDao.deleteStudentById(1);

//        CityDao cityDao = new CityDaoImpl();
//        City city = cityDao.getCityById(4);
//        System.out.println(city);
//        city.setName("Pery");
//        city.setCitizens(2000);
//        cityDao.deleteCityByName(city);
    }
}
