package org.pitagoras.app.Services;

import org.pitagoras.app.db.entity.Pagesa;
import org.pitagoras.app.db.entity.Student;
import org.pitagoras.app.repository.PagesaRepository;
import org.pitagoras.app.repository.StudentRepository;

import java.sql.Date;
import java.util.List;

public class StudentService {

    private final StudentRepository studentRepo;
    private final PagesaRepository pagesaRepo;

    public StudentService(StudentRepository sRepo, PagesaRepository pagesaRepo) {
        this.studentRepo = sRepo;
        this.pagesaRepo = pagesaRepo;
    }

    public void createStudent(Student newStudent) {
        studentRepo.createStudent(newStudent);
        newStudent = studentRepo.findLastStudent();

        for (int i = 0; i <= 10; i++) {
            Pagesa pagesa = new Pagesa(0L, newStudent.getId(), new Date(2024 - 1900, i, 1), new Date(2024 - 1900, i + 1, 1), false, null);
            pagesaRepo.createPagesa(pagesa);
        }
    }

    public void deleteStudentById(Long id) {
        this.pagesaRepo.deletePaymentByStudentId(id);
        this.studentRepo.deleteStudent(id);
    }

    public Student findStudentById(Long id) {
        return this.studentRepo.findStudentById(id, true);

    }

    public List<Student> kthejTeGjithStudentet() {
        return this.studentRepo.kthejTeGjitheStudentet();
    }

    public void updateStudent(Long id, Student student) {
        this.studentRepo.updateStudent(id, student);
    }

    public void kryejPagesenPerMuajinEFUndit(Long studentId) {
        Student student = studentRepo.findStudentById(studentId, true);
        System.out.println(student);
        for (Pagesa p : student.getPagesat()) {
            if (!p.getEshtePaguar()) {
                p.setEshtePaguar(true);
                pagesaRepo.updatePagesa(p.getId(), p);
                System.out.println("Payment made successfully and expires at: " + p.getDataEMbarimit());
                break;
            }
        }
    }
}
