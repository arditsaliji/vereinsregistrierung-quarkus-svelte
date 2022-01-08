package service;

import model.Verein;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class VereinService {

    public int deleteVereinById(int vereinId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM verein WHERE id=?")) {
            pstmt.setInt(1, vereinId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vereinId;
    }

    public int updateVerein(Verein verein) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             PreparedStatement pstmt = connection.prepareStatement("UPDATE verein SET name=?, zweck=?, adresse=?, entstehung=?, kategorie=?, obmann=? WHERE id=?")) {

            pstmt.setString(1, verein.getName());
            pstmt.setString(2, verein.getZweck());
            pstmt.setString(3, verein.getAdresse());
            pstmt.setString(4, verein.getEntstehung());
            pstmt.setString(5, verein.getKategorie());
            pstmt.setString(6, verein.getObmann());
            pstmt.setInt(7, verein.getId());

            pstmt.executeUpdate();
        }
        return verein.getId();
    }

    public int addVerein(Verein verein) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO verein (id, name, kategorie, zweck, entstehung, adresse, obmann) VALUES (?, ?, ?, ?, ?, ?, ?)")){
            pstmt.setInt(1, verein.getId());
            pstmt.setString(2, verein.getName());
            pstmt.setString(3, verein.getKategorie());
            pstmt.setString(4, verein.getZweck());
            pstmt.setString(5, verein.getEntstehung());
            pstmt.setString(6, verein.getAdresse());
            pstmt.setString(7, verein.getObmann());

            pstmt.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return verein.getId();
    }


}
