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

public class PaymentRepository {
    private DBConnection dbConnection;

    public PaymentRepository(DBConnection connection) {
        this.dbConnection = connection;
    }

    public void creastePagesa(Pagesa pagesa) {
        String querry = "insert into pagesat" +
                "(studentId, dataEFillmit, dataEMbarimit, eshtePaguar,paguarMe)" +
                "values(?,?,?,?,?)";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {

            urdher.setLong(1, pagesa.getStudentId());
            urdher.setDate(2, pagesa.getDataEFillimit());
            urdher.setDate(3, pagesa.getDataEMbarimit());
            urdher.setBoolean(4, pagesa.getEshtePaguar());
            urdher.setTimestamp(5, pagesa.getPaguarMe());

            urdher.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Nuk mujta me regjistru pagesen");
            e.printStackTrace();

        }
    }

    public List<Pagesa> kthejPagesatEStudentit(Long studentId) {
        String query = "SELECT * FROM pagesat where studentId = ?";
        List<Pagesa> pagesaList = new ArrayList<>();

        try (Connection lidhja = this.dbConnection.getConnation()){
            PreparedStatement urdher = lidhja.prepareStatement(query);
             urdher.setLong(1,studentId);
             ResultSet respons = urdher.executeQuery();


            while (respons.next()) {

                Pagesa pagesa = new Pagesa(
                        respons.getLong("id"),
                        respons.getLong("studentId"),
                        respons.getDate("dataEFIllimit"),
                        respons.getDate("dataEMbarimit"),
                        respons.getBoolean("eshtePaguar"),
                        respons.getTimestamp("paguarMe")
                );
                pagesaList.add(pagesa);
            }

        } catch (SQLException e) {
            System.out.println("Nuk mujta me i kthy studentet");
            e.printStackTrace();
        }
        return pagesaList;
    }
    public void updatePagesa(Long id,Pagesa pagesa) {

        String querry = "update Pagesat set studentId =  ?, dataEFillmit = ?, dataEMbarimit = ?, eshtePaguar = ?,paguarMe = ?";

        try (Connection lidhja = this.dbConnection.getConnation();
             PreparedStatement urdher = lidhja.prepareStatement(querry)
        ) {
            urdher.setLong(1, pagesa.getStudentId());
            urdher.setDate(2, pagesa.getDataEFillimit());
            urdher.setDate(3, pagesa.getDataEMbarimit());
            urdher.setBoolean(4, pagesa.getEshtePaguar());
            urdher.setTimestamp(5, pagesa.getPaguarMe());

            urdher.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Nuk mujta me update pagesen");
            e.printStackTrace();

        }

    }


}


