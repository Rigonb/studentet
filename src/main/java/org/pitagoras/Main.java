package org.pitagoras;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Pagesa;
import org.pitagoras.app.db.entity.Student;
import org.pitagoras.app.repository.PaymentRepository;
import org.pitagoras.app.repository.StudentRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.




// to do create table if not exist
//create pagesa java class at entity
// add a new field at student class pagesat wich is a list of pagesat
// when a student is created it should be createdd also for each month a payment until 30 qeshor


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBConnection dbc = new DBConnection();

        StudentRepository srepo = new StudentRepository(dbc);
        PaymentRepository prepo = new PaymentRepository(dbc);
        System.out.println(srepo.kthejTeGjitheStudentet());
        // Student newStudent = new Student(0L,"Rigon",18,"Bashota","34567","Podujev",'M',"Pitagoras");
        //srepo.createStudent(newStudent); D.SH update
        while (true) {
            System.out.println("Pres 1 if u want to see the list of the students. ");
            System.out.println("Pres 2 if u want to update a Student. ª");
            System.out.println("Pres 4 if u wana see a Student.");
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
                Student studenti = srepo.ktheStudentin(Long.valueOf(id),false);
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
                Student studenti = srepo.ktheStudentin(Long.valueOf(id),false);
                if (studenti == null) {
                    System.out.println("Id was not found");
                } else {
                    srepo.deleteStudentByID(Long.valueOf(id));
                }


            } else if (input.equals("4")) {
                System.out.println("Wich student du u wana see tell me his id?");
                String id = scanner.nextLine();
                Student studenti = srepo.ktheStudentin(Long.valueOf(id),true);
                //System.out.println(studenti);
                System.out.println("If want to see the payment of the student write y otherwise pres enter");
                String answr = scanner.nextLine();
                if (answr.equals("y")) {
                    List<Pagesa> pagesat = prepo.kthejPagesatEStudentit(studenti.getId());
                    //System.out.println("Pagesat :");
                  //  pagesat.stream().forEach((Pagesa p) -> {
                       // System.out.println(p);

                //    });
                    studenti.setPagesa(pagesat);


                }
                System.out.println(studenti);

            }



        }


    }
}
