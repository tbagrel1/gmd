package com.tbagrel1.gmd.jdbc.ex_1;

import com.tbagrel1.gmd.jdbc.PersonalInformation;
import com.tbagrel1.gmd.jdbc.utils.CsvWriter;
import com.tbagrel1.gmd.jdbc.utils.MysqlAssistant;

import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

public class TpJdbcEx1 {
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
            CsvWriter csvWriter = new CsvWriter(Paths.get("tp_jdbc/output/mysql.csv"));

            for (PersonalInformation info : allInfo) {
                csvWriter.write(info.toTuple());
            }

            csvWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MysqlAssistant.withConnection(connection -> convertMysqlCsv(connection, false));
    }
}
