package org.pitagoras.app.repository;

import org.pitagoras.app.db.DBConnection;
import org.pitagoras.app.db.entity.Pagesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagesaRepository {
    private final DBConnection dbConnection;

    public PagesaRepository(DBConnection connection) {
        this.dbConnection = connection;
    }

    public void createPagesa(Pagesa pagesa) {
        String query = "insert into pagesat" + "(studentId, dataEFillimit, dataEMbarimit, eshtePaguar, paguarMe )" + " values(?,?,?,?,?)";

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)

        ) {
            urdheri.setLong(1, pagesa.getStudentId());
            urdheri.setDate(2, pagesa.getDataEFillimit());
            urdheri.setDate(3, pagesa.getDataEMbarimit());
            urdheri.setBoolean(4, pagesa.getEshtePaguar());
            urdheri.setTimestamp(5, pagesa.getPaguarMe());
            urdheri.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nuk mujta me e regjistru nje pagese.");
            e.printStackTrace();
        }

    }

    public List<Pagesa> kthejPagesatEStudentit(Long studentId) {
        String query = "SELECT * FROM pagesat where studentId = ?";
        List<Pagesa> pagesaList = new ArrayList<>();

        try (Connection lidhja = this.dbConnection.getConnection()) {
            PreparedStatement urdher = lidhja.prepareStatement(query);
            urdher.setLong(1, studentId);
            ResultSet respons = urdher.executeQuery();

            while (respons.next()) {

                Pagesa pagesa = new Pagesa(respons.getLong("id"), respons.getLong("studentId"), respons.getDate("dataEFillimit"), respons.getDate("dataEMbarimit"), respons.getBoolean("eshtePaguar"), respons.getTimestamp("paguarMe")

                );
                pagesaList.add(pagesa);
            }
        } catch (SQLException e) {
            System.out.println("Nuk mujta me i kthy studentet");
            e.printStackTrace();
        }
        return pagesaList;
    }

    public void updatePagesa(Long id, Pagesa pagesa) {
        String query = "Update Pagesat set studentId =?, dataEFillimit =?, dataEMbarimit=?, eshtePaguar=?, paguarMe=?  where id = ?";


        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)

        ) {
            System.out.println(pagesa);
            urdheri.setLong(1, pagesa.getStudentId());
            urdheri.setDate(2, pagesa.getDataEFillimit());
            urdheri.setDate(3, pagesa.getDataEMbarimit());
            urdheri.setBoolean(4, pagesa.getEshtePaguar());
            urdheri.setTimestamp(5, pagesa.getPaguarMe());
            urdheri.setLong(6, id);

            urdheri.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Nuk mujta me ba update pagesen.");
            e.printStackTrace();
        }
    }

    public void deletePaymentByStudentId(Long studentId) {
        String query = "Delete from Pagesat where studentId  = ?";

        try (Connection lidhja = this.dbConnection.getConnection(); PreparedStatement urdheri = lidhja.prepareStatement(query)

        ) {

            urdheri.setLong(1, studentId);
            urdheri.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nuk mujta me fshi pagesen");
            e.printStackTrace();
        }
    }
}