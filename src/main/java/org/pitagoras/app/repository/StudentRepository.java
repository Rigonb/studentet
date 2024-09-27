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
        String querry = "insert into Studentet(name,age)values(?,?)";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {

            urdher.setString(1, student.getName());
            urdher.setInt(2, student.getAge());
            urdher.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Nuk mujta me shtu studentin");
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
                return new Student(respons.getLong("id"), respons.getString("name"), respons.getInt("age"));
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
                Student student = new Student(
                        respons.getLong("id"),
                        respons.getString("name"),
                        respons.getInt("age")
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
        String querry = "update Studentet set name = ?,age = ? where id = ?";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {

            urdher.setString(1, student.getName());
            urdher.setInt(2, student.getAge());
            urdher.setLong(3, id);
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

