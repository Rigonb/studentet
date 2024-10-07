package org.pitagoras;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Student;
import org.pitagoras.app.repository.StudentRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBConnection dbc = new DBConnection();

        StudentRepository srepo = new StudentRepository(dbc);
        System.out.println(srepo.kthejTeGjitheStudentet());
        // Student newStudent = new Student(0L,"Rigon",18,"Bashota","34567","Podujev",'M',"Pitagoras");
        //srepo.createStudent(newStudent); D.SH update
        while (true) {
            System.out.println("Pres 1 if u want to see the list of the students. ");
            System.out.println("Pres 2 if u want to update a Student. ª");
            System.out.println("Pres 3 if u want to delete a Student.");
            System.out.println("Pres x if u want to exit.");
            String input = scanner.nextLine();
            if (input.equals("x")) {
                break;

            } else if (input.equals("1")) {
                List<Student> students = srepo.kthejTeGjitheStudentet();
                students.stream().forEach((Student s) -> {
                    System.out.println(s);
                });


                System.out.println("Wich student should i update please tell me his id");
                String id = scanner.nextLine();
                Student studenti = srepo.ktheStudentin(Long.valueOf(id));
                if (studenti == null) {
                    System.out.println("Student was not found");

                } else {
                    System.out.println(studenti);
                    System.out.println("Please type the following fields");
                    System.out.println("If u dont want to change please leave if blank");
                    System.out.print("Name: ");
                    String emriIRi = scanner.nextLine();
                    System.out.println("Age: ");
                    int moshaERe = 0;
                    String moshaInput = scanner.nextLine();
                    if (!moshaInput.isEmpty()) {
                        moshaERe = Integer.valueOf(moshaInput);
                    }
                    System.out.println("LastName: ");
                    String newLastName = scanner.nextLine();
                    System.out.println("Phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.println("Birthplace: ");
                    String newBirthPlace = scanner.nextLine();
                    System.out.println("Gender: ");
                    String newGender = scanner.nextLine();
                    System.out.println("CourseName: ");
                    String newCourseName = scanner.nextLine();


                    Student updatedStudent = new Student();
                    if (emriIRi.isEmpty()) {
                        updatedStudent.setName(studenti.getName());
                    } else {
                        updatedStudent.setName(emriIRi);
                    }
                    if (moshaERe == 0) {
                        updatedStudent.setAge(studenti.getAge());
                    } else {
                        updatedStudent.setAge(moshaERe);
                    }
                    if (newLastName.isEmpty()) {
                        updatedStudent.setLastname(studenti.getLastname());
                    } else {
                        updatedStudent.setLastname(newLastName);
                    }
                    if (newPhone.isEmpty()) {
                        updatedStudent.setPhone(studenti.getPhone());
                    } else {
                        updatedStudent.setPhone(newPhone);
                    }
                    if (newBirthPlace.isEmpty()) {
                        updatedStudent.setBirthplace(studenti.getBirthplace());
                    } else {
                        updatedStudent.setBirthplace(newBirthPlace);
                    }
                    if (newGender.isEmpty()) {
                        updatedStudent.setGender(studenti.getGender());
                    } else {
                        updatedStudent.setGender(newGender.charAt(0));
                    }
                    if (newCourseName.isEmpty()) {
                        updatedStudent.setCourse_name(studenti.getCourseName());
                    } else {
                        updatedStudent.setCourse_name(newCourseName);
                    }

                    srepo.updateStudent(studenti.getId(), updatedStudent);
                }


            } else if (input.equals("3")) {
                System.out.println("Wich id should i delete");
                String id = scanner.nextLine();
                Student studenti = srepo.ktheStudentin(Long.valueOf(id));
                if (studenti == null) {
                    System.out.println("Id was not found");
                } else {
                    srepo.deleteStudentByID(Long.valueOf(id));
                }


            }
        }


    }
}
