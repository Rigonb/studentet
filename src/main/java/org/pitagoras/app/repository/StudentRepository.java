package org.pitagoras.app.repository;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Pagesa;
import org.pitagoras.app.db.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final DBConnection dbConnection;

    public StudentRepository(DBConnection connection) {
        this.dbConnection = connection;
    }

    public void createStudent(Student student) {
        String query = "insert into studentet(name,age,last_name,phone,birthplace,gender,course_name) values(?,?,?,?,?,?,?)";

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)

        ) {
            urdheri.setString(1, student.getName());
            urdheri.setInt(2, student.getAge());
            urdheri.setString(3, student.getLastName());
            urdheri.setString(4, student.getPhone());
            urdheri.setString(5, student.getBirthplace());
            urdheri.setString(6, String.valueOf(student.getGender()));
            urdheri.setString(7, student.getCourseName());
            urdheri.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nuk mujta me shtu studentin");
            e.printStackTrace();
        }
    }

    public Student findStudentById(Long id, boolean includePayments) {
        String query = "select s.id as studentId, s.name as name, s.age as age, s.last_name as lastName, s.phone as phone, s.birthplace as birthplace, s.gender as gender, s.course_name as courseName ";

        if (includePayments) {
            query += ", p.id as pagesaId, p.dataEFillimit as dataEFillimit, p.dataEMbarimit as dataEMbarimit, p.eshtePaguar as eshtePaguar, p.paguarMe as paguarMe ";
            query += "from studentet s left join pagesat p on s.id = p.studentId ";
        } else {
            query += "from studentet s ";
        }
        query += "where s.id = ? ";

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)) {

            urdheri.setLong(1, id);
            ResultSet response = urdheri.executeQuery();

            Student student = null;
            List<Pagesa> pagesat = new ArrayList<>();

            while (response.next()) {
                if (student == null) {
                    String genderRespStr = response.getString("gender");
                    Character genderResponse = null;
                    if (genderRespStr != null) {
                        genderResponse = genderRespStr.charAt(0);
                    }

                    student = new Student(response.getLong("studentId"), response.getString("name"), response.getInt("age"), response.getString("lastName"), response.getString("phone"), response.getString("birthplace"), genderResponse, response.getString("courseName"));
                }

                if (includePayments) {
                    Long paymentId = response.getLong("pagesaId");
                    if (paymentId != 0) {
                        Pagesa pagesa = new Pagesa(paymentId, response.getLong("studentId"), response.getDate("dataEFillimit"), response.getDate("dataEMbarimit"), response.getBoolean("eshtePaguar"), response.getTimestamp("paguarMe"));
                        pagesat.add(pagesa);
                    }
                }
            }

            if (student != null && includePayments) {
                student.setPagesat(pagesat);
            }

            return student;

        } catch (SQLException e) {
            System.out.println("Nuk mujta me gjet studentin.");
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> kthejTeGjitheStudentet() {
        String query = "SELECT * FROM studentet";
        List<Student> studentList = new ArrayList<>();

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdher = lidhja.prepareStatement(query); ResultSet respons = urdher.executeQuery()) {

            while (respons.next()) {
                String genderStr = respons.getString("gender");
                char gender = 0;
                if (genderStr != null) {
                    gender = genderStr.charAt(0);
                }
                Student student = new Student(respons.getLong("id"), respons.getString("name"), respons.getInt("age"), respons.getString("last_name"), respons.getString("phone"), respons.getString("birthplace"), gender, respons.getString("course_name")

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
        String query = "Update Studentet set name = ? , age = ?,last_name = ?,phone = ?,birthplace = ?,gender = ?,course_name = ? where id = ?";

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)

        ) {
            String genderStr = student.getGender() + "";
            urdheri.setString(1, student.getName());
            urdheri.setInt(2, student.getAge());
            urdheri.setString(3, student.getLastName());
            urdheri.setString(4, student.getPhone());
            urdheri.setString(5, student.getBirthplace());
            urdheri.setString(6, genderStr.charAt(0) + "");
            urdheri.setString(7, student.getCourseName());
            urdheri.setLong(8, id);
            urdheri.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nuk mujta me ndryshu studentin");
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id) {
        String query = "Delete from Studentet where id = ?";
        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)) {

            urdheri.setLong(1, id);
            urdheri.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nuk mujta me fshi studentin");
            e.printStackTrace();
        }
    }

    public Student findLastStudent() {
        String query = "select s.id as studentId, s.name as name, s.age as age, s.last_name as lastName, s.phone as phone, s.birthplace as birthplace, s.gender as gender, s.course_name as courseName from studentet s order by id desc limit 1";

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)) {
            ResultSet response = urdheri.executeQuery();
            Student student = null;

            if (response.next()) {
                String genderRespStr = response.getString("gender");
                Character genderResponse = null;
                if (genderRespStr != null) {
                    genderResponse = genderRespStr.charAt(0);
                }

                student = new Student(response.getLong("studentId"), response.getString("name"), response.getInt("age"), response.getString("lastName"), response.getString("phone"), response.getString("birthplace"), genderResponse, response.getString("courseName"));
            }

            return student;

        } catch (SQLException e) {
            System.out.println("Nuk mujta me gjet studentin.");
            e.printStackTrace();
        }
        return null;
    }
}

