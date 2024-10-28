package org.pitagoras;

import org.pitagoras.app.Services.StudentService;
import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.repository.PagesaRepository;
import org.pitagoras.app.repository.StudentRepository;
import org.pitagoras.app.ui.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DBConnection dbc = new DBConnection();

        StudentRepository studentRepo = new StudentRepository(dbc);
        PagesaRepository pagesaRepo = new PagesaRepository(dbc);
        StudentService studentService = new StudentService(studentRepo, pagesaRepo);

        UserInterface ui = new UserInterface(studentService, scanner);
        ui.start();
    }
}