package org.pitagoras;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Pagesa;
import org.pitagoras.app.db.entity.Student;
import org.pitagoras.app.repository.PagesaRepository;
import org.pitagoras.app.repository.StudentRepository;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // to do create table if not exist
        //create pagesa java class at entity
        // add a new field at student class pagesat wich is a list of pagesat
        // when a student is created it should be created also for each month a payment until 30 qeshor
        // when a student is created it should be createdd also for each month a payment until 30 qeshor

        Scanner scanner = new Scanner(System.in);
        DBConnection dbc = new DBConnection();

        StudentRepository studentRepo = new StudentRepository(dbc);
        PagesaRepository pagesaRepo = new PagesaRepository(dbc);

//        studentRepo.createStudent(new Student("Rigon", 17));

//        Student newStudent = new Student(0L,"Hajdar",21,"Hasani","949093","Podujeve",'M',"Pitagoras");
//        studentRepo.createStudent(newStudent);

        System.out.println(studentRepo.kthejTeGjitheStudentet());

        while (true) {

            System.out.println("Press 1 if u want to see the list of students");
            System.out.println("Press 2 if u want to uptade a Students");
            System.out.println("Press 3 if u want to delete a Students");
            System.out.println("Press 4 if you want to see a Student");
            System.out.println("Press 5 to make a payment.");
            System.out.println("Press 6 to add a new Student.");
            System.out.println("Press x if u want to exit");

            String input = scanner.nextLine();
            if(input.equals("x")) {
                break;

            }else if (input.equals(("1"))) {
                List<Student> students = studentRepo.kthejTeGjitheStudentet();
                students.stream().forEach((Student s) -> {
                    System.out.println(s);
                });
            } else if (input.equals("2")) {
                System.out.println("Which student should I update ,tell me the id");
                String id = scanner.nextLine();
                Student studenti = studentRepo.findStudentById(Long.valueOf(id),false);
                if (studenti == null) {
                    System.out.println("Student was not found");
                } else {
                    System.out.println(studenti);
                    System.out.println("Please  tell new name and new age");
                    System.out.println("If u dont want to change plase leave it blank");
                    System.out.println("Name:");
                    String emriIRi = scanner.nextLine();
                    System.out.println("Age:");
                    int moshaERe = 0;
                    String moshaInput = scanner.nextLine();
                    if (!moshaInput.isEmpty()) {
                        moshaERe = Integer.valueOf(moshaInput);
                    }
                    System.out.println("Last name:");
                    String lastNameR = scanner.nextLine();
                    System.out.println("Phone:");
                    String phoneR = scanner.nextLine();
                    System.out.println("Gender:");
                    String genderR = scanner.nextLine();
                    System.out.println("Birthplace:");
                    String birthPlaceR = scanner.nextLine();
                    System.out.println("Course name:");
                    String courseNameR = scanner.nextLine();


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
                    if (lastNameR.isEmpty()){
                        updatedStudent.setLastName(studenti.getLastName());
                    } else {
                        updatedStudent.setLastName(lastNameR);
                    }
                    if (phoneR.isEmpty()){
                        updatedStudent.setPhone(studenti.getPhone());
                    } else {
                        updatedStudent.setPhone(phoneR);
                    }
                    if (genderR.isEmpty()){
                        updatedStudent.setGender(studenti.getGender());
                    } else {
                        updatedStudent.setGender(genderR.charAt(0));

                    }
                    if (birthPlaceR.isEmpty()){
                        updatedStudent.setBirthplace(studenti.getBirthplace());
                    } else {
                        updatedStudent.setBirthplace(birthPlaceR);
                    }
                    if (courseNameR.isEmpty()){
                        updatedStudent.setCourseName(studenti.getCourseName());
                    } else {
                        updatedStudent.setCourseName(courseNameR);
                    }


                    studentRepo.updateStudent(studenti.getId(), updatedStudent);
                }
            }else if(input.equals("3")){
                System.out.println("Which student should I delete ,tell me the id");
                String id = scanner.nextLine();
                Student studenti = studentRepo.findStudentById(Long.valueOf(id),false);
                if(studenti == null){
                    System.out.println("User has not been found");
                }else{
                    studentRepo.deleteStudent(Long.valueOf(id));
                }
            } else if (input.equals("4")) {
                System.out.println("Which student do you want to see,tell me the id?");
                String id = scanner.nextLine();
//                Student studenti = studentRepo.findStudentById(Long.valueOf(id));
//                System.out.println(studenti);
                System.out.println("If you want to see the payment of students writte y,otherwise press enter.");
                String answ = scanner.nextLine();
//                if(answ.equals("y")){
//                    List<Pagesa> pagesat = pagesaRepo.kthejPagesatEStudentit(studenti.getId());
////                    System.out.println("Pagesat :");
////                    pagesat.stream().forEach((Pagesa p) -> {
////                        System.out.println(p);
////                    });
//                    studenti.setPagesat(pagesat);
//
//                }
                Student studenti = studentRepo.findStudentById(Long.valueOf(id),answ.equals("y"));
                System.out.println(studenti);

                //if you want to see the payments of students writte y,otherwise press enter

            }else if(input.equals("5")){
                System.out.println("For which student do you want to pay,tell the id?");
                String id = scanner.nextLine();
                Student student = studentRepo.findStudentById(Long.valueOf(id),true);
                System.out.println(student);
                for(Pagesa p : student.getPagesat()){
                    if(!p.getEshtePaguar()) {
                        p.setEshtePaguar(true);
                        pagesaRepo.updatePagesa(p.getId(), p);
                        System.out.println("Payment made successfully and expires at: "+ p.getDataEMbarimit());
                        break;
                    }
                }
            } else if (input.equals("6")) {
                System.out.println("Jepni te dhenat tuaja");
                System.out.println("Emri");
                String emri1 = scanner.nextLine();
                System.out.println("Age");
                int mosha1 = Integer.valueOf(scanner.nextLine());
                System.out.println("Last Name");
                String mbiemri1 = scanner.nextLine();
                System.out.println("Phone");
                String tel1 = scanner.nextLine();
                System.out.println("Gender");
                String gender1 = scanner.nextLine();
                System.out.println("Birthplace");
                String vendlindja1 = scanner.nextLine();
                System.out.println("Course Name");
                String kursi1 = scanner.nextLine();

                Student newStudent = new Student(0L, emri1, mosha1, mbiemri1, tel1, vendlindja1, gender1.charAt(0), kursi1);
                studentRepo.createStudent(newStudent);

                newStudent = studentRepo.findLastStudent();

                for(int i = 0; i <= 10;i++){
                    Pagesa pagesa = new Pagesa(0L,newStudent.getId(),new java.sql.Date(2024,i+1,1),new Date(2024,i+2,1),false,null);
                    pagesaRepo.createPagesa(pagesa);
                }
            }
        }
    }
}