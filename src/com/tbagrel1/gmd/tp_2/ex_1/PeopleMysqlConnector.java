package com.tbagrel1.gmd.tp_2.ex_1;

import com.tbagrel1.gmd.tp_2.PersonalInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeopleMysqlConnector {
    protected Connection connection;

    public PeopleMysqlConnector(Connection connection) {
        this.connection = connection;
    }

    public List<PersonalInformation> getAllInfo() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet cursor = statement.executeQuery(
            "SELECT p.id, p.prenom, p.nom, ip.age, ip.ville, p.telephone, p.email FROM personne p JOIN info_perso ip on p.id = ip.info_id"
        );
        List<PersonalInformation> info = new ArrayList<>();
        while (cursor.next()) {
            info.add(new PersonalInformation(
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7)
            ));
        }
        return info;
    }
}
