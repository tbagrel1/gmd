package com.tbagrel1.gmd.tp_2;

import com.tbagrel1.gmd.utils.CsvWriter;
import com.tbagrel1.gmd.utils.MysqlAssistant;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

public class Tp2Ex1 {
    private static final String url = "jdbc:mysql://neptune.telecomnancy.univ-lorraine.fr:3306/gmd";
    private static final String user = "gmd-read";
    private static final String password = "esial";

    public static void run(Connection connection) {
        try {
            List<People.PersonalInformation> allInfo = People.getAllInfo(connection);
            System.out.println("### Personal Information ###");
            for (People.PersonalInformation info : allInfo) {
                System.out.println(info);
            }

            CsvWriter csvWriter = new CsvWriter(Paths.get("output/tp_2_1.csv"));
            allInfo.stream()
                .map(info -> info.toTuple())
                .forEach(tuple -> {
                    try {
                        csvWriter.write(tuple);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            csvWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MysqlAssistant.withConnection(connection -> run(connection));
    }
}
