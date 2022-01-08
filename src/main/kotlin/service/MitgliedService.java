package service;

import model.Mitglied;
import model.Verein;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class MitgliedService {

    public int deleteMitgliedById(int mitgliedId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM mitglied WHERE id=?")) {
            pstmt.setInt(1, mitgliedId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mitgliedId;
    }

    public int updateMitglied(Mitglied mitglied) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             PreparedStatement pstmt = connection.prepareStatement("UPDATE mitglied SET vorname=?, nachname=?, adresse=?, geburtsdatum=?, vereinId=? WHERE id=?")) {

            pstmt.setString(1, mitglied.getVorname());
            pstmt.setString(2, mitglied.getNachname());
            pstmt.setString(3, mitglied.getAdresse());
            pstmt.setString(4, mitglied.getGeburtsdatum());
            pstmt.setInt(5, mitglied.getVerein().getId());
            pstmt.setInt(6, mitglied.getId());

            pstmt.executeUpdate();
        }
        return mitglied.getId();
    }

    public int addMitglied(Mitglied mitglied) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO mitglied (id, vorname, nachname, adresse, geburtsdatum, vereinId) VALUES (?, ?, ?, ?, ?, ?)")){
            pstmt.setInt(1, mitglied.getId());
            pstmt.setString(2, mitglied.getVorname());
            pstmt.setString(3, mitglied.getNachname());
            pstmt.setString(4, mitglied.getAdresse());
            pstmt.setString(5, mitglied.getGeburtsdatum());
            pstmt.setInt(6, mitglied.getVerein().getId());

            pstmt.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mitglied.getId();
    }


}
