package com.tbagrel1.gmd.tp_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class People {
    public static List<PersonalInformation> getAllInfo(Connection connection) throws SQLException {
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

    public static class PersonalInformation {
        protected int id;
        protected String firstname;
        protected String lastname;
        protected String phoneNumber;
        protected String email;
        protected int age;
        protected String city;
        public PersonalInformation(int id, String firstname, String lastname, int age, String city, String phoneNumber, String email) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.city = city;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public String getCity() {
            return city;
        }

        public int getId() {
            return id;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public List<Object> toTuple() {
            List<Object> tuple = new ArrayList<>();
            tuple.add(id);
            tuple.add(firstname);
            tuple.add(lastname);
            tuple.add(age);
            tuple.add(city);
            tuple.add(phoneNumber);
            tuple.add(email);
            return tuple;
        }

        @Override
        public String toString() {
            return String.format(
                "People#%d(firstname=%s, lastname=%s, age=%d, city=%s, phoneNumber=%s, email=%s)",
                id, firstname, lastname, age, city, phoneNumber, email
            );
        }
    }
}
