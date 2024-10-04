package org.pitagoras.app.repository;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.geom.RectangularShape;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {
    private DBConnection dbConnection;

    public StudentRepository(DBConnection connection) {
        this.dbConnection = connection;
    }

    public void createStudent(Student student) {
        String querry = "insert into Studentet(name,age,last_name,phone,birthplace,gender,course_name)values(?,?,?,?,?,?,?)";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {

            urdher.setString(1, student.getName());
            urdher.setInt(2, student.getAge());
            urdher.setString(3,student.getLastname());
            urdher.setString(4,student.getPhone());
            urdher.setString(5,student.getBirthplace());
            urdher.setString(6, String.valueOf(student.getGender()));
            urdher.setString(7,student.getCourseName());

            urdher.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Nuk mujta me shtu studentin!!");
            e.printStackTrace();

        }

    }

    public Student ktheStudentin(long id) {
        String querry = "Select * from studentet where id = ?";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {

            urdher.setLong(1, id);
            ResultSet respons = urdher.executeQuery();
            if (respons.next()) {
                return new Student(respons.getLong("id"), respons.getString("name"), respons.getInt("age"),respons.getString("last_name"),respons.getString("phone"),respons.getString("birthplace"),respons.getString("gender").charAt(0),respons.getString("course_name"));
            }

        } catch (SQLException e) {
            System.out.println("Nuk mujta me shtu studentin");
            e.printStackTrace();

        }
        return null;
    }

    public List<Student> kthejTeGjitheStudentet() {
        String query = "SELECT * FROM studentet";
        List<Student> studentList = new ArrayList<>();

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(query);
             ResultSet respons = urdher.executeQuery()) {

            while (respons.next()) {
                String genderStr = respons.getString("gender");
                char gender = 0;
                if(genderStr != null){
                    gender = genderStr.charAt(0);
                }
                Student student = new Student(
                        respons.getLong("id"),
                        respons.getString("name"),
                        respons.getInt("age"),
                        respons.getString("last_name"),
                        respons.getString("phone"),
                        respons.getString("birthplace"),
                        gender,
                        respons.getString("course_name")
                );
                studentList.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Nuk mujta me i kthy studentet");
            e.printStackTrace();
        }
        return studentList;
    }

    public void updateStudent(Long id, Student student) {
        String querry = "update Studentet set name = ?,age = ?,last_name = ?,phone = ?,birthplace = ?,gender = ?,course_name = ? where id = ?";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {
//          char gender = 0;
          String genderStr = student.getGender() + "";
//          if (!genderStr.isEmpty()){
//              gender = genderStr.charAt(0);
//          }
//            System.out.println("Gender: " + gender + ":" +genderStr);
            urdher.setString(1, student.getName());
            urdher.setInt(2, student.getAge());
            urdher.setString(3,student.getLastname());
            urdher.setString(4,student.getPhone());
            urdher.setString(5,student.getBirthplace());
            urdher.setString(6, genderStr.charAt(0) + "");
            urdher.setString(7,student.getCourseName());
            urdher.setLong(8, id);

            urdher.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Nuk mujta me update studentin");
            e.printStackTrace();

        }

    }

    public void deleteStudentByID(Long id) {
        String querry = "delete from Studentet  where id = ?";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {


            urdher.setLong(1, id);
            urdher.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Nuk mujta me fshi studentin");
            e.printStackTrace();

        }

    }


}

