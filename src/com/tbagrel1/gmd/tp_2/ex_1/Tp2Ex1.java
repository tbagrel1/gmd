package com.tbagrel1.gmd.tp_2.ex_1;

import com.tbagrel1.gmd.tp_2.PersonalInformation;
import com.tbagrel1.gmd.utils.CsvWriter;
import com.tbagrel1.gmd.utils.MysqlAssistant;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

public class Tp2Ex1 {
    public static void convertMysqlCsv(Connection connection, boolean show) {
        try {
            PeopleMysqlConnector mysqlConnector = new PeopleMysqlConnector(connection);
            List<PersonalInformation> allInfo = mysqlConnector.getAllInfo();

            if (show) {
                System.out.println("### Personal Information ###");
                for (PersonalInformation info : allInfo) {
                    System.out.println(info);
                }
            }
            CsvWriter csvWriter = new CsvWriter(Paths.get("output/mysql.csv"));
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
        MysqlAssistant.withConnection(connection -> convertMysqlCsv(connection, false));
    }
}
