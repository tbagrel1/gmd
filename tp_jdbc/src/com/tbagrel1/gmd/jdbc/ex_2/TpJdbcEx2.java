package com.tbagrel1.gmd.jdbc.ex_2;

import com.tbagrel1.gmd.jdbc.PersonalInformation;
import com.tbagrel1.gmd.jdbc.utils.CsvWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TpJdbcEx2 {
    public static void main(String[] args) {
        convertXmlCsv(Paths.get("tp_jdbc/data_sources/italie.xml"), false);
        convertXmlCsv(Paths.get("tp_jdbc/data_sources/espagne.xml"), false);
        convertXmlCsv(Paths.get("tp_jdbc/data_sources/angleterre.xml"), false);
    }

    public static void convertXmlCsv(Path filePath, boolean show) {
        try {
            PeopleXmlConnector xmlConnector = new PeopleXmlConnector(filePath);
            List<PersonalInformation> allInfo = xmlConnector.getAllInfo();

            if (show) {
                System.out.println("### Personal Information ###");
                for (PersonalInformation info : allInfo) {
                    System.out.println(info);
                }
            }

            Path outputPath = Paths.get("tp_jdbc/output/" + filePath.getFileName().toString().replace(".xml", "") + ".csv");
            CsvWriter csvWriter = new CsvWriter(outputPath);

            for (PersonalInformation info : allInfo) {
                csvWriter.write(info.toTuple());
            }

            csvWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
