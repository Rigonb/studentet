package org.pitagoras.app.db.entity;


import java.util.List;

public class Student {
    private long id;
    private String name;
    private int age;
    private String lastname;
    private String phone;
    private String birthplace;
    private Character  gender;
    private String courseName;
    private List<Pagesa> pagesa;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", gender=" + gender +
                ", courseName='" + courseName + '\'' +
                ", pagesa=" + pagesa +
                '}';
    }

    public List<Pagesa> getPagesa() {
        return pagesa;
    }

    public void setPagesa(List<Pagesa> pagesa) {
        this.pagesa = pagesa;
    }

    public Student(long id, String name, int age){
    this.id=id;
    this.name = name;
    this.age = age;


    }
    public Student(String name,int age){

        this.name = name;
        this.age = age;


    }

    public Student(long id, String name, int age, String lastname, String phone, String birthplace, Character gender, String courseName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lastname = lastname;
        this.phone = phone;
        this.birthplace = birthplace;
        this.gender = gender;
        this.courseName = courseName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourse_name(String course_name) {
        this.courseName = course_name;
    }

    public Student(){

        this.id = 0;
        this.age = 0;
        this.name = "";


    }





    public long getId(){
        return this.id;
    }


    public String getName(){
        return this.name;
    }


    public int getAge(){
        return this.age;
    }


    public void setId(long id){
        this.id = id;
    }


    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age = age;
    }


}
