package org.pitagoras.app.repository;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Pagesa;
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
    public Student ktheStudentin(long id, boolean includePayments) {
        String query = "SELECT s.id AS studentId, s.name AS name, s.age AS age, " +
                "s.last_name AS last_name, s.phone AS phone, " +
                "s.birthplace AS birthplace, s.gender AS gender, " +
                "s.course_name AS course_name ";

        if (includePayments) {
            query += ", p.id AS paymentId, p.studentId AS paymentStudentId, " +
                    "p.dataEFillimit AS paymentStartDate, p.dataEMbarimit AS paymentEndDate, " +
                    "p.eshtePaguar AS isPaid, p.paguarMe AS paidOn " +
                    "FROM studentet s " +
                    "LEFT JOIN pagesat p ON s.id = p.studentId ";
        } else {
            query += "FROM studentet s ";
        }

        query += "WHERE s.id = ?";

        try (Connection connection = this.dbConnection.getConnation();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String genderRespStr = resultSet.getString("gender");
                Character genderResponse = (genderRespStr != null) ? genderRespStr.charAt(0) : null;

                Student student = new Student(
                        resultSet.getLong("studentId"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("birthplace"),
                        genderResponse,
                        resultSet.getString("course_name")
                );

                if (includePayments) {
                    List<Pagesa> payments = new ArrayList<>();
                    do {
                        Long paymentId = resultSet.getLong("paymentId");
                        if (paymentId != 0) { // Assuming that 0 indicates no payment record
                            Pagesa payment = new Pagesa(
                                    paymentId,
                                    resultSet.getLong("paymentStudentId"),
                                    resultSet.getDate("paymentStartDate"),
                                    resultSet.getDate("paymentEndDate"),
                                    resultSet.getBoolean("isPaid"),
                                    resultSet.getTimestamp("paidOn")
                            );
                            payments.add(payment);
                        }
                    } while (resultSet.next());

                    student.setPagesa(payments);
                }

                return student;
            }

        } catch (SQLException e) {
            System.out.println("Nuk mujta me shtu studentin");
            e.printStackTrace();
        }

        return null; // Return null if no student is found or an error occurs
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
        System.out.println(student + " :" + student.getGender());
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

