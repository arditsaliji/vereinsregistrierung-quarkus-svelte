package dataaccess;

import model.Mitglied;
import model.Verein;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MitgliedDataAccess {

    @Inject
    private VereinDataAccess vereinDataAccess;

    public List<Mitglied> getMitglieder() throws Exception {
        List<Mitglied> mitglieder = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://s76.goserver.host:3306/web122_db9?useSSL=false", "web122_9", "service2021");
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * from mitglied");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vorname = resultSet.getString("vorname");
                String nachname = resultSet.getString("nachname");
                String adresse = resultSet.getString("adresse");
                String geburtsdatum = resultSet.getString("geburtsdatum");
                int vereinId = resultSet.getInt("vereinId");
                Verein verein = vereinDataAccess.getVereinById(vereinId);

                Mitglied mitglied = new Mitglied(id, vorname, nachname, adresse, geburtsdatum, verein);
                mitglieder.add(mitglied);
            }
            return mitglieder;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
